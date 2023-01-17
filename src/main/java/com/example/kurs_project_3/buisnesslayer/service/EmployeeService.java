package com.example.kurs_project_3.buisnesslayer.service;



import com.example.kurs_project_3.buisnesslayer.JSONview.EmployeeJSON;
import com.example.kurs_project_3.buisnesslayer.converters.EmployeeToEmployeeJSON;
import com.example.kurs_project_3.buisnesslayer.domain.*;
import com.example.kurs_project_3.buisnesslayer.domain.Keys.EmployeeSpecKey;
import com.example.kurs_project_3.persistence.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class EmployeeService{
    private final PostRepository postRepository;
    private final EmployeeRepository employeeRepository;
    private final PromotionRepository promotionRepository;
    private final QualificationRepository qualificationRepository;
    private final SpecialityRepository specialityRepository;
    private final EmplSpecRepository emplSpecRepository;

    public EmployeeService(PostRepository postRepository, EmployeeRepository employeeRepository, PromotionRepository promotionRepository, QualificationRepository qualificationRepository, SpecialityRepository specialityRepository, EmplSpecRepository emplSpecRepository) {
        this.postRepository = postRepository;
        this.employeeRepository = employeeRepository;
        this.specialityRepository = specialityRepository;
        this.promotionRepository = promotionRepository;
        this.qualificationRepository = qualificationRepository;
        this.emplSpecRepository = emplSpecRepository;
    }

    public Employee findEmployeeById(Long id){
//        System.out.println(employeeRepository.findEmployeeById(id).getSpecialities());
        return employeeRepository.findEmployeeById(id);
    }

    public TreeSet<EmployeeJSON> searchEmployee(String o){
        Set<EmployeeJSON> employeeJSONS = new HashSet<>();
        for(Employee employee : employeeRepository.searchEmployees(o)){
            employeeJSONS.add(EmployeeToEmployeeJSON.toEmployeeJSON(employee));
        }
        return new TreeSet<>(employeeJSONS);
    }

    public Employee save(Employee employeeToSave, Long postId){
        Employee employee;
        String test = new String("^(?:\\+3)?8?(0\\d{9})$");
        Pattern pattern = Pattern.compile(test);
        Matcher matcher = pattern.matcher(employeeToSave.getPhone_number());

        if (employeeToSave.getHome_adress().equals("")){
            employeeToSave.setHome_adress(null);
        }

        if(!matcher.matches() || employeeToSave.getName().equals("") || employeeToSave.getBirth_date() == null || employeeToSave.getEmployment_date() == null){
            return null;
        }
        if(postId!=null) {
            Post dep = postRepository.findPostById(postId);
            employeeToSave.setPost(dep);

            employee = employeeRepository.save(employeeToSave);
            if(dep != null){
                dep.getEmployees().add(employeeToSave);
                postRepository.save(dep);
            }
        }else {
            employeeToSave.setPost(null);
            employee = employeeRepository.save(employeeToSave);
        }

        return employee;
    }

    public void deleteById(Long id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee.getPost() != null){
            Post dep = employee.getPost();
            dep.getEmployees().remove(employee);
            postRepository.save(dep);
        }
        for(Promotion promotion : employeeRepository.findEmployeeById(id).getPromotions()){
            promotion.setEmployee(null);
            promotionRepository.save(promotion);
        }
        for(Qualification qualification : employeeRepository.findEmployeeById(id).getQualifications()){
            qualification.setEmployee(null);
            qualificationRepository.save(qualification);
        }
        Employee empl1 = employeeRepository.findEmployeeById(id);
        for(EmplSpec emplSpec : empl1.getEmplSpecs()){
            EmployeeSpecKey key = emplSpec.getId();
            emplSpecRepository.deleteById(key);
        }
        empl1.getEmplSpecs().clear();
        empl1.getPromotions().clear();
        empl1.getQualifications().clear();
        employeeRepository.save(empl1);
        employeeRepository.deleteById(id);
    }

    public TreeSet<Employee> findAll() {
        Set<Employee> employees = new HashSet<>();
        employeeRepository.findAll().forEach(employees::add);

        return new TreeSet<>(employees);
    }

    public TreeSet<EmployeeJSON> findAllView() {
        Set<Employee> employees = new HashSet<>();
        Set<EmployeeJSON> employeeJSONs = new HashSet<>();
        employeeRepository.findAll().forEach(employees::add);
        for (Employee employee:employees){
            employeeJSONs.add(EmployeeToEmployeeJSON.toEmployeeJSON(employee));
        }
        return new TreeSet<>(employeeJSONs);
    }


    public Employee updateEmployee(Employee employeeFromClient, Long postId){
        Post dep;
        Employee foundEmployee = employeeRepository.findEmployeeById(employeeFromClient.getId());

        if (postId != null) {
            dep = postRepository.findPostById(postId);
        } else {
            dep = null;
            foundEmployee.setPost(null);
        }
        if (foundEmployee == null) {
            return null;
        }

        if (employeeFromClient.getHome_adress().equals("")){
            employeeFromClient.setHome_adress(null);
        }
        String test = new String("^(?:\\+3)?8?(0\\d{9})$");
        Pattern pattern = Pattern.compile(test);
        Matcher matcher = pattern.matcher(employeeFromClient.getPhone_number());

        if(!matcher.matches() || employeeFromClient.getName().equals("") || employeeFromClient.getBirth_date() == null || employeeFromClient.getEmployment_date() == null){
            return null;
        }

        foundEmployee.setName(employeeFromClient.getName());
        foundEmployee.setBirth_date(employeeFromClient.getBirth_date());
        foundEmployee.setEmployment_date(employeeFromClient.getEmployment_date());
        foundEmployee.setHome_adress(employeeFromClient.getHome_adress());
        foundEmployee.setPhone_number(employeeFromClient.getPhone_number());
        foundEmployee.setSex(employeeFromClient.getSex());
        if (dep != null) {
            foundEmployee.setPost(dep);
            dep.getEmployees().add(employeeFromClient);
            postRepository.save(dep);
        }

        Employee employee = employeeRepository.save(foundEmployee);
        return employee;
    }


    public TreeSet<EmployeeJSON> SHOW_WOMEN_THAT_HAVE_MORE_SALARY_THAN_ALL_MEN_IN_N_POST(String postname){
        if (postname.equals("")){
            return null;
        }
        Set<EmployeeJSON> employeeJSONs = new HashSet<>();
        for (Employee employee:employeeRepository.SHOW_WOMEN_THAT_HAVE_MORE_SALARY_THAN_ALL_MEN_IN_N_POST(postname)){
            employeeJSONs.add(EmployeeToEmployeeJSON.toEmployeeJSON(employee));
        }
        return new TreeSet<>(employeeJSONs);
    }

    public Long NUMBER_OF_EMPLOYEES_WHO_WORKS_MORE_THAN_N_YEARS(Long n_years){
        return employeeRepository.NUMBER_OF_EMPLOYEES_WHO_WORKS_MORE_THAN_N_YEARS(n_years);
    }

    public List<EmployeeJSON> top3Empl(){
        List<EmployeeJSON> employeeJSONS = new ArrayList<>();
        for (Employee employee:employeeRepository.top3Empl()){
            employeeJSONS.add(EmployeeToEmployeeJSON.toEmployeeJSON(employee));
        }
        return employeeJSONS;
    }
}
