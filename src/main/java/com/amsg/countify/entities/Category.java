package com.amsg.countify.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "cat_id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id;

    @Column(nullable = false, unique = true)
    @Setter
    private String cat_name;

    @Column(nullable = false)
    @Setter
    private String cat_description;
}
