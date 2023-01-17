package com.example.kurs_project_3.buisnesslayer.JSONview;

public class EmplSpecJSON {
    private Long employee_id;
    private Long speciality_id;
    private Long diploma_number;

    public EmplSpecJSON(Long employee_id, Long speciality_id, Long diploma_number) {
        this.employee_id = employee_id;
        this.speciality_id = speciality_id;
        this.diploma_number = diploma_number;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Long getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(Long speciality_id) {
        this.speciality_id = speciality_id;
    }

    public Long getDiploma_number() {
        return diploma_number;
    }

    public void setDiploma_number(Long diploma_number) {
        this.diploma_number = diploma_number;
    }
}
