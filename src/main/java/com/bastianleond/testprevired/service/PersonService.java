package com.bastianleond.testprevired.service;

import com.bastianleond.testprevired.dto.request.*;
import com.bastianleond.testprevired.dto.response.AddressDetailsDTO;
import com.bastianleond.testprevired.dto.response.PeopleSummaryDTO;
import com.bastianleond.testprevired.dto.response.PersonDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    PersonDetailsDTO createPerson(CreatePersonRequestDTO request);

    List<PeopleSummaryDTO> getAll();

    PersonDetailsDTO findByUUID(UUID uuid);

    PersonDetailsDTO addAddress(AddAddressRequestDTO request);

    PersonDetailsDTO updatePerson(UpdatePersonRequestDTO request);

    AddressDetailsDTO updateAddress(UpdateAddressRequestDTO request);

    void deletePerson(UUID uuid);

    void removeAddress(UUID uuid);
}
