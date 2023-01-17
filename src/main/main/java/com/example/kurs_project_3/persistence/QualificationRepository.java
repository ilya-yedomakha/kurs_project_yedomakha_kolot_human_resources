package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.Promotion;
import com.example.kurs_project_3.buisnesslayer.domain.Qualification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface QualificationRepository extends CrudRepository<Qualification,Long> {
    Qualification findQualificationById(Long id);

    @Transactional
    @Procedure
    void SET_TODAY_IF_QUALIFICATION_WAS_EXPIRED();

    @Query(value = "SELECT * FROM qualification u WHERE u.qualification_id like %:word% or u.qualification_name like %:word% or u.certificate like %:word% or" +
            " u.passing_date like %:word% or u.certificate_end like %:word% or u.employee_id like %:word%",
            nativeQuery = true)
    Set<Qualification> searchQualifications(@Param("word") String word);
}
