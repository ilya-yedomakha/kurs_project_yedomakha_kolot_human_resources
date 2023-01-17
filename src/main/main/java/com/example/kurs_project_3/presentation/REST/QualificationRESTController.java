package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.JSONview.QualificationJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Qualification;
import com.example.kurs_project_3.buisnesslayer.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.TreeSet;

@RestController
public class QualificationRESTController {
    @Autowired
    QualificationService  qualificationService;

    @PostMapping("/qualification")
    public Qualification saveQualification(@RequestBody Qualification qualification){
        Long depId;
        if(qualification.getEmployee() != null) {
            depId = qualification.getEmployee().getId();
        }else  depId = null;
        Qualification qualification1 = qualificationService.save(qualification,depId);
        if (qualification1 == null) {
            String wrongField = "";
            if (qualification.getCertificate_end() == null){
                wrongField = "Certificate end";
            }
            if (qualification.getPassing_date() == null){
                wrongField = "Passing date";
            }
            if (qualification.getCertificate() == null || qualification.getCertificate().equals("")){
                wrongField = "Certificate";
            }
            if (qualification.getQualification_name() == null || qualification.getQualification_name().equals("")){
                wrongField = "Name";
            }
            throw new NullPointerException(wrongField);
        }else {
            return qualification1;
        }
    }

    @GetMapping("/qualification/{id}")
    public Qualification getQualification(@PathVariable Long id) {
        return qualificationService.findQualificationById(id);
    }

    @GetMapping("/qualification/getAllQualifications")
    public TreeSet<QualificationJSON> getQualifications(){
        return qualificationService.findAllView();
    }
    @DeleteMapping("/qualification/delete/{id}")
    public void deleteEmployee(@PathVariable String id){
        Long longId = Long.parseLong(id);
        qualificationService.deleteById(longId);
    }
    @PutMapping("/qualification/update")
    public Qualification updateQualification(@RequestBody Qualification qualification){
        Long depId;
        if(qualification.getEmployee() != null) {
            depId = qualification.getEmployee().getId();
        }else  depId = null;

        Qualification qualification1 = qualificationService.updateQualification(qualification,depId);
        if (qualification1 == null) {
            String wrongField = "";
            if (qualification.getCertificate_end() == null){
                wrongField = "Certificate end";
            }
            if (qualification.getPassing_date() == null){
                wrongField = "Passing date";
            }
            if (qualification.getCertificate() == null || qualification.getCertificate().equals("")){
                wrongField = "Certificate";
            }
            if (qualification.getQualification_name() == null || qualification.getQualification_name().equals("")){
                wrongField = "Name";
            }
            throw new NullPointerException(wrongField);
        }else {
            return qualification1;
        }
    }

    @Transactional
    @PutMapping("/qualification/SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED")
    public void SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED(){
        qualificationService.SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED();
    }

    @GetMapping("/qualification/search/{word}")
    public TreeSet<QualificationJSON> searchQualifications(@PathVariable String word){
        return qualificationService.searchQualifications(word);
    }
}
