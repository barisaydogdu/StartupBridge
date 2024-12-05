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
@Table(name = "InterestandValues",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class InterestandValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long interest_id;

    @Column(name = "investor_id")
    private Integer investor_id;

    @Column(name = "social_impact")
    private  String social_impact;

    @Column(name = "interest_area")
    private String interest_area;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}
