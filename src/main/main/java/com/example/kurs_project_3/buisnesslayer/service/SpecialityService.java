package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.JSONview.SpecialityJSON;
import com.example.kurs_project_3.buisnesslayer.converters.SpecialityToSpecialityJSON;
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
public class SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final EmplSpecRepository emplSpecRepository;

    public SpecialityService(SpecialityRepository specialityRepository, EmployeeRepository employeeRepository, EmplSpecRepository emplSpecRepository){
        this.specialityRepository = specialityRepository;
        this.emplSpecRepository = emplSpecRepository;
    }

    public Speciality findSpecialityById(Long id){
        return specialityRepository.findSpecialityById(id);
    }

    public TreeSet<Speciality> findAll(){
        Set<Speciality> specialities = new HashSet<>();
        specialityRepository.findAll().forEach(specialities::add);
        return new TreeSet<>(specialities);
    }
    public TreeSet<SpecialityJSON> findAllView() {
        Set<Speciality> specialitys = new HashSet<>();
        Set<SpecialityJSON> specialityJSONs = new HashSet<>();
        specialityRepository.findAll().forEach(specialitys::add);
        for (Speciality speciality:specialitys){
            specialityJSONs.add(SpecialityToSpecialityJSON.toSpecialityJSON(speciality));
        }
        return new TreeSet<>(specialityJSONs);
    }

    public void deleteById(Long id){
        for(EmplSpec emplSpec : specialityRepository.findSpecialityById(id).getEmplSpecs()){
            EmployeeSpecKey key = emplSpec.getId();
            emplSpecRepository.deleteById(key);
        }
        Speciality dep = specialityRepository.findSpecialityById(id);
        dep.getEmplSpecs().clear();
        specialityRepository.save(dep);
        specialityRepository.deleteById(id);
    }

    public Speciality save(Speciality specialityToSave){
        if (specialityToSave.getName().equals("") || specialityToSave.getUniver_name().equals("") || specialityToSave.getCity_name().equals("")){
            return null;
        }
        return specialityRepository.save(specialityToSave);
    }

    public int countSpecNotNull() {
        return specialityRepository.countSpecialitiesByNameNotNull();
    }

    public TreeSet<SpecialityJSON> searchSpecialities(String word){
        Set<SpecialityJSON> specialityJSONS = new HashSet<>();
        for(Speciality speciality : specialityRepository.searchSpecialities(word)){
            specialityJSONS.add(SpecialityToSpecialityJSON.toSpecialityJSON(speciality));
        }
        return new TreeSet<>(specialityJSONS);
    }
}
