package ru.tinkoff.summer.taskmicroservice.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.summer.taskmicroservice.crud.model.Language;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long > {
    Optional<Language> getByLanguage(String language);
}
