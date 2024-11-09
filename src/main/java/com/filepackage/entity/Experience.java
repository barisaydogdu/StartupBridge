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
@Table(name = "experience",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experience_id")
    private Long experience_id;

    @Column(name = "enterpreneur_id")
    private Integer enterpreneur_id;

    @Column(name = "company_name")
    private  String company_name;

    @Column(name = "position")
    private String position;

    @Column(name = "description")
    private Long description;

    @Column(name = "duration_years")
    private LocalDateTime duration_years;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}