package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.domain.Test;
import com.example.kurs_project_3.persistence.TestRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public Test findTestById(Long id){
        return testRepository.findTestById(id);
    }

    public Set<Test> findAll() {
        Set<Test> tests = new HashSet<>();
        testRepository.findAll().forEach(tests::add);
        return tests;
    }
}
