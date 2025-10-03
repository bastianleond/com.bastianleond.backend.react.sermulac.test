package com.bastianleond.testprevired.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PEOPLE")
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(unique = true, nullable = false, length = 12)
    private String rut;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false
    )
    private Address address;

    public void addAddress(Address address){
        if (address != null){
            this.setAddress(address);
            address.setPerson(this);
        }
    }


}
