package com.example.kurs_project_3.buisnesslayer.converters;

import com.example.kurs_project_3.buisnesslayer.JSONview.EmployeeJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeToEmployeeJSON {
    public static EmployeeJSON toEmployeeJSON(Employee employee){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long postId;
        if(employee.getPost() == null){
            postId = null;
        }else postId = employee.getPost().getId();

        String birthdate;
        String empldate;

        if(employee.getBirth_date()!=null){
            birthdate = formatter.format(employee.getBirth_date());
        }else birthdate = null;

        if(employee.getEmployment_date()!=null){
            empldate = formatter.format(employee.getEmployment_date());
        }else empldate = null;
        String sex;
        if(employee.getSex() != null){
            sex = employee.getSex().name();
        }else sex = null;
        return new EmployeeJSON(employee.getId(), postId,employee.getName(), birthdate,empldate,employee.getHome_adress(),employee.getPhone_number(),sex);
    }
}
