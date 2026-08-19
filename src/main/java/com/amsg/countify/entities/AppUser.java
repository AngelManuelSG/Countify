package com.amsg.countify.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long userId;

    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String userName;

    @Column(nullable = false)
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String encryptedPassword;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDate birthDate;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String profilePicture;

    @OneToMany(mappedBy = "user")
    private List<SpendingLimit> spendingLimits;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

}
