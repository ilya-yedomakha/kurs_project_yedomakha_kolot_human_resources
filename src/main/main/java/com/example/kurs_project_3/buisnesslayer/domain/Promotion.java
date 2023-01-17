package com.example.kurs_project_3.buisnesslayer.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @Column(name = "promotion_date")
    @Temporal(TemporalType.DATE)
    private Date promotion_date;

    @Column(name = "promotiom_type")
    private String promotion_type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    //   @JsonIgnore
    private Employee employee;

    public Promotion() {
    }

    public Date getPromotion_date() {
        return promotion_date;
    }

    public void setPromotion_date(Date promotion_date) {
        this.promotion_date = promotion_date;
    }

    public String getPromotion_type() {
        return promotion_type;
    }

    public void setPromotion_type(String promotion_type) {
        this.promotion_type = promotion_type;
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
}
