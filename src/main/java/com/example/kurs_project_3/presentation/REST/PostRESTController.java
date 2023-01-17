package com.example.kurs_project_3.presentation.REST;


import com.example.kurs_project_3.buisnesslayer.JSONview.PostJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Post;
import com.example.kurs_project_3.buisnesslayer.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

@RestController
public class PostRESTController {
    @Autowired
    PostService postService;

    @PostMapping("/post")
    public Post savePost(@RequestBody Post post){
        Long depId;
        if(post.getDepartment() != null) {
            depId = post.getDepartment().getId();
        }else  depId = null;
        Post post1 = postService.save(post,depId);
        if (post1 == null) {
            String wrongField = "";
            if (post.getSalary() == null){
                wrongField = "salary";
            }
            if (post.getName() == null || post.getName().equals("")){
                wrongField = "name";
            }
            throw new NullPointerException(wrongField);
        }
        return post1;
    }

    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.findPostById(id);
    }

//    @GetMapping("/post/getAllPosts")
//    public Set<Post> getDepartment(){
//        return postService.findAll();
//    }
    @GetMapping("/post/getAllPosts")
    public TreeSet<PostJSON> getPosts(){
        return postService.findAllView();
    }

    @DeleteMapping("/post/delete/{id}")
    public void deletePost(@PathVariable String id){
        Long longId = Long.parseLong(id);
        postService.deleteById(longId);
    }
    @PutMapping("/post/update")
    public Post updatePost(@RequestBody Post post){
        Long depId;
        if(post.getDepartment() != null) {
            depId = post.getDepartment().getId();
        }else  depId = null;

        Post post1 = postService.save(post,depId);
        if (post1 == null) {
            String wrongField = "";
            if (post.getSalary() == null){
                wrongField = "salary";
            }
            if (post.getName() == null || post.getName().equals("")){
                wrongField = "name";
            }
            throw new NullPointerException(wrongField);
        }
        return post1;
    }

    @GetMapping("/post/search/{word}")
    public TreeSet<PostJSON> searchPosts(@PathVariable String word){
        return postService.searchPosts(word);
    }
}