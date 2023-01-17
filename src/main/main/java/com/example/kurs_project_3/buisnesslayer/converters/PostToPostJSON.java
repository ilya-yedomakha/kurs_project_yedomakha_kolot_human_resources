package com.example.kurs_project_3.buisnesslayer.converters;

import com.example.kurs_project_3.buisnesslayer.JSONview.PostJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Post;

import java.text.SimpleDateFormat;

public class PostToPostJSON {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static PostJSON toPostJSON(Post post){
        Long depId;
        if(post.getDepartment() == null){
            depId = null;
        }else depId = post.getDepartment().getId();
        return new PostJSON(post.getId(), post.getName(), post.getSalary(), depId);
    }
}
