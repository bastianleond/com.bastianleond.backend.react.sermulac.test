package com.bastianleond.testprevired.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String commune;

    @Column(nullable = false)
    private String region;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personUuid", nullable = false)
    private Person person;


}
