package com.example.kurs_project_3.buisnesslayer.JSONview;

import com.example.kurs_project_3.buisnesslayer.domain.Enums.Sex;
import com.example.kurs_project_3.buisnesslayer.domain.Post;

import javax.persistence.*;
import java.util.Date;

public class EmployeeJSON implements Comparable<EmployeeJSON>{
    private Long id;

    private Long post_id;

    private String name;

    private String birth_date;

    private String employment_date;

    private String home_adress;

    private String phone_number;

    private String sex;

    public EmployeeJSON(Long id, Long post_id, String name, String birth_date, String employment_date, String home_adress, String phone_number, String sex) {
        this.id = id;
        this.post_id = post_id;
        this.name = name;
        this.birth_date = birth_date;
        this.employment_date = employment_date;
        this.home_adress = home_adress;
        this.phone_number = phone_number;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(String employment_date) {
        this.employment_date = employment_date;
    }

    public String getHome_adress() {
        return home_adress;
    }

    public void setHome_adress(String home_adress) {
        this.home_adress = home_adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int compareTo(EmployeeJSON employee) {
        return this.id.compareTo(employee.id);
    }
}
