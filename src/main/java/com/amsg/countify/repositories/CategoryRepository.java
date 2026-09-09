package com.amsg.countify.repositories;

import com.amsg.countify.entities.AppUser;
import com.amsg.countify.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Category c WHERE LOWER(c.name) = LOWER(:name) AND (c.user IS NULL OR c.user = :user)")
    Boolean existsByCatNameAndUser(@Param("name") String catName, @Param("user") AppUser user);

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:name) AND (c.user IS NULL OR c.user = :user)")
    Optional<Category> findByCatNameAndUser (@Param("name")String catName, @Param("user") AppUser user);

    @Query("SELECT c FROM Category c WHERE c.user IS NULL OR c.user = :user")
    List<Category> findAllAvailableForUser(@Param("user") AppUser user);

}
