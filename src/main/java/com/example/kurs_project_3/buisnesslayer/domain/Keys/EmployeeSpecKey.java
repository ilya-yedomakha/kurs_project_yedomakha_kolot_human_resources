package com.example.kurs_project_3.buisnesslayer.domain.Keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeSpecKey implements Serializable {

    @Column(name = "employee_id")
    Long employeeId;

    @Column(name = "speciality_id")
    Long specialityId;

    public EmployeeSpecKey(Long employeeId, Long specialityId) {
        this.employeeId = employeeId;
        this.specialityId = specialityId;
    }

    public EmployeeSpecKey() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeSpecKey that = (EmployeeSpecKey) o;

        if (!Objects.equals(employeeId, that.employeeId)) return false;
        return Objects.equals(specialityId, that.specialityId);
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (specialityId != null ? specialityId.hashCode() : 0);
        return result;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }
}
