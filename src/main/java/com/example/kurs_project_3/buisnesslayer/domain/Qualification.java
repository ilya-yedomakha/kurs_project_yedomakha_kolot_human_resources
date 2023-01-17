package com.example.kurs_project_3.buisnesslayer.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "qualification")
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qualification_id")
    private Long id;

    private String qualification_name;

    private String certificate;

    private Date passing_date;

    private Date certificate_end;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Qualification() {
    }

    public String getQualification_name() {
        return qualification_name;
    }

    public void setQualification_name(String qualification_name) {
        this.qualification_name = qualification_name;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Date getPassing_date() {
        return passing_date;
    }

    public void setPassing_date(Date passing_date) {
        this.passing_date = passing_date;
    }

    public Date getCertificate_end() {
        return certificate_end;
    }

    public void setCertificate_end(Date certificate_end) {
        this.certificate_end = certificate_end;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Qualification{" +
                "id=" + id +
                ", qualification_name='" + qualification_name + '\'' +
                ", certificate='" + certificate + '\'' +
                '}';
    }
}
