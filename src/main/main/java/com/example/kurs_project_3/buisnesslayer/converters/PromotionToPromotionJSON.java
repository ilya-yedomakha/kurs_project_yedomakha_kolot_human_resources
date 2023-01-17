package com.example.kurs_project_3.buisnesslayer.converters;

import com.example.kurs_project_3.buisnesslayer.JSONview.PromotionJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Post;
import com.example.kurs_project_3.buisnesslayer.domain.Promotion;

import java.text.SimpleDateFormat;

public class PromotionToPromotionJSON {
    public static PromotionJSON toPromotionJSON(Promotion promotion) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Long employeeId;
        if (promotion.getEmployee() != null){
            employeeId = promotion.getEmployee().getId();
        }else employeeId = null;
        String promdate;
        if (promotion.getPromotion_date() != null){
            promdate = formatter.format(promotion.getPromotion_date());
        }else promdate = null;
        return new PromotionJSON(promotion.getId(),promdate,promotion.getPromotion_type(),employeeId);
    }
}
