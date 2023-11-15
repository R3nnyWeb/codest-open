package ru.tinkoff.summer.taskmicroservice.crud.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.summer.taskmicroservice.crud.model.SolutionsAttempts;

@Repository
public interface SolutionsAttemptsRepository extends JpaRepository<SolutionsAttempts, Long> {
}
