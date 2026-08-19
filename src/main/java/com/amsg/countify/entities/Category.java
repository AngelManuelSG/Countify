package com.amsg.countify.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "catId")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;

    @Column(nullable = false, unique = true)
    @Setter
    private String catName;

    @Column(nullable = false)
    @Setter
    private String catDescription;

    @OneToMany(mappedBy = "category")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private AppUser user;
}
