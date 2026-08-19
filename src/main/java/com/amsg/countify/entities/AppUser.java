package com.amsg.countify.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Temporal;
import java.util.Date;
import java.util.List;

import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "user_id")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long user_id;

    @Column(nullable = false, unique = true)
    @Getter @Setter
    private String user_name;

    @Column(nullable = false)
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String encrypted_password;

    @Column(nullable = false)
    @Temporal
    @Getter @Setter
    private Date birth_date;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private String profile_picture;

    @OneToMany(mappedBy = "user")
    private List<SpendingLimit> spendinglimits;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

}
