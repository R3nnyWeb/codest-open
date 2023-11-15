package ru.tinkoff.summer.taskmicroservice.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.summer.taskmicroservice.crud.model.Test;

public interface TestRepo extends JpaRepository<Test,Long> {
    void deleteByTaskTaskId(long taskId);
}
