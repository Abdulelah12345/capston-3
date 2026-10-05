package com.example.capston3.Controller;

import com.example.capston3.Api.ApiResponse;
import com.example.capston3.Model.NutritionPlan;
import com.example.capston3.Service.NutritionPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nutrition-plan")
@RequiredArgsConstructor
public class NutritionPlanController {

    private final NutritionPlanService nutritionPlanService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllNutritionPlans() {
        List<NutritionPlan> nutritionPlans = nutritionPlanService.getAllNutritionPlans();

        return ResponseEntity.status(200).body(nutritionPlans);
    }


    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addNutritionPlan(@PathVariable Integer userId, @RequestBody @Valid NutritionPlan nutritionPlan) {

        nutritionPlanService.addNutritionPlan(userId, nutritionPlan);

        return ResponseEntity.status(200).body(new ApiResponse("Nutrition plan added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNutritionPlan(@PathVariable Integer id, @RequestBody @Valid NutritionPlan nutritionPlan) {

        nutritionPlanService.updateNutritionPlan(id, nutritionPlan);

        return ResponseEntity.status(200).body(new ApiResponse("Nutrition plan updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNutritionPlan(@PathVariable Integer id) {

        nutritionPlanService.deleteNutritionPlan(id);

        return ResponseEntity.status(200).body(new ApiResponse("Nutrition plan deleted successfully"));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getNutritionPlanByUserId(@PathVariable Integer userId) {

        NutritionPlan nutritionPlan =nutritionPlanService.getNutritionPlanByUserId(userId);

        return ResponseEntity.status(200).body(nutritionPlan);
    }
}