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
@Table(name = "projects",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long project_id;

    @Column(name = "entrepreneur_id")
    private Integer entrepreneur_id;

    @Column(name = "project_name")
    private  String project_name;

    @Column(name = "short_description")
    private String short_description;

    @Column(name = "target_sector")
    private String target_sector;

    @Column(name = "stage")
    private String stage;

    @Column(name = "budget_needed")
    private String budget_needed;

    @Column(name = "revenue_model")
    private String revenue_model;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}

