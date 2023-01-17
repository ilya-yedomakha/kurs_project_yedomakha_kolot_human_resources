package com.example.kurs_project_3.persistence;

import com.example.kurs_project_3.buisnesslayer.domain.DepartmentDeleteLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDeleteLogRepository extends CrudRepository<DepartmentDeleteLog,Long> {
}
