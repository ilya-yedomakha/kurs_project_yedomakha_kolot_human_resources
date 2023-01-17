package com.example.kurs_project_3.buisnesslayer.JSONview;


public class PostJSON implements Comparable<PostJSON>{
    private Long post_id;

    private String post_name;

    private Float salary;

    private Long department_id;

    public PostJSON(Long post_id, String post_name, Float salary, Long department_id) {
        this.post_id = post_id;
        this.post_name = post_name;
        this.salary = salary;
        this.department_id = department_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public int compareTo(PostJSON post) {
        return this.post_id.compareTo(post.post_id);
    }
}
