package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.domain.DepartmentDeleteLog;
import com.example.kurs_project_3.persistence.DepartmentDeleteLogRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class DepartmentDeleteLogService {
    private final DepartmentDeleteLogRepository departmentDeleteLogRepository;

    public DepartmentDeleteLogService(DepartmentDeleteLogRepository departmentDeleteLogRepository) {
        this.departmentDeleteLogRepository = departmentDeleteLogRepository;
    }

    public TreeSet<DepartmentDeleteLog> findAllDeleteLogs(){
        Set<DepartmentDeleteLog> departmentDeleteLogs = new HashSet<>();
        departmentDeleteLogRepository.findAll().forEach(departmentDeleteLogs::add);
        return new TreeSet<>(departmentDeleteLogs);
    }
}
