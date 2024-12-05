package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin_actions", schema = "public")
public class AdminAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionId;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "action_type", nullable = false)
    private String actionType;

    @Column(name = "target_id")
    private Integer targetId;

    @Column(name = "action_description")
    private String actionDescription;

    @Column(name = "action_timestamp")
    private LocalDateTime actionTimestamp;

    @PrePersist
    protected void onCreate() {
        this.actionTimestamp = LocalDateTime.now();
    }
}
