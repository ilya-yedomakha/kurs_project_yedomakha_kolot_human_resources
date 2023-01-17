package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.kurs_project_3.buisnesslayer.domain.Test;

import java.util.Set;

@RestController
public class TestRESTController {
    @Autowired
    TestService testService;

    @GetMapping("/test/{id}")
    public Test getTest(@PathVariable Long id){
        return testService.findTestById(id);
    }

    @GetMapping("/test/getAll")
    public Set<Test> getAllTests(){
        return testService.findAll();
    }


}
