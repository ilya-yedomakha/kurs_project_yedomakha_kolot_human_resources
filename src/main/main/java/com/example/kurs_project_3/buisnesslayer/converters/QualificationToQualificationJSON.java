package com.example.kurs_project_3.buisnesslayer.converters;

import com.example.kurs_project_3.buisnesslayer.JSONview.QualificationJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Qualification;

import java.text.SimpleDateFormat;

public class QualificationToQualificationJSON {
    public static QualificationJSON toQualificationJSON(Qualification qualification) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long employeeId;
        if (qualification.getEmployee() != null){
            employeeId = qualification.getEmployee().getId();
        }else employeeId = null;
        String passingdate;
        String certificateend;
        if(qualification.getPassing_date() != null){
            passingdate = formatter.format(qualification.getPassing_date());
        }else passingdate = null;
        if(qualification.getCertificate_end() != null){
            certificateend = formatter.format(qualification.getCertificate_end());
        }else certificateend = null;
        return new QualificationJSON(qualification.getId(),qualification.getQualification_name(),qualification.getCertificate(),passingdate,certificateend,employeeId);
    }
}
