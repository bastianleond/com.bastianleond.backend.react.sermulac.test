package com.bastianleond.testprevired.mapper;

import com.bastianleond.testprevired.dto.request.AddAddressRequestDTO;
import com.bastianleond.testprevired.dto.request.CreateAddressRequestDTO;
import com.bastianleond.testprevired.dto.request.CreatePersonRequestDTO;
import com.bastianleond.testprevired.dto.response.AddressDetailsDTO;
import com.bastianleond.testprevired.dto.response.PeopleSummaryDTO;
import com.bastianleond.testprevired.dto.response.PersonDetailsDTO;
import com.bastianleond.testprevired.entity.Address;
import com.bastianleond.testprevired.entity.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class PersonMapper {

    public Person toPersonEntity(CreatePersonRequestDTO request) {
        return Person.builder()
                .rut(request.rut().toUpperCase())
                .name(request.name())
                .lastName(request.lastName())
                .birthDate(request.birthDate())
                .build();
    }

    public Address toAddressEntity(CreateAddressRequestDTO request) {
        if (request == null) {
            return null;
        }
        return Address.builder()
                .street(request.street())
                .commune(request.commune())
                .region(request.region())
                .build();
    }

    public Address toAddAddressEntity(AddAddressRequestDTO request) {
        if (request == null) {
            return null;
        }
        return Address.builder()
                .street(request.street())
                .commune(request.commune())
                .region(request.region())
                .build();
    }


    public PeopleSummaryDTO toSummaryDto(Person person) {
        return PeopleSummaryDTO.builder()
                .uuid(person.getUuid())
                .rut(person.getRut())
                .name(person.getName())
                .lastName(person.getLastName())
                .birthDate(person.getBirthDate())
                .age(calculateAgeOfByBirthDate(person.getBirthDate()))
                .build();
    }

    public PersonDetailsDTO toPersonDetailsDTO(Person person) {
        return PersonDetailsDTO.builder()
                .uuid(person.getUuid())
                .rut(person.getRut())
                .name(person.getName())
                .lastName(person.getLastName())
                .birthDate(person.getBirthDate())
                .age(calculateAgeOfByBirthDate(person.getBirthDate()))
                .address(toAddressDetailsDTO(person.getAddress()))
                .build();
    }

    public AddressDetailsDTO toAddressDetailsDTO(Address address) {
        if (address == null){
            return null;
        }
        return AddressDetailsDTO.builder()
                .uuid(address.getUuid())
                .street(address.getStreet())
                .commune(address.getCommune())
                .region(address.getRegion())
                .build();
    }

    private int calculateAgeOfByBirthDate(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
