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
public class ProjectDto {
    private Long project_id;

    private Integer entrepreneur_id;

    private  String project_name;

    private String short_description;

    private String target_sector;

    private String stage;

    private String budget_needed;

    private String revenue_model;

    private LocalDateTime created_at;
}
