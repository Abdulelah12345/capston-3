package com.example.capston3.Model;

import com.example.capston3.Enum.ChronicCondition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "health_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @NotNull(message = "Height is required")
    @DecimalMin(value = "50.0", message = "Height must be at least 50 cm")
    @DecimalMax(value = "250.0", message = "Height must be at most 250 cm")
    private Double heightCm;

    @NotNull(message = "Activity level is required")
    @Column(nullable = false, length = 20)
    private String activityLevel;

    // stored as a JSON array, e.g. ["DIABETES_T2", "HYPERTENSION"]
    @NotNull(message = "Conditions are required (use [] if none)")
    @NotNull(message = "Conditions are required")
    @Column(columnDefinition = "text")
    private String conditions;

    @NotNull(message = "Exercise risk is required")
    @Column(nullable = false)
    private Boolean exerciseRisk;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnore
    private User user;

}