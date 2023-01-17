package com.example.kurs_project_3.buisnesslayer.domain;

import com.example.kurs_project_3.buisnesslayer.domain.Keys.EmployeeSpecKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "emplspec")
public class EmplSpec{

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long emplSpecId;

    @EmbeddedId
    EmployeeSpecKey id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @MapsId("specialityId")
    @JoinColumn(name = "speciality_id")
    Speciality speciality;

    Long diploma_number;

    public EmplSpec() {
    }

    public EmployeeSpecKey getId() {
        return id;
    }

    public void setId(EmployeeSpecKey id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Long getDiploma_number() {
        return diploma_number;
    }

    public void setDiploma_number(Long diploma_number) {
        this.diploma_number = diploma_number;
    }

    @Override
    public String toString() {
        return "EmplSpec{" +
                "id=" + id +
                ", employee=" + employee +
                ", speciality=" + speciality +
                ", diploma_number=" + diploma_number +
                '}';
    }
}
