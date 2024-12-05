package com.filepackage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "communication_preference", schema = "public")
public class CommunicationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Long preferenceId;

    @Column(name = "investor_id", nullable = false)
    private Integer investorId;

    @Column(name = "meeting_preference", length = 50)
    private String meetingPreference;

    @Column(name = "contact_channels")
    private String contactChannels;
}
