package com.example.capston3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NutritionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private String items;

    @NotEmpty
    @Column(columnDefinition = "text")
    private String summary;

    @NotEmpty
    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne
    @JsonIgnore
    private User user;
}