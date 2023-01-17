package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.JSONview.QualificationJSON;
import com.example.kurs_project_3.buisnesslayer.converters.QualificationToQualificationJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Qualification;
import com.example.kurs_project_3.persistence.EmployeeRepository;
import com.example.kurs_project_3.persistence.QualificationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class QualificationService{
    private final EmployeeRepository employeeRepository;
    private final QualificationRepository qualificationRepository;
//    private final SpecialityRepository specialityRepository;

    public QualificationService(EmployeeRepository employeeRepository, QualificationRepository qualificationRepository) {
        this.employeeRepository = employeeRepository;
        this.qualificationRepository = qualificationRepository;
    }

    public Qualification findQualificationById(Long id){
//        System.out.println(qualificationRepository.findQualificationById(id).getSpecialities());
        return qualificationRepository.findQualificationById(id);
    }

    public Qualification save(Qualification qualificationToSave, Long employeeId){
        Qualification qualification;
        if(qualificationToSave.getCertificate_end() == null || qualificationToSave.getQualification_name().equals("") || qualificationToSave.getCertificate().equals("") || qualificationToSave.getPassing_date() == null){
            return null;
        }

        if(employeeId!=null) {
            Employee dep = employeeRepository.findEmployeeById(employeeId);
            qualificationToSave.setEmployee(dep);

            qualification = qualificationRepository.save(qualificationToSave);
            if(dep != null){
                dep.getQualifications().add(qualificationToSave);
                employeeRepository.save(dep);
            }
        }else {
            qualificationToSave.setEmployee(null);
            qualification = qualificationRepository.save(qualificationToSave);
        }

        return qualification;
    }

    public void deleteById(Long id){
        Qualification qualification = qualificationRepository.findQualificationById(id);
        if(qualification.getEmployee() != null){
            Employee dep = qualification.getEmployee();
            dep.getQualifications().remove(qualification);
            employeeRepository.save(dep);
        }
        qualificationRepository.deleteById(id);
    }

    public Set<Qualification> findAll() {
        Set<Qualification> qualifications = new HashSet<>();
        qualificationRepository.findAll().forEach(qualifications::add);
        return qualifications;
    }

    public TreeSet<QualificationJSON> findAllView() {
        Set<Qualification> qualifications = new HashSet<>();
        Set<QualificationJSON> qualificationJSONs = new HashSet<>();
        qualificationRepository.findAll().forEach(qualifications::add);
        for (Qualification qualification:qualifications){
            qualificationJSONs.add(QualificationToQualificationJSON.toQualificationJSON(qualification));
        }
        return new TreeSet<>(qualificationJSONs);
    }

    public Qualification updateQualification(Qualification qualificationFromClient, Long employeeId){
        Employee dep;
        Qualification foundQualification = qualificationRepository.findQualificationById(qualificationFromClient.getId());

        if (employeeId != null) {
            dep = employeeRepository.findEmployeeById(employeeId);
        } else {
            dep = null;
            foundQualification.setEmployee(null);
        }
        if (foundQualification == null || qualificationFromClient.getCertificate_end() == null || qualificationFromClient.getQualification_name().equals("") || qualificationFromClient.getCertificate().equals("") || qualificationFromClient.getPassing_date() == null) {
            return null;
        }

        foundQualification.setQualification_name(qualificationFromClient.getQualification_name());
        foundQualification.setCertificate(qualificationFromClient.getCertificate());
        foundQualification.setPassing_date(qualificationFromClient.getPassing_date());
        foundQualification.setCertificate_end(qualificationFromClient.getCertificate_end());
        if (dep != null) {
            foundQualification.setEmployee(dep);
            dep.getQualifications().add(qualificationFromClient);
            employeeRepository.save(dep);

        }

        Qualification qualification = qualificationRepository.save(foundQualification);
        return qualification;
    }

    public void SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED(){
        qualificationRepository.SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED();
    }

    public TreeSet<QualificationJSON> searchQualifications(String word){
        Set<QualificationJSON> qualificationJSONS = new HashSet<>();
        for(Qualification qualification : qualificationRepository.searchQualifications(word)){
            qualificationJSONS.add(QualificationToQualificationJSON.toQualificationJSON(qualification));
        }
        return new TreeSet<>(qualificationJSONS);
    }
}
