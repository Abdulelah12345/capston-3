package com.example.capston3.Repository;

import com.example.capston3.Model.VitalSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalSignRepository extends JpaRepository<VitalSign, Integer> {

    VitalSign findVitalSignById(Integer id);


}