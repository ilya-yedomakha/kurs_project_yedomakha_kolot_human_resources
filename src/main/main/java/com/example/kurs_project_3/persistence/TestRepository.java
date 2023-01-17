package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
    Test findTestById(Long id);
}
