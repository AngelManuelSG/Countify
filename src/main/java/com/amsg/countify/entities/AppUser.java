package com.amsg.countify.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
public class AppUser implements UserDetails {
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

    // -------------------------------------------------------------------
    // Métodos implementados de la interfaz UserDetails
    // -------------------------------------------------------------------

    /**
     * Public method used for obtaining user roles, permits or privileges
     * @return Collection of privileges
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Now it returns an empty list because we consider all user equals.
        return List.of();
    }


    @Override
    public @Nullable String getPassword() {
        return this.encryptedPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    // The following methods are simplified. They always returns true because we are not controlling if the account
    // or credentials are expired, locked, or enabled. In case we want to manage it, we should include four attributes
    // on the DB with this information.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
