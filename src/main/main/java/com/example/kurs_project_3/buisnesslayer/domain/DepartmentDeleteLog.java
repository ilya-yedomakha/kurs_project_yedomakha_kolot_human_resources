package com.example.kurs_project_3.buisnesslayer.domain;

import javax.persistence.*;

@Entity
@Table(name = "department_delete_log")
public class DepartmentDeleteLog implements Comparable<DepartmentDeleteLog>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long department_id;

    private String department_name;
    private String department_boss_name;

    public DepartmentDeleteLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_boss_name() {
        return department_boss_name;
    }

    public void setDepartment_boss_name(String department_boss_name) {
        this.department_boss_name = department_boss_name;
    }

    @Override
    public int compareTo(DepartmentDeleteLog o) {
        return this.id.compareTo(o.id);
    }
}
