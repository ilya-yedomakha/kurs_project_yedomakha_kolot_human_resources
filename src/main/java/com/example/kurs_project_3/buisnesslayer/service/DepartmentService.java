package com.example.kurs_project_3.buisnesslayer.service;


import com.example.kurs_project_3.buisnesslayer.domain.Department;
import com.example.kurs_project_3.buisnesslayer.domain.Post;
import com.example.kurs_project_3.persistence.DepartmentRepository;
import com.example.kurs_project_3.persistence.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final PostRepository postRepository;


    public DepartmentService(DepartmentRepository departmentRepository, PostRepository postRepository){
        this.departmentRepository = departmentRepository;
        this.postRepository = postRepository;
    }

    public Department findDepartmentById(Long id){
        return departmentRepository.findDepartmentById(id);
    }

    public Department save(Department toSave){
        if(toSave.getName() == null || toSave.getName().equals("") || toSave.getBossName() == null || toSave.getBossName().equals("")){
            return null;
        }
        return departmentRepository.save(toSave);
    }

    public void deleteById(Long id){
        for(Post post : departmentRepository.findDepartmentById(id).getPosts()){
            post.setDepartment(null);
            postRepository.save(post);
        }
        Department dep = departmentRepository.findDepartmentById(id);
        dep.getPosts().clear();
        departmentRepository.save(dep);
        departmentRepository.deleteById(id);
    }

    public TreeSet<Department> findAll() {
        Set<Department> departments = new HashSet<>();
        departmentRepository.findAll().forEach(departments::add);
//        for (Department d : departments){
//                System.out.println(d.getId() + ": " + d.getPosts());
//        }
        return new TreeSet<>(departments);
    }

    public TreeSet<Department> search(String o){
        Set<Department> departments = departmentRepository.findDepartmentsByIdOrBossNameOrName(o);
        return new TreeSet<>(departments);
    }
}
