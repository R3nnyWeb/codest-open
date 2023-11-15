package ru.tinkoff.summer.taskmicroservice.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.summer.taskmicroservice.crud.model.Examples;

public interface ExamplesRepository extends JpaRepository<Examples, Long> {
}
