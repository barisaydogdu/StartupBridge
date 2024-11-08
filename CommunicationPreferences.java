package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "communication_preferences", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preference_id;

    @Column(name = "investor_id")
    private Integer investor_id;

    @Column(name = "meeting_preference")
    private String meeting_preference;

    @Column(name = "contact_channels")
    private String contact_channels;

    @PrePersist
    protected void onCreate() {
        
    }
}
