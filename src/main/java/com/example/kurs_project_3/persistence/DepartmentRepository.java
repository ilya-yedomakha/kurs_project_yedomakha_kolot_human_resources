package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findDepartmentById(Long id);

    @Query(value = "SELECT * FROM department u WHERE u.id like %:o% or u.name like %:o% or u.boss_name like %:o%",
            nativeQuery = true)
    Set<Department> findDepartmentsByIdOrBossNameOrName(@Param("o") String o);
}
