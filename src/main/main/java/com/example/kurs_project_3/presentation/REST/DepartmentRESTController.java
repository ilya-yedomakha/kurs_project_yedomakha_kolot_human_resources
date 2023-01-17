package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.domain.Department;
import com.example.kurs_project_3.buisnesslayer.domain.DepartmentDeleteLog;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.service.DepartmentDeleteLogService;
import com.example.kurs_project_3.buisnesslayer.service.DepartmentService;
import com.example.kurs_project_3.buisnesslayer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

@RestController
public class DepartmentRESTController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentDeleteLogService departmentDeleteLogService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department){
        Department department1 = departmentService.save(department);
        if (department1 == null) {
            String wrongField = "";
            if(department.getBossName() == null || department.getBossName().equals("")){
                wrongField = "boss name";
            }
            if(department.getName() == null || department.getName().equals("")){
                wrongField = "name";
            }
            System.out.println(wrongField);
           throw new NullPointerException(wrongField);
        }else {
            return department1;
        }
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("/department/search/{word}")
    public TreeSet<Department> getDepartment(@PathVariable String word) {
        return departmentService.search(word);
    }

    @GetMapping("/department/getAllDepartments")
    public Set<Department> getDepartment(){
        return departmentService.findAll();
    }
    @DeleteMapping("/department/delete/{id}")
    public void deleteDepartment(@PathVariable String id){
        Long longId = Long.parseLong(id);
        departmentService.deleteById(longId);
    }
    @PutMapping("/department/update")
    public Department updateDepartment(@RequestBody Department department){
        Department department1 = departmentService.save(department);
        if (department1 == null) {
            String wrongField = "";
            if(department.getBossName() == null || department.getBossName().equals("")){
                wrongField = "boss name";
            }
            if(department.getName() == null || department.getName().equals("")){
                wrongField = "name";
            }
            System.out.println(wrongField);
            throw new NullPointerException(wrongField);
        }else {
            return department1;
        }
    }
    @GetMapping("/department/deletelog")
    public TreeSet<DepartmentDeleteLog> getLogs(){
        return departmentDeleteLogService.findAllDeleteLogs();
    }

    @GetMapping("/department/testEmpl")
    public TreeSet<Employee> test(){
        return employeeService.findAll();
    }
}
