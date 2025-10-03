package com.bastianleond.testprevired.service.impl;

import com.bastianleond.testprevired.dto.request.AddAddressRequestDTO;
import com.bastianleond.testprevired.dto.request.CreatePersonRequestDTO;
import com.bastianleond.testprevired.dto.request.UpdateAddressRequestDTO;
import com.bastianleond.testprevired.dto.request.UpdatePersonRequestDTO;
import com.bastianleond.testprevired.dto.response.AddressDetailsDTO;
import com.bastianleond.testprevired.dto.response.PeopleSummaryDTO;
import com.bastianleond.testprevired.dto.response.PersonDetailsDTO;
import com.bastianleond.testprevired.entity.Address;
import com.bastianleond.testprevired.entity.Person;
import com.bastianleond.testprevired.exception.domain.AddressNotFound;
import com.bastianleond.testprevired.exception.domain.PersonAlreadyExists;
import com.bastianleond.testprevired.exception.domain.PersonAlreadyHasAddress;
import com.bastianleond.testprevired.exception.domain.PersonNotFound;
import com.bastianleond.testprevired.mapper.PersonMapper;
import com.bastianleond.testprevired.repository.AddressRepository;
import com.bastianleond.testprevired.repository.PersonRepository;
import com.bastianleond.testprevired.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public PersonDetailsDTO createPerson(CreatePersonRequestDTO request) {

        if (personRepository.findByRut(request.rut().toUpperCase()).isPresent()) {
            throw new PersonAlreadyExists("The person with rut " + request.rut() + " already exists");
        }
        Person personToCreate = personMapper.toPersonEntity(request);
        if (request.address() != null) {
            Address addressToAssign = personMapper.toAddressEntity(request.address());
            personToCreate.addAddress(addressToAssign);
        }
        personRepository.save(personToCreate);
        return personMapper.toPersonDetailsDTO(personToCreate);
    }

    @Override
    public List<PeopleSummaryDTO> getAll() {
        List<Person> people = personRepository.findAll();
        return people.stream().map(personMapper::toSummaryDto).toList();
    }

    @Override
    public PersonDetailsDTO findByUUID(UUID uuid) {
        return personMapper.toPersonDetailsDTO(getPersonByUUID(uuid));
    }

    @Override
    @Transactional
    public PersonDetailsDTO addAddress(AddAddressRequestDTO request) {
        Person person = getPersonByUUID(request.personUuid());
        if (person.getAddress() != null) {
            throw new PersonAlreadyHasAddress("This person has address, you can't add more than 1.");
        }
        Address address = personMapper.toAddAddressEntity(request);
        person.addAddress(address);

        Person updatedPerson = personRepository.save(person);
        return personMapper.toPersonDetailsDTO(updatedPerson);

    }

    @Override
    @Transactional
    public PersonDetailsDTO updatePerson(UpdatePersonRequestDTO request) {
        Person personToUpdate = getPersonByUUID(request.uuid());
        if (!Objects.equals(request.rut(), personToUpdate.getRut())) {
            Optional<Person> validatedPerson = personRepository.findByRut(request.rut());
            if (validatedPerson.isPresent()) {
                throw new PersonAlreadyExists("The person with rut " + request.rut() + " already exists");
            }
        }
        personToUpdate.setRut(request.rut());
        personToUpdate.setName(request.name());
        personToUpdate.setLastName(request.lastName());
        personToUpdate.setBirthDate(request.birthDate());
        Person updatedPerson = personRepository.save(personToUpdate);
        return personMapper.toPersonDetailsDTO(updatedPerson);
    }

    @Override
    @Transactional
    public AddressDetailsDTO updateAddress(UpdateAddressRequestDTO request) {
        Optional<Address> optionalAddress = addressRepository.findById(request.uuid());
        if (optionalAddress.isEmpty()) {
            throw new AddressNotFound("The address isn't in our records");
        }
        Address addressToUpdate = optionalAddress.get();
        addressToUpdate.setStreet(request.street());
        addressToUpdate.setCommune(request.commune());
        addressToUpdate.setRegion(request.region());
        Address updatedAddress = addressRepository.save(addressToUpdate);
        return personMapper.toAddressDetailsDTO(updatedAddress);
    }

    @Override
    @Transactional
    public void deletePerson(UUID uuid) {
        Person personToDelete = getPersonByUUID(uuid);
        personRepository.delete(personToDelete);
    }

    @Override
    @Transactional
    public void removeAddress(UUID uuid) {
        Optional<Address> searchedAddress = addressRepository.findById(uuid);
        if (searchedAddress.isEmpty()) {
            throw new AddressNotFound("The address isn't in our records");
        }
        Address addressToRemove = searchedAddress.get();
        addressRepository.delete(addressToRemove);
    }


    private Person getPersonByUUID(UUID uuid) {
        Optional<Person> searchedPerson = personRepository.findByUuid(uuid);
        if (searchedPerson.isEmpty()) {
            throw new PersonNotFound("Person not found");
        }
        return searchedPerson.get();
    }
}
