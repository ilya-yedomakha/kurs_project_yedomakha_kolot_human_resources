package com.example.kurs_project_3.buisnesslayer.domain;

import com.example.kurs_project_3.buisnesslayer.domain.Enums.EduRate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Speciality implements Serializable,Comparable<Speciality> {

    @Id
    @Column(name = "speciality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "speciality_name")
    private String name;

    private String univer_name;

    private String city_name;

    @Enumerated(EnumType.STRING)
    private EduRate rate;

    @OneToMany(mappedBy = "speciality")
    @JsonIgnore
    Set<EmplSpec> emplSpecs = new HashSet<>();

    public Set<EmplSpec> getEmplSpecs() {
        return emplSpecs;
    }

    public void setEmplSpecs(Set<EmplSpec> emplSpecs) {
        this.emplSpecs = emplSpecs;
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

    public EduRate getRate() {
        return rate;
    }

    public void setRate(EduRate rate) {
        this.rate = rate;
    }

    public Speciality() {
    }

//    public Speciality(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Speciality o) {
        return this.id.compareTo(o.id);
    }

//    @Override
//    public String toString() {
//        return "Speciality{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }
}
