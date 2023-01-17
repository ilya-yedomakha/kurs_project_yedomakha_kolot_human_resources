package com.example.kurs_project_3.buisnesslayer.service;

import com.example.kurs_project_3.buisnesslayer.JSONview.PromotionJSON;
import com.example.kurs_project_3.buisnesslayer.converters.PromotionToPromotionJSON;
import com.example.kurs_project_3.buisnesslayer.domain.Employee;
import com.example.kurs_project_3.buisnesslayer.domain.Promotion;
import com.example.kurs_project_3.persistence.EmployeeRepository;
import com.example.kurs_project_3.persistence.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class PromotionService{
    private final EmployeeRepository employeeRepository;
    private final PromotionRepository promotionRepository;

    public PromotionService(EmployeeRepository employeeRepository, PromotionRepository promotionRepository) {
        this.employeeRepository = employeeRepository;
        this.promotionRepository = promotionRepository;
    }

    public Promotion findPromotionById(Long id){
        return promotionRepository.findPromotionById(id);
    }

    public Promotion save(Promotion promotionToSave, Long employeeId){
        Promotion promotion;
        if(promotionToSave.getPromotion_type().equals("") || promotionToSave.getPromotion_date() == null){
           return null;
        }
        if(employeeId!=null) {
            Employee dep = employeeRepository.findEmployeeById(employeeId);
            promotionToSave.setEmployee(dep);

            promotion = promotionRepository.save(promotionToSave);
            if(dep != null){
                dep.getPromotions().add(promotionToSave);
                employeeRepository.save(dep);
            }
        }else {
            promotionToSave.setEmployee(null);
            promotion = promotionRepository.save(promotionToSave);
        }

        return promotion;
    }

    public void deleteById(Long id){
        Promotion promotion = promotionRepository.findPromotionById(id);
        if(promotion.getEmployee() != null){
            Employee dep = promotion.getEmployee();
            dep.getPromotions().remove(promotion);
            employeeRepository.save(dep);
        }
        promotionRepository.deleteById(id);
    }

    public Set<Promotion> findAll() {
        Set<Promotion> promotions = new HashSet<>();
        promotionRepository.findAll().forEach(promotions::add);
        return promotions;
    }

    public TreeSet<PromotionJSON> findAllView() {
        Set<Promotion> promotions = new HashSet<>();
        Set<PromotionJSON> promotionJSONs = new HashSet<>();
        promotionRepository.findAll().forEach(promotions::add);
        for (Promotion promotion:promotions){
            promotionJSONs.add(PromotionToPromotionJSON.toPromotionJSON(promotion));
        }
        return new TreeSet<>(promotionJSONs);
    }

    public Promotion updatePromotion(Promotion promotionFromClient, Long employeeId){
        Employee dep;
        Promotion foundPromotion = promotionRepository.findPromotionById(promotionFromClient.getId());

        if (employeeId != null) {
            dep = employeeRepository.findEmployeeById(employeeId);
        } else {
            dep = null;
            foundPromotion.setEmployee(null);
        }
        if (foundPromotion == null || promotionFromClient.getPromotion_type().equals("") || promotionFromClient.getPromotion_date() == null) {
            return null;
        }

        foundPromotion.setPromotion_date(promotionFromClient.getPromotion_date());
        foundPromotion.setPromotion_type(promotionFromClient.getPromotion_type());
        if (dep != null) {
            foundPromotion.setEmployee(dep);
            dep.getPromotions().add(promotionFromClient);
            employeeRepository.save(dep);

        }

        Promotion promotion = promotionRepository.save(foundPromotion);
        return promotion;
    }

    public TreeSet<PromotionJSON> searchPromotions(String word){
        Set<PromotionJSON> promotionJSONS = new HashSet<>();
        for(Promotion promotion : promotionRepository.searchPromotions(word)){
            promotionJSONS.add(PromotionToPromotionJSON.toPromotionJSON(promotion));
        }
        return new TreeSet<>(promotionJSONS);
    }
}
