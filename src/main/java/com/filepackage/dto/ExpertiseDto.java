package com.filepackage.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpertiseDto {

    private Long expertise_id;

    private Integer entrepreneur_id;
    private EntrepreneurDto entrepreneur; // Entrepreneur bilgisi


    private  String skill_name;
}
