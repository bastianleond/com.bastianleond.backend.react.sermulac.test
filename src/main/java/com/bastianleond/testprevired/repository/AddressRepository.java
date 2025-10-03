package com.bastianleond.testprevired.repository;

import com.bastianleond.testprevired.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
