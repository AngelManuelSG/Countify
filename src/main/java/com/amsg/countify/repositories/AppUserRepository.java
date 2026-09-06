package com.amsg.countify.repositories;

import com.amsg.countify.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String username);
    Boolean existsByUserName(String username);
}
