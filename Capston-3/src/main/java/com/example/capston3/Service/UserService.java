package com.example.capston3.Service;

import com.example.capston3.Api.ApiException;
//import com.example.capston3.Model.HealthProfile;
import com.example.capston3.Model.HealthProfile;
import com.example.capston3.Model.NutritionPlan;
import com.example.capston3.Model.User;
//import com.example.capston3.Repository.HealthProfileRepository;
import com.example.capston3.Repository.NutritionPlanRepository;
import com.example.capston3.Repository.UserRepository;
//import com.example.capston3.Repository.VitalSignRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final HealthProfileRepository healthProfileRepository;
//    private final VitalSignRepository vitalSignRepository;
    private final NutritionPlanRepository nutritionPlanRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }

    public void addUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ApiException("Email already exists");
        }
        if (userRepository.findUserByPhone(user.getPhone()) != null) {
            throw new ApiException("Phone already exists");
        }

        user.setId(null);
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }

        oldUser.setFullName(user.getFullName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setPassword(user.getPassword());
        oldUser.setDateOfBirth(user.getDateOfBirth());
        oldUser.setGender(user.getGender());
        oldUser.setWhatsappOptIn(user.getWhatsappOptIn());

        userRepository.save(oldUser);
    }


    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User not found");
        }

        userRepository.delete(user);
    }
}