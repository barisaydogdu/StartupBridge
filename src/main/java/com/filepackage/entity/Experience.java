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

    @Column(name = "entrepreneur_id")
    private Integer entrepreneur_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrepreneur_id", insertable = false, updatable = false)
    private Entrepreneur entrepreneur;

    @Column(name = "company_name")
    private  String company_name;

    @Column(name = "position")
    private String position;

    @Column(name = "description")
    private String description;

    @Column(name = "duration_years")
    private Integer duration_years;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }


}