package com.example.capston3.Repository;

import com.example.capston3.Model.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionPlanRepository extends JpaRepository<NutritionPlan, Integer> {

    NutritionPlan findNutritionPlanById(Integer id);

    NutritionPlan findNutritionPlanByUserId(Integer userId);

    boolean existsByUserId(Integer id);
}