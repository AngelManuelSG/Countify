package com.amsg.countify.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Temporal;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "transaction_id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private TransactionEnum type;

    @Column(nullable = false)
    @Setter
    @Temporal
    private Date transaction_date;

    @Column(nullable = false)
    @Setter
    private Integer quantity;

    @Column(nullable = false)
    @Setter
    private String description;

    @Setter
    private String large_description;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Category category;

}
