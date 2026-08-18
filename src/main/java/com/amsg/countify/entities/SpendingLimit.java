package com.amsg.countify.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Temporal;

import java.util.Date;

@Entity
@Getter
public class SpendingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long limit_id;

    @Temporal
    @Column(nullable = false)
    @Setter
    private Date init_date;

    @Temporal
    @Column(nullable = false)
    @Setter
    private Date deadline;

    @Column(nullable = false)
    @Setter
    private Integer limit_quantity;
}
