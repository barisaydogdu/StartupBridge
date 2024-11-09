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
public class AdminActionsDto {
    private Long action_id;

    private Integer admin_id;

    private String action_type;

    private Integer target_id;

    private String action_description;

    private LocalDateTime action_timestamp;
}
