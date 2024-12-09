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
@Table(name = "entrepreneurs", schema = "public")
public class Entrepreneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrepreneur_id")
    private Integer entrepreneurId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "profile_picture" , columnDefinition = "TEXT")
    private String profilePicture;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "phone_visibility", nullable = false)
    private Boolean phoneVisibility = true;

    //baska bir entity oldugu icin iliski kurmamiz gerekiyor bir enterpreterin bir useri olabilir gibi bir mantik var
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false) // `users` tablosundaki user_id ile ilişkilendirilir
    private User user;
}
