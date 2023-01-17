package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Employee findEmployeeById(Long id);

    @Transactional
    @Procedure
    Set<Employee> SHOW_WOMEN_THAT_HAVE_MORE_SALARY_THAN_ALL_MEN_IN_N_POST(String postname);

    @Transactional
    @Procedure
    Long NUMBER_OF_EMPLOYEES_WHO_WORKS_MORE_THAN_N_YEARS(@Param("n_years") Long n_years);

    @Query(value = "SELECT * FROM employee u WHERE u.employee_id like %:o% or u.full_name like %:o% or u.home_adress like %:o% or u.phone_number like %:o% or u.sex like %:o% or u.post_id like %:o% or u.employment_data like %:o% or u.birth_date like %:o%",
            nativeQuery = true)
    Set<Employee> searchEmployees(@Param("o")String o);

    @Query(value = "SELECT\n" +
            "e.employee_id,\n" +
            "e.full_name,\n" +
            "e.birth_date,\n" +
            "e.home_adress,\n" +
            "e.phone_number,\n" +
            "e.sex,\n" +
            "e.employment_data,\n" +
            "e.post_id\n" +
            "FROM employee e\n" +
            "RIGHT JOIN promotion p ON e.employee_id = p.employee_id\n" +
            "WHERE MONTH(p.promotion_date) = MONTH(CURRENT_DATE) AND YEAR(p.promotion_date) = YEAR(CURRENT_DATE)\n" +
            "GROUP BY e.employee_id\n" +
            "ORDER BY COUNT(e.employee_id) DESC LIMIT 3",
            nativeQuery = true)
    List<Employee> top3Empl();
}
