package ru.tinkoff.summer.taskmicroservice.application.port.data;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.summer.taskshareddomain.AttemptDTO;
import ru.tinkoff.summer.taskshareddomain.task.dto.TaskForAttemptDTO;



public interface CrudAdapter {

    @PostMapping("/api/v1/tasks/{taskId}/attempts")
    AttemptDTO save(@PathVariable("taskId") long taskId , @RequestBody  AttemptDTO attemptDTO);
    @PutMapping("/api/v1/attempts/{id}")
    AttemptDTO update(@PathVariable("id") long id, @RequestBody AttemptDTO attemptDTO);
    @GetMapping("/api/v1/attempts/{id}")
    AttemptDTO getById(@PathVariable("id") long id);
    @GetMapping("/api/v1/tasks/{taskId}/details")
    TaskForAttemptDTO getTask(@PathVariable("taskId") long taskId);
}
