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
@Table(name = "admin_actions", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class AdminAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long action_id;

    @Column(name = "admin_id")
    private Integer admin_id;

    @Column(name = "action_type")
    private String action_type;

    @Column(name = "target_id")
    private Integer target_id;

    @Column(name = "action_description")
    private String action_description;

    @Column(name = "action_timestamp")
    private LocalDateTime action_timestamp;

    @PrePersist
    protected void onCreate() {
        if (this.action_timestamp == null) {
            this.action_timestamp = LocalDateTime.now();
        }
    }
}
