package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.Qualification;
import com.example.kurs_project_3.buisnesslayer.domain.Speciality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
    Speciality findSpecialityById(Long id);
    int countSpecialitiesByNameNotNull();

    @Query(value = "SELECT * FROM speciality u WHERE u.speciality_id like %:word% or u.speciality_name like %:word% or u.univer_name like %:word% or" +
            " u.city_name like %:word% or u.rate like %:word%",
            nativeQuery = true)
    Set<Speciality> searchSpecialities(@Param("word") String word);
}
