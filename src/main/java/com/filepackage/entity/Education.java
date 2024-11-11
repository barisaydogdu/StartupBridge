package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "education",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long education_id;

    @Column(name = "entrepreneur_id")
    private Integer entrepreneur_id;

    @Column(name = "school_name")
    private  String school_name;

    @Column(name = "degree")
    private String degree;

    @Column(name = "graduation_year")
    private Integer graduation_year;


    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}

