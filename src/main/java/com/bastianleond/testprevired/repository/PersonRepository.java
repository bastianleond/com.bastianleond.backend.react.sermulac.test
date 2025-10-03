package com.bastianleond.testprevired.repository;

import com.bastianleond.testprevired.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByRut(String rut);
    Optional<Person> findByUuid(UUID uuid);
}
