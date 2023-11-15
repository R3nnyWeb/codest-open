package ru.tinkoff.summer.taskmicroservice.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.summer.taskmicroservice.crud.model.Test;
import ru.tinkoff.summer.taskmicroservice.crud.repository.TestRepository;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Long testId, Test test) {
        Test existingTest = testRepository.findById(test.getTestId())
                .orElseThrow(() -> new IllegalArgumentException("Test not Found"));

        return testRepository.save(test);
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
