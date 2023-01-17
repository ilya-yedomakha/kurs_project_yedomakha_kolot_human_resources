package com.example.kurs_project_3.buisnesslayer.service;



import com.example.kurs_project_3.buisnesslayer.JSONview.PostJSON;
import com.example.kurs_project_3.buisnesslayer.converters.PostToPostJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Department;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Post;
import com.example.kurs_project_3.persistence.DepartmentRepository;
import com.example.kurs_project_3.persistence.EmployeeRepository;
import com.example.kurs_project_3.persistence.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


@Service
public class PostService{
    private final DepartmentRepository departmentRepository;
    private final PostRepository postRepository;
    private final EmployeeRepository employeeRepository;
//    private final SpecialityRepository specialityRepository;

    public PostService(DepartmentRepository departmentRepository, PostRepository postRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.postRepository = postRepository;
//        this.specialityRepository = specialityRepository;
        this.employeeRepository = employeeRepository;
    }

    public Post findPostById(Long id){
//        System.out.println(postRepository.findPostById(id).getSpecialities());
        return postRepository.findPostById(id);
    }

    public Post save(Post postToSave, Long departmentId){
        Post post;
        if (postToSave.getName().equals("") || postToSave.getSalary() == null){
            return null;
        }
        if(departmentId!=null) {
            Department dep = departmentRepository.findDepartmentById(departmentId);
            postToSave.setDepartment(dep);

            post = postRepository.save(postToSave);
            if(dep != null){
                dep.getPosts().add(postToSave);
                departmentRepository.save(dep);
            }
        }else {
            postToSave.setDepartment(null);
            post = postRepository.save(postToSave);
        }

        return post;
    }

    public void deleteById(Long id){
        Post post = postRepository.findPostById(id);
        if(post.getDepartment() != null){
            Department dep = post.getDepartment();
            dep.getPosts().remove(post);
            departmentRepository.save(dep);
        }
        for(Employee employee : postRepository.findPostById(id).getEmployees()){
            employee.setPost(null);
            employeeRepository.save(employee);
        }
        Post dep = postRepository.findPostById(id);
        dep.getEmployees().clear();
        postRepository.save(dep);
        postRepository.deleteById(id);
    }

    public TreeSet<Post> findAll() {
        Set<Post> posts = new HashSet<>();
        postRepository.findAll().forEach(posts::add);
        return new TreeSet<>(posts);
    }

    public TreeSet<PostJSON> findAllView() {
        Set<Post> posts = new HashSet<>();
        Set<PostJSON> postJSONs = new HashSet<>();
        postRepository.findAll().forEach(posts::add);
        for (Post post:posts){
            postJSONs.add(PostToPostJSON.toPostJSON(post));
        }
        return new TreeSet<>(postJSONs);
    }

    public Post updatePost(Post postFromClient, Long departmentId){
        Department dep;
        Post foundPost = postRepository.findPostById(postFromClient.getId());

        if (departmentId != null) {
            dep = departmentRepository.findDepartmentById(departmentId);
        } else {
            dep = null;
            foundPost.setDepartment(null);
        }
        if (foundPost == null) {
            return null;
        }

        if (postFromClient.getName().equals("") || postFromClient.getSalary() == null){
            return null;
        }
        foundPost.setSalary(postFromClient.getSalary());
        if (dep != null) {
            foundPost.setDepartment(dep);
            dep.getPosts().add(postFromClient);
            departmentRepository.save(dep);

        }

        Post post = postRepository.save(foundPost);
        return post;
    }

    public TreeSet<PostJSON> searchPosts(String word){
        Set<PostJSON> postJSONS = new HashSet<>();
        for(Post post : postRepository.searchPosts(word)){
            postJSONS.add(PostToPostJSON.toPostJSON(post));
        }
        return new TreeSet<>(postJSONS);
    }

}

