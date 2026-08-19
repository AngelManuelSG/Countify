package com.amsg.countify.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "transactionId")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private TransactionEnum type;

    @Column(nullable = false)
    @Setter
    private LocalDate transactionDate;

    @Column(nullable = false)
    @Setter
    private BigDecimal quantity;

    @Column(nullable = false)
    @Setter
    private String description;

    @Setter
    private String largeDescription;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category category;

}
