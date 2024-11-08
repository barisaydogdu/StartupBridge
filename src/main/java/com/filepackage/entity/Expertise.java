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
@Table(name = "expertise",schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Expertise {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "expertise_id")
        private Long expertise_id;

        @Column(name = "entrepreneur_id")
        private Integer entrepreneur_id;

        @Column(name = "skill_name")
        private  String skill_name;

}
