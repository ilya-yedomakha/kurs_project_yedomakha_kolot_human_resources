package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.JSONview.EmployeeJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Speciality;
import com.example.kurs_project_3.buisnesslayer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class EmployeeRESTController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        Long depId;
        if(employee.getPost() != null) {
            depId = employee.getPost().getId();
        }else  depId = null;
        Employee employee1 = employeeService.save(employee,depId);
        if (employee1 == null) {
            String wrongField = "";
            if (employee.getEmployment_date() == null){
                wrongField = "Employment date";
            }
            String test = "^(?:\\+3)?8?(0\\d{9})$";
            Pattern pattern = Pattern.compile(test);
            Matcher matcher = pattern.matcher(employee.getPhone_number());
            if (employee.getPhone_number() == null || employee.getPhone_number().equals("") || !matcher.matches()){
                wrongField = "Phone number";
            }
            if (employee.getBirth_date() == null){
                wrongField = "Birth date";
            }
            if (employee.getName() == null || employee.getName().equals("")){
                wrongField = "name";
            }
            throw new NullPointerException(wrongField);
        }else {
            return employee1;
        }
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/employee/getAllEmployees")
    public TreeSet<EmployeeJSON> getPost(){
        return employeeService.findAllView();
    }

    @GetMapping("/employee/getAll")
    public TreeSet<Employee> getEmpl(){
        return employeeService.findAll();
    }
    @DeleteMapping("/employee/delete/{id}")
    public void deletePost(@PathVariable String id){
        Long longId = Long.parseLong(id);
        employeeService.deleteById(longId);
    }
    @PutMapping("/employee/update")
    public Employee updateEmployee(@RequestBody Employee employee){
        Long depId;
        if(employee.getPost() != null) {
            depId = employee.getPost().getId();
        }else  depId = null;

        Employee employee1 = employeeService.save(employee,depId);
        if (employee1 == null) {
            String wrongField = "";
            if (employee.getEmployment_date() == null){
                wrongField = "Employment date";
            }
            String test = "^(?:\\+3)?8?(0\\d{9})$";
            Pattern pattern = Pattern.compile(test);
            Matcher matcher = pattern.matcher(employee.getPhone_number());
            if (employee.getPhone_number() == null || employee.getPhone_number().equals("") || !matcher.matches()){
                wrongField = "Phone number";
            }
            if (employee.getBirth_date() == null){
                wrongField = "Birth date";
            }
            if (employee.getName() == null || employee.getName().equals("")){
                wrongField = "name";
            }
            throw new NullPointerException(wrongField);
        }else {
            return employee1;
        }
    }

    @Transactional
    @GetMapping("/employee/SHOW_WOMEN_THAT_HAVE_MORE_SALARY_THAN_ALL_MEN_IN_N_POST/{postname}")
    public TreeSet<EmployeeJSON> SHOW_WOMEN_THAT_HAVE_MORE_SALARY_THAN_ALL_MEN_IN_N_POST(@PathVariable String postname){
        return employeeService.SHOW_WOMEN_THAT_HAVE_MORE_SALARY_THAN_ALL_MEN_IN_N_POST(postname);
    }


    @Transactional
    @GetMapping("/employee/NUMBER_OF_EMPLOYEES_WHO_WORKS_MORE_THAN_N_YEARS/{years}")
    public ResponseEntity<Long> NUMBER_OF_EMPLOYEES_WHO_WORKS_MORE_THAN_N_YEARS(@PathVariable Long years){
        Long entity = employeeService.NUMBER_OF_EMPLOYEES_WHO_WORKS_MORE_THAN_N_YEARS(years);
        if (entity == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(entity,HttpStatus.OK);
    }

    @GetMapping("/employee/search/{word}")
    public TreeSet<EmployeeJSON> searchEmployees(@PathVariable String word){
        return employeeService.searchEmployee(word);
    }
//    @PostMapping("/employee/{id}/speciality")
//    public Employee addSpecialityToEmployee(@PathVariable Long id,@RequestBody Speciality speciality){
//        return employeeService.addSpecialityToEmpl(id, speciality);
//    }

    @GetMapping("/employee/gettop3")
    public List<EmployeeJSON> top3Empl(){
        return employeeService.top3Empl();
    }
}