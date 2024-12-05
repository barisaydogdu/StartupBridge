package com.filepackage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {

    private Long educationId;
    private Integer entrepreneurId;
    private String schoolName;
    private String degree;
    private Integer graduationYear;
}
