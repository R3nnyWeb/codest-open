package ru.tinkoff.summer.taskmicroservice.crud.repository;


import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.summer.taskmicroservice.crud.model.Test;

@ReadingConverter
public interface TestRepository extends JpaRepository<Test, Long> {
}
