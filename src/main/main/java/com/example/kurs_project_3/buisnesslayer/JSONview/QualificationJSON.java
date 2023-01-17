package com.example.kurs_project_3.buisnesslayer.JSONview;

public class QualificationJSON implements Comparable<QualificationJSON>{

    private Long qualification_id;

    private String qualification_name;

    private String certificate;

    private String passing_date;

    private String certificate_end;

    private Long employee_id;

    public QualificationJSON(Long qualification_id, String qualification_name, String certificate, String passing_date, String certificate_end, Long employee_id) {
        this.qualification_id = qualification_id;
        this.qualification_name = qualification_name;
        this.certificate = certificate;
        this.passing_date = passing_date;
        this.certificate_end = certificate_end;
        this.employee_id = employee_id;
    }

    public Long getQualification_id() {
        return qualification_id;
    }

    public void setQualification_id(Long qualification_id) {
        this.qualification_id = qualification_id;
    }

    public String getQualification_name() {
        return qualification_name;
    }

    public void setQualification_name(String qualification_name) {
        this.qualification_name = qualification_name;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPassing_date() {
        return passing_date;
    }

    public void setPassing_date(String passing_date) {
        this.passing_date = passing_date;
    }

    public String getCertificate_end() {
        return certificate_end;
    }

    public void setCertificate_end(String certificate_end) {
        this.certificate_end = certificate_end;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public int compareTo(QualificationJSON o) {
        return this.qualification_id.compareTo(o.qualification_id);
    }
}
