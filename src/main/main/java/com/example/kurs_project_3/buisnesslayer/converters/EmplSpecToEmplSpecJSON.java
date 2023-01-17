package com.example.kurs_project_3.buisnesslayer.converters;

import com.example.kurs_project_3.buisnesslayer.JSONview.EmplSpecJSON;
import com.example.kurs_project_3.buisnesslayer.JSONview.EmployeeJSON;
import com.example.kurs_project_3.buisnesslayer.domain.EmplSpec;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;

public class EmplSpecToEmplSpecJSON {
    public static EmplSpecJSON toEmployeeSpecJSON(EmplSpec emplSpec){

        return new EmplSpecJSON(emplSpec.getEmployee().getId(),emplSpec.getSpeciality().getId(),emplSpec.getDiploma_number());
    }
}
