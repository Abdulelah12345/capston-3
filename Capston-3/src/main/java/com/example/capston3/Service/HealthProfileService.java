package com.example.capston3.Service;

import com.example.capston3.Api.ApiException;
import com.example.capston3.Enum.ChronicCondition;
import com.example.capston3.Model.HealthProfile;
import com.example.capston3.Model.User;
import com.example.capston3.Repository.HealthProfileRepository;
import com.example.capston3.Repository.NutritionPlanRepository;
import com.example.capston3.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class HealthProfileService {

    private final HealthProfileRepository healthProfileRepository;
    private final UserRepository userRepository;
    private final NutritionPlanRepository nutritionPlanRepository;

    public List<HealthProfile> getAllHealthProfiles() {
        return healthProfileRepository.findAll();
    }

    public HealthProfile getHealthProfileById(Integer id) {
        HealthProfile healthProfile = healthProfileRepository.findHealthProfileById(id);
        if (healthProfile == null) {
            throw new ApiException("Health profile not found");
        }
        return healthProfile;
    }

    public HealthProfile getHealthProfileByUserId(Integer userId) {
        if (userRepository.findUserById(userId) == null) {
            throw new ApiException("User not found");
        }

        HealthProfile healthProfile = healthProfileRepository.findHealthProfileByUserId(userId);
        if (healthProfile == null) {
            throw new ApiException("This user has no health profile");
        }
        return healthProfile;
    }

    public void addHealthProfile(Integer userId, HealthProfile healthProfile) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }
        if (healthProfileRepository.existsByUserId(userId)) {
            throw new ApiException("User already has a health profile");
        }

       // validateConditions(healthProfile.getConditions());

        healthProfile.setId(null);
        healthProfile.setUser(user);
        healthProfileRepository.save(healthProfile);
    }

    public void updateHealthProfile(Integer id, HealthProfile healthProfile) {
        HealthProfile oldHealthProfile = healthProfileRepository.findHealthProfileById(id);
        if (oldHealthProfile == null) {
            throw new ApiException("Health profile not found");
        }

       // validateConditions(healthProfile.getConditions());

        oldHealthProfile.setHeightCm(healthProfile.getHeightCm());
        oldHealthProfile.setActivityLevel(healthProfile.getActivityLevel());
        oldHealthProfile.setConditions(healthProfile.getConditions());
        oldHealthProfile.setExerciseRisk(healthProfile.getExerciseRisk());

        healthProfileRepository.save(oldHealthProfile);
    }

    public void deleteHealthProfile(Integer id) {
        HealthProfile healthProfile = healthProfileRepository.findHealthProfileById(id);
        if (healthProfile == null) {
            throw new ApiException("Health profile not found");
        }

        // the nutrition plan is built from the health profile
        if (nutritionPlanRepository.existsByUserId(healthProfile.getUser().getId())) {
            throw new ApiException("Delete the user's nutrition plan before deleting the health profile");
        }

        healthProfileRepository.delete(healthProfile);
    }

    // Business rules for the conditions list
    private void validateConditions(List<ChronicCondition> conditions) {
        if (new HashSet<>(conditions).size() != conditions.size()) {
            throw new ApiException("Conditions must not contain duplicates");
        }
        if (conditions.contains(ChronicCondition.DIABETES_T1)
                && conditions.contains(ChronicCondition.DIABETES_T2)) {
            throw new ApiException("Choose either type 1 or type 2 diabetes, not both");
        }
        if (conditions.contains(ChronicCondition.FRUCTOSE_MALABSORPTION)
                && conditions.contains(ChronicCondition.HEREDITARY_FRUCTOSE_INTOLERANCE)) {
            throw new ApiException("Choose either fructose malabsorption or hereditary fructose intolerance, not both");
        }
        if (conditions.contains(ChronicCondition.CELIAC)
                && conditions.contains(ChronicCondition.GLUTEN_SENSITIVITY)) {
            throw new ApiException("Choose either celiac disease or gluten sensitivity, not both");
        }
    }
}