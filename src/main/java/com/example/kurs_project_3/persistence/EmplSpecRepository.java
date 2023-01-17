package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.EmplSpec;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Keys.EmployeeSpecKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmplSpecRepository extends CrudRepository<EmplSpec,EmployeeSpecKey> {
    EmplSpec findEmplSpecById(EmployeeSpecKey id);
    @Query(value = "SELECT * FROM emplspec u WHERE u.employee_id like %:word% or u.speciality_id like %:word% or u.diploma_number like %:word%",
            nativeQuery = true)
    Set<EmplSpec> searchEmplSpecs(@Param("word") String word);
}
