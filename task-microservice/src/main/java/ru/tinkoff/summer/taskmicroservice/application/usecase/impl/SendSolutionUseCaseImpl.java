package ru.tinkoff.summer.taskmicroservice.application.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.DTO.SolutionData;
import ru.tinkoff.summer.taskmicroservice.application.port.data.CrudAdapter;

import ru.tinkoff.summer.taskmicroservice.application.port.messaging.AttemptProducer;
import ru.tinkoff.summer.taskmicroservice.application.usecase.SendSolutionUseCase;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.AttemptForExecuteDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;

@Service
@RequiredArgsConstructor
public class SendSolutionUseCaseImpl implements SendSolutionUseCase {

    private final AttemptProducer attemptProducer;
    private final CrudAdapter crudAdapter;
    Logger log = LoggerFactory.getLogger(SendSolutionUseCaseImpl.class);
    @Override
    public AttemptDTO execute( SolutionData solutionData) {
        var task  = crudAdapter.getTask(solutionData.getTaskId());
        log.info("Get task from CRUD: {}",task);
        var attempt = AttemptDTO.of(solutionData.getCode(), solutionData.getLanguage(), task);
        attempt.setStatus(ExecutionStatus.PENDING);
        attempt = crudAdapter.save( task.getId(), attempt);
        log.info("Save attempt on CRUD: {}",attempt);
        var dto = new AttemptForExecuteDTO();
        dto.setLanguage(solutionData.getLanguage());
        dto.setCode(solutionData.getCode());
        dto.setId(attempt.getId());
        dto.setParams(task.getParams());
        dto.setTaskTestCases(task.getTaskTestCases());
        dto.setMethodName(task.getMethodName());

        attemptProducer.publishForExecute(dto);
        return attempt;
    }
}
