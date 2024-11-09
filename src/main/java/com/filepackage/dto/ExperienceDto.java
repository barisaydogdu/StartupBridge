package com.filepackage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto {
    private Long experience_id;

    private Integer entrepreneur_id;

    private  String company_name;

    private String position;

    private String description;

    private LocalDateTime duration_years;

    private LocalDateTime created_at;
}
