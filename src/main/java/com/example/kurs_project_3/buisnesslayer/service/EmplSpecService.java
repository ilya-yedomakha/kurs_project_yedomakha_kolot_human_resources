package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.JSONview.EmplSpecJSON;
import com.example.kurs_project_3.buisnesslayer.converters.EmplSpecToEmplSpecJSON;
import com.example.kurs_project_3.buisnesslayer.domain.EmplSpec;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Keys.EmployeeSpecKey;
import com.example.kurs_project_3.buisnesslayer.domain.Speciality;
import com.example.kurs_project_3.persistence.EmplSpecRepository;
import com.example.kurs_project_3.persistence.EmployeeRepository;
import com.example.kurs_project_3.persistence.SpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


@Service
public class EmplSpecService{
    private final EmployeeRepository employeeRepository;
    private final EmplSpecRepository emplSpecRepository;
    private final SpecialityRepository specialityRepository;

    public EmplSpecService(EmployeeRepository employeeRepository, EmplSpecRepository emplSpecRepository, SpecialityRepository specialityRepository) {
        this.employeeRepository = employeeRepository;
        this.emplSpecRepository = emplSpecRepository;

        this.specialityRepository = specialityRepository;
    }

    public EmplSpec save(EmplSpec emplSpecToSave, Long employeeId, Long specialityId){
        EmplSpec emplSpec;

        if(emplSpecToSave.getDiploma_number() == null){
            return null;
        }
        if(employeeId!=null) {
            Employee dep = employeeRepository.findEmployeeById(employeeId);
            emplSpecToSave.setEmployee(dep);

            emplSpec = emplSpecRepository.save(emplSpecToSave);
            if(dep != null){
                dep.getEmplSpecs().add(emplSpecToSave);
                employeeRepository.save(dep);
            }
        }else {
            emplSpecToSave.setEmployee(null);
            emplSpec = emplSpecRepository.save(emplSpecToSave);
        }
        if(specialityId!=null) {
            Speciality spec = specialityRepository.findSpecialityById(specialityId);
            emplSpecToSave.setSpeciality(spec);

            emplSpec = emplSpecRepository.save(emplSpecToSave);
            if(spec != null){
                spec.getEmplSpecs().add(emplSpecToSave);
                specialityRepository.save(spec);
            }
        }else {
            emplSpecToSave.setSpeciality(null);
            emplSpec = emplSpecRepository.save(emplSpecToSave);
        }

        return emplSpec;
    }

    public void deleteById(EmployeeSpecKey id){

        EmplSpec emplSpec = emplSpecRepository.findEmplSpecById(id);
        if(emplSpec.getEmployee() != null){
            Employee dep = emplSpec.getEmployee();
            dep.getEmplSpecs().remove(emplSpec);
            employeeRepository.save(dep);
        }

        if(emplSpec.getEmployee() != null){
            Speciality spec = emplSpec.getSpeciality();
            spec.getEmplSpecs().remove(emplSpec);
            specialityRepository.save(spec);
        }

        EmplSpec dep = emplSpecRepository.findEmplSpecById(id);
        dep.setEmployee(null);
        dep.setSpeciality(null);
        emplSpecRepository.save(dep);
        emplSpecRepository.deleteById(id);
    }

    public Set<EmplSpec> findAll() {
        Set<EmplSpec> emplSpecs = new HashSet<>();
        emplSpecRepository.findAll().forEach(emplSpecs::add);
        return emplSpecs;
    }

    public Set<EmplSpecJSON> findAllView() {
        Set<EmplSpec> emplSpecs = new HashSet<>();
        Set<EmplSpecJSON> emplSpecJSONs = new HashSet<>();
        emplSpecRepository.findAll().forEach(emplSpecs::add);
        for (EmplSpec emplSpec:emplSpecs){
            emplSpecJSONs.add(EmplSpecToEmplSpecJSON.toEmployeeSpecJSON(emplSpec));
        }
        return emplSpecJSONs;
    }

    public Set<EmplSpecJSON> searchEmplSpec(String o){
        Set<EmplSpecJSON> emplSpecJSONS = new HashSet<>();
        for(EmplSpec emplSpec : emplSpecRepository.searchEmplSpecs(o)){
            emplSpecJSONS.add(EmplSpecToEmplSpecJSON.toEmployeeSpecJSON(emplSpec));
        }
        return emplSpecJSONS;
    }

}




