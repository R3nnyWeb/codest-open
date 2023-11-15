package ru.tinkoff.summer.taskmicroservice.crud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.crud.model.Examples;
import ru.tinkoff.summer.taskmicroservice.crud.repository.ExamplesRepository;

import java.util.List;

@Service
public class ExamplesService {
    private final ExamplesRepository examplesRepository;

    @Autowired
    public ExamplesService(ExamplesRepository examplesRepository) {
        this.examplesRepository = examplesRepository;
    }

    public Examples saveExample(Examples example) {
        return examplesRepository.save(example);
    }

    public Examples updateExample(Long exampleId, Examples example) {
        Examples existingExample = examplesRepository.findById(exampleId)
                .orElseThrow(() -> new IllegalArgumentException("Example not found"));
        example.setExampleId(exampleId);
        return examplesRepository.save(example);
    }

    public Examples getExampleById(Long exampleId) {
        return examplesRepository.findById(exampleId).orElse(null);
    }

    public List<Examples> getAllExamples() {
        return examplesRepository.findAll();
    }

    public void deleteExample(Long exampleId) {
        examplesRepository.deleteById(exampleId);
    }
}
