package com.example.capston3.Controller;

import com.example.capston3.Api.ApiResponse;
import com.example.capston3.Model.ExercisePlan;
import com.example.capston3.Service.ExercisePlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise-plan")
@RequiredArgsConstructor
public class ExercisePlanController {

    private final ExercisePlanService exercisePlanService;


    @GetMapping("/get")
    public ResponseEntity<?> getAllExercisePlans() {
        List<ExercisePlan> exercisePlans = exercisePlanService.getAllExercisePlans();

        return ResponseEntity.status(200).body(exercisePlans);
    }


    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addExercisePlan(
            @PathVariable Integer userId,
            @RequestBody @Valid ExercisePlan exercisePlan) {

        exercisePlanService.addExercisePlan(userId, exercisePlan);

        return ResponseEntity.status(200).body(new ApiResponse("Exercise plan added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExercisePlan(
            @PathVariable Integer id,
            @RequestBody @Valid ExercisePlan exercisePlan) {

        exercisePlanService.updateExercisePlan(id, exercisePlan);

        return ResponseEntity.status(200).body(new ApiResponse("Exercise plan updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExercisePlan(@PathVariable Integer id) {

        exercisePlanService.deleteExercisePlan(id);

        return ResponseEntity.status(200).body(new ApiResponse("Exercise plan deleted successfully"));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getExercisePlanByUserId(@PathVariable Integer userId) {

        ExercisePlan exercisePlan = exercisePlanService.getExercisePlanByUserId(userId);

        return ResponseEntity.status(200).body(exercisePlan);
    }
}