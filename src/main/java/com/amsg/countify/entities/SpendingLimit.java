package com.amsg.countify.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "limitId")
public class SpendingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long limitId;

    @Column(nullable = false)
    @Setter
    private LocalDate initDate;

    @Column(nullable = false)
    @Setter
    private LocalDate deadline;

    @Column(nullable = false)
    @Setter
    private BigDecimal limitQuantity;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private AppUser user;
}
