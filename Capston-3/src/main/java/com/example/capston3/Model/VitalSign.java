package com.example.capston3.Model;

//import com.example.capston3.Enum.MeasurementContext;
//import com.example.capston3.Enum.VitalFlag;
//import com.example.capston3.Enum.VitalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vital_signs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @NotNull(message = "Type is required")
  //  @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private String type;

    // systolic, glucose, weight, waist or heart rate
    @NotNull(message = "Primary value is required")
    @Positive(message = "Primary value must be positive")
    private Double systolic;

    //  (blood pressure only)
    @Positive(message = "Secondary value must be positive")
    private Double diastolic;

    // set by the service based on type
    @Column(length = 10)
    private String unit;

    // glucose only
   // @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private String context;

    // calculated by the service
  //  @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private String flag;

    @NotNull(message = "Measured at is required")
    @PastOrPresent(message = "Measured at cannot be in the future")
    private LocalDateTime measuredAt;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}