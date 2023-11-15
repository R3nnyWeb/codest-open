package ru.tinkoff.summer.taskmicroservice.application.port.data;

import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.crud.service.SolutionsAttemptsService;
import ru.tinkoff.summer.taskmicroservice.crud.service.TaskService;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;

@Service
public class CrudAdapterImpl implements CrudAdapter {
    private final SolutionsAttemptsService solutionsAttemptsService;
    private final TaskService taskService;

    public CrudAdapterImpl(SolutionsAttemptsService solutionsAttemptsService, TaskService taskService) {
        this.solutionsAttemptsService = solutionsAttemptsService;
        this.taskService = taskService;
    }

    @Override
    public AttemptDTO save(long taskId, AttemptDTO attemptDTO) {
        return solutionsAttemptsService.saveSolutionsAttempts(taskId, attemptDTO);
    }

    @Override
    public AttemptDTO update(long id, AttemptDTO attemptDTO) {
        return solutionsAttemptsService.updateSolutionsAttempts(id, attemptDTO);
    }

    @Override
    public AttemptDTO getById(long id) {
        return solutionsAttemptsService.getSolutionsAttemptsById(id);
    }

    @Override
    public TaskForAttemptDTO getTask(long taskId) {
        return taskService.getTaskDetailsById(taskId);
    }
}
