package com.example.kurs_project_3.buisnesslayer.JSONview;


public class SpecialityJSON implements Comparable<SpecialityJSON>{

    Long speciality_id;

    String speciality_name;

    String univer_name;

    String city_name;

    String rate;

    public SpecialityJSON(Long speciality_id, String speciality_name, String univer_name, String city_name, String rate) {
        this.speciality_id = speciality_id;
        this.speciality_name = speciality_name;
        this.univer_name = univer_name;
        this.city_name = city_name;
        this.rate = rate;
    }

    public Long getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(Long speciality_id) {
        this.speciality_id = speciality_id;
    }

    public String getSpeciality_name() {
        return speciality_name;
    }

    public void setSpeciality_name(String speciality_name) {
        this.speciality_name = speciality_name;
    }

    public String getUniver_name() {
        return univer_name;
    }

    public void setUniver_name(String univer_name) {
        this.univer_name = univer_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public int compareTo(SpecialityJSON o) {
        return this.speciality_id.compareTo(o.speciality_id);
    }
}
