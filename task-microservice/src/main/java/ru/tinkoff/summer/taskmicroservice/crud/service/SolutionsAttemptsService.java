package ru.tinkoff.summer.taskmicroservice.crud.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.crud.model.SolutionsAttempts;
import ru.tinkoff.summer.taskmicroservice.crud.repository.LanguageRepository;
import ru.tinkoff.summer.taskmicroservice.crud.repository.SolutionsAttemptsRepository;
import ru.tinkoff.summer.taskmicroservice.crud.repository.TaskRepository;
import ru.tinkoff.summer.taskmicroservice.crud.repository.TestRepo;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.ExecutionStatus;
import ru.tinkoff.summer.taskshareddomain.Language;
import ru.tinkoff.summer.taskshareddomain.task.TaskTestCase;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionsAttemptsService {
    private final SolutionsAttemptsRepository solutionsAttemptsRepository;
    private final TaskRepository taskRepository;
    private final LanguageRepository languageRepository;
    private final TestRepo testRepo;
    Logger log = LoggerFactory.getLogger(SolutionsAttemptsService.class);

    public AttemptDTO saveSolutionsAttempts(long taskId, AttemptDTO dto) {
        var task = taskRepository.findById(taskId).orElse(null);
        var language = languageRepository.getByLanguage(dto.getLanguage().toString()).orElse(null);
        log.info("Save new attempt task {}, language {}, attempt {}", task.getTaskId(), language.getLanguage(), dto);
        var solutionAttempt = new SolutionsAttempts();
        solutionAttempt.setTask(task);
        solutionAttempt.setCode(dto.getCode());
        solutionAttempt.setStatus(dto.getStatus().toString());
        solutionAttempt.setLanguage(language);
        solutionAttempt.setCreationDate(LocalDateTime.now());
        solutionAttempt = solutionsAttemptsRepository.save(solutionAttempt);
        return solutionAttemptToDto(solutionAttempt);
    }

    private AttemptDTO solutionAttemptToDto(SolutionsAttempts solutionAttempt) {
        var dto = AttemptDTO.builder()
                .id(solutionAttempt.getAttemptId())
                .actualResult(solutionAttempt.getActualResult())
                .code(solutionAttempt.getCode())
                .executionTimeNs(solutionAttempt.getExecutionTime())
                .errorMessage(solutionAttempt.getErrorMessage())
                .status(ExecutionStatus.valueOf(solutionAttempt.getStatus()))
                .language(Language.valueOf(solutionAttempt.getLanguage().getLanguage()))
                .memoryUsageMb(solutionAttempt.getSolutionsVolume())
                .testCase(getFailedTest(solutionAttempt))
                .creationDate(solutionAttempt.getCreationDate())
                .build();
        log.info("Return  AttemptDTO {}", dto);
        return dto;
    }

    private TaskTestCase getFailedTest(SolutionsAttempts solutionAttempt) {
        var failedTest = solutionAttempt.getFailedTest();
        if (failedTest == null) return null;
        return new TaskTestCase(failedTest.getTestId(), failedTest.getInputParameters(), failedTest.getOutputParameters());
    }

    public AttemptDTO updateSolutionsAttempts(Long attemptId, AttemptDTO dto) {
        log.info("Update  attempt id {}, dto {}", attemptId, dto);
        SolutionsAttempts existingAttempts = solutionsAttemptsRepository.findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("SolutionsAttempts not found"));
        existingAttempts.setStatus(dto.getStatus().toString());
        existingAttempts.setErrorMessage(dto.getErrorMessage());

        if (dto.getStatus() != ExecutionStatus.ERROR) {
            existingAttempts.setExecutionTime((dto.getExecutionTimeNs()));
            existingAttempts.setSolutionsVolume(dto.getMemoryUsageMb());
        }
        if (dto.getStatus() == ExecutionStatus.FAILURE) {
            existingAttempts.setActualResult(dto.getActualResult());
            var test = testRepo.findById(dto.getTestCase().getId()).orElse(null);
            existingAttempts.setFailedTest(test);
        }

        log.info("Saving attempt {}", attemptId);
        existingAttempts = solutionsAttemptsRepository.save(existingAttempts);
        return solutionAttemptToDto(existingAttempts);
    }

    public AttemptDTO getSolutionsAttemptsById(Long attemptId) {
        log.info("Get attempt id {}", attemptId);
        var solutionAttempt = solutionsAttemptsRepository.findById(attemptId).orElse(null);
        if (solutionAttempt == null) return null;
        return solutionAttemptToDto(solutionAttempt);
    }

    public List<SolutionsAttempts> getAllSolutionsAttempts() {
        return solutionsAttemptsRepository.findAll();
    }

    public void deleteSolutionsAttempts(Long attemptId) {
        solutionsAttemptsRepository.deleteById(attemptId);
    }

}
