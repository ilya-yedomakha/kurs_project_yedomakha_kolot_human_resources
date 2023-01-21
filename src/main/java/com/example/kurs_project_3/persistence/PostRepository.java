package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.EmplSpec;
import com.example.kurs_project_3.buisnesslayer.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
    Post findPostById(Long id);

    @Query(value = "SELECT * FROM post u WHERE u.post_id like %:word% or u.post_name like %:word% or u.salary like %:word% or u.department_id like %:word%",
            nativeQuery = true)
    Set<Post> searchPosts(@Param("word") String word);
}
