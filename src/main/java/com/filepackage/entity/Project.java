package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrepreneur_id", insertable = false, updatable = false)
     private Entrepreneur entrepreneur;

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

   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entrepreneur_id", nullable = false)
    private Entrepreneur entrepreneur;
*/
  /*  @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // `users` tablosundaki user_id ile ilişkilendirilir
    private User user;*/

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    /*@ManyToOne
    @JoinColumn(name = "entrepreneur_id", nullable = false)
    private Entrepreneur entrepreneur; */
}

