package ru.tinkoff.summer.taskmicroservice.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.summer.taskmicroservice.crud.model.TasksLangs;
import ru.tinkoff.summer.taskmicroservice.crud.model.composite_key.TaskLangsId;

public interface LangsTaskRepo extends JpaRepository<TasksLangs, TaskLangsId> {
}
