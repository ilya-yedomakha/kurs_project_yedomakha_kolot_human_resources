package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.JSONview.EmplSpecJSON;
import com.example.kurs_project_3.buisnesslayer.JSONview.EmployeeJSON;
import com.example.kurs_project_3.buisnesslayer.domain.EmplSpec;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Keys.EmployeeSpecKey;
import com.example.kurs_project_3.buisnesslayer.domain.Post;
import com.example.kurs_project_3.buisnesslayer.service.EmplSpecService;
import com.example.kurs_project_3.buisnesslayer.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

@RestController
public class EmplSpecRESTController {
    @Autowired
    EmplSpecService emplSpecService;

    @PostMapping("/employee_speciality")
    public EmplSpec savePost(@RequestBody EmplSpec emplSpec) {
        Long emplId;
        Long specId;
        if (emplSpec.getEmployee() != null) {
            emplId = emplSpec.getEmployee().getId();
        } else emplId = null;
        if (emplSpec.getSpeciality() != null) {
            specId = emplSpec.getSpeciality().getId();
        } else specId = null;
        EmplSpec employee1 = emplSpecService.save(emplSpec, emplId, specId);
        if (employee1 == null) {
            String wrongField = "";
            if(emplSpec.getDiploma_number() == null || emplSpec.getDiploma_number().equals("")){
                wrongField = "diploma number";
            }
            throw new NullPointerException(wrongField);
        }
        return employee1;
    }

    @GetMapping("/employee_speciality/getAll")
    public Set<EmplSpec> getDepartment() {
        return emplSpecService.findAll();
    }

    @GetMapping("/employee_speciality/getAllEmplSpecs")
    public Set<EmplSpecJSON> getDepartmentView() {
        return emplSpecService.findAllView();
    }

    @DeleteMapping("/employee_speciality/delete/employee/{emplId}/speciality/{specId}")
    public void deleteEmplSpec(@PathVariable Long emplId,@PathVariable Long specId){
        emplSpecService.deleteById(new EmployeeSpecKey(emplId,specId));
    }

    @GetMapping("/employee_speciality/search/{word}")
    public Set<EmplSpecJSON> searchEmployees(@PathVariable String word){
        return emplSpecService.searchEmplSpec(word);
    }
}
