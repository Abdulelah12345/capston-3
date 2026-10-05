package com.example.capston3.Repository;

import com.example.capston3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);
    User findUserByEmail(String email);
        User findUserByPhone(String phone);
}