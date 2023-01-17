package com.example.kurs_project_3.buisnesslayer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department implements Comparable<Department>{

    private String name;
    @Column(name = "boss_name")
    private String bossName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    public Set<Post> getPosts() {
        return posts;
    }

    public void setEmployees(Set<Post> employees) {
        this.posts = employees;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Department() {
    }

//    public Department(String name, String boss_name, Set<Employee> employees) {
//        this.name = name;
//        this.boss_name = boss_name;
//        this.employees = employees;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String boss_name) {
        this.bossName = boss_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int compareTo(Department dep) {
        return this.id.compareTo(dep.id);
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", boss_name='" + bossName + '\'' +
                ", posts=" + posts +
                ", id=" + id +
                '}';
    }
}
