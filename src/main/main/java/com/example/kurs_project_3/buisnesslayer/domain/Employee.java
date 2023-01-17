package com.example.kurs_project_3.buisnesslayer.domain;

import com.example.kurs_project_3.buisnesslayer.domain.Enums.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements Comparable<Employee>{

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
 //   @JsonIgnore
    private Post post;

    @Column(name = "full_name")
    private String name;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birth_date;

    @Column(name = "employment_data")
    @Temporal(TemporalType.DATE)
    private Date employment_date;

    private String home_adress;

    private String phone_number;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<Promotion> promotions = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private Set<Qualification> qualifications = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    Set<EmplSpec> emplSpecs = new HashSet<>();

    public Set<EmplSpec> getEmplSpecs() {
        return emplSpecs;
    }

    public void setEmplSpecs(Set<EmplSpec> emplSpecs) {
        this.emplSpecs = emplSpecs;
    }


    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }


    public Employee() {
    }

    public String toStringDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.employment_date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(Date employment_date) {
        this.employment_date = employment_date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employment_date=" + employment_date +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.id.compareTo(o.id);
    }
}
