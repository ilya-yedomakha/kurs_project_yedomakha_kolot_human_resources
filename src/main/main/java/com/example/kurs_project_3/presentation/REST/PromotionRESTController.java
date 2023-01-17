package com.example.kurs_project_3.presentation.REST;

import com.example.kurs_project_3.buisnesslayer.JSONview.PromotionJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Promotion;
import com.example.kurs_project_3.buisnesslayer.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.TreeSet;

@RestController
public class PromotionRESTController {
    @Autowired
    PromotionService promotionService;

    @PostMapping("/promotion")
    public Promotion savePromotion(@RequestBody Promotion promotion){
        Long depId;
        if(promotion.getEmployee() != null) {
            depId = promotion.getEmployee().getId();
        }else  depId = null;
        Promotion promotion1 = promotionService.save(promotion,depId);
        if (promotion1 == null) {
            String wrongField = "";
            if (promotion.getPromotion_date() == null){
                wrongField = "date";
            }
            if (promotion.getPromotion_type() == null || promotion.getPromotion_type().equals("")){
                wrongField = "type";
            }
            throw new NullPointerException(wrongField);
        }
        return promotion;
    }

    @GetMapping("/promotion/{id}")
    public Promotion getPromotion(@PathVariable Long id) {
        return promotionService.findPromotionById(id);
    }

    @GetMapping("/promotion/getAllPromotions")
    public TreeSet<PromotionJSON> getPromotions(){
        return promotionService.findAllView();
    }
    @DeleteMapping("/promotion/delete/{id}")
    public void deleteEmployee(@PathVariable String id){
        Long longId = Long.parseLong(id);
        promotionService.deleteById(longId);
    }
    @PutMapping("/promotion/update")
    public Promotion updatePromotion(@RequestBody Promotion promotion){
        Long depId;
        if(promotion.getEmployee() != null) {
            depId = promotion.getEmployee().getId();
        }else  depId = null;

        Promotion promotion1 = promotionService.updatePromotion(promotion,depId);
        if (promotion1 == null) {
            String wrongField = "";
            if (promotion.getPromotion_date() == null){
                wrongField = "date";
            }
            if (promotion.getPromotion_type() == null || promotion.getPromotion_type().equals("")){
                wrongField = "type";
            }
            throw new NullPointerException(wrongField);
        }
        return promotion;
    }

    @GetMapping("/promotion/search/{word}")
    public TreeSet<PromotionJSON> searchPromotions(@PathVariable String word){
        return promotionService.searchPromotions(word);
    }
}
