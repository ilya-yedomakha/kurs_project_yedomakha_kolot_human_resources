package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.JSONview.SpecialityJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Speciality;
import com.example.kurs_project_3.buisnesslayer.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;
import java.util.TreeSet;

@RestController
public class SpecialityRESTController {
    @Autowired
    SpecialityService specialityService;

    @PostMapping("/speciality")
    public Speciality saveSpeciality(@RequestBody Speciality speciality){
        Speciality speciality1 = specialityService.save(speciality);
        if (speciality1 == null) {
            String wrongField = "";
            if (speciality.getRate() == null){
                wrongField = "Rate";
            }
            if (speciality.getCity_name() == null || speciality.getCity_name().equals("")){
                wrongField = "City";
            }
            if(speciality.getUniver_name() == null || speciality.getUniver_name().equals("")){
                wrongField = "University";
            }
            if (speciality.getName() == null || speciality.getName().equals("")){
                wrongField = "Name";
            }
            throw new NullPointerException(wrongField);
        }else return speciality1;
    }

    @GetMapping("/speciality/{id}")
    public Speciality getSpecialityById(@PathVariable Long id) {
        return specialityService.findSpecialityById(id);
    }

    @GetMapping("/speciality/getAllSpecialities")
    public TreeSet<SpecialityJSON> getSpecialities(){
        return specialityService.findAllView();
    }

    @GetMapping("/speciality/getAll")
    public TreeSet<Speciality> getSpec(){
        return specialityService.findAll();
    }

    @DeleteMapping("/speciality/delete/{id}")
    public void deleteSpeciality(@PathVariable String id){
        Long longId = Long.parseLong(id);
        specialityService.deleteById(longId);
    }
    @PutMapping("/speciality/update")
    public Speciality updateSpeciality(@RequestBody Speciality speciality){
        Speciality speciality1 = specialityService.save(speciality);
        if (speciality1 == null) {
            String wrongField = "";
            if (speciality.getRate() == null){
                wrongField = "Rate";
            }
            if (speciality.getCity_name() == null || speciality.getCity_name().equals("")){
                wrongField = "City";
            }
            if(speciality.getUniver_name() == null || speciality.getUniver_name().equals("")){
                wrongField = "University";
            }
            if (speciality.getName() == null || speciality.getName().equals("")){
                wrongField = "Name";
            }
            throw new NullPointerException(wrongField);
        }else return speciality1;
    }
    @GetMapping("/speciality/notnull")
    public int getNotNullCount(){
        return specialityService.countSpecNotNull();
    }

    @GetMapping("/speciality/search/{word}")
    public TreeSet<SpecialityJSON> searchSpecialitys(@PathVariable String word){
        return specialityService.searchSpecialities(word);
    }
}
