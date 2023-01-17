package com.example.kurs_project_3.buisnesslayer.converters;

import com.example.kurs_project_3.buisnesslayer.JSONview.SpecialityJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Speciality;

import java.text.SimpleDateFormat;

public class SpecialityToSpecialityJSON {
    public static SpecialityJSON toSpecialityJSON(Speciality speciality) {
        return new SpecialityJSON(speciality.getId(),speciality.getName(),speciality.getUniver_name(),speciality.getCity_name(),speciality.getRate().name());
    }
}
