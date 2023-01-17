package com.example.kurs_project_3.buisnesslayer.JSONview;

public class PromotionJSON implements Comparable<PromotionJSON>{

    private Long promotion_id;

    private String promotion_date;

    private String promotion_type;

    private Long employee_id;

    public PromotionJSON(Long promotion_id, String promotion_date, String promotion_type, Long employee_id) {
        this.promotion_id = promotion_id;
        this.promotion_date = promotion_date;
        this.promotion_type = promotion_type;
        this.employee_id = employee_id;
    }

    public Long getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(Long promotion_id) {
        this.promotion_id = promotion_id;
    }

    public String getPromotion_date() {
        return promotion_date;
    }

    public void setPromotion_date(String promotion_date) {
        this.promotion_date = promotion_date;
    }

    public String getPromotion_type() {
        return promotion_type;
    }

    public void setPromotion_type(String promotion_type) {
        this.promotion_type = promotion_type;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public int compareTo(PromotionJSON promotion) {
        return this.promotion_id.compareTo(promotion.promotion_id);
    }
}
