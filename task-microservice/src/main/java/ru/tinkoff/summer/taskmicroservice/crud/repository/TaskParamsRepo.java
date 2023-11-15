package ru.tinkoff.summer.taskmicroservice.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.summer.taskmicroservice.crud.model.TaskParams;

public interface TaskParamsRepo extends JpaRepository<TaskParams,Long> {
}
