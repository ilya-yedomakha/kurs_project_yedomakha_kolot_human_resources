package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.Promotion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion,Long> {
    Promotion findPromotionById(Long id);

    @Query(value = "SELECT * FROM promotion u WHERE u.promotion_id like %:word% or u.promotion_date like %:word% or u.promotiom_type like %:word% or u.employee_id like %:word%",
            nativeQuery = true)
    Set<Promotion> searchPromotions(@Param("word") String word);
}
