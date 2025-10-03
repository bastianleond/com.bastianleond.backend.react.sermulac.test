package com.bastianleond.testprevired.controller;

import com.bastianleond.testprevired.dto.request.AddAddressRequestDTO;
import com.bastianleond.testprevired.dto.request.CreatePersonRequestDTO;
import com.bastianleond.testprevired.dto.request.UpdateAddressRequestDTO;
import com.bastianleond.testprevired.dto.request.UpdatePersonRequestDTO;
import com.bastianleond.testprevired.dto.response.AddressDetailsDTO;
import com.bastianleond.testprevired.dto.response.PeopleSummaryDTO;
import com.bastianleond.testprevired.dto.response.PersonDetailsDTO;
import com.bastianleond.testprevired.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/people")
@CrossOrigin(origins = "*")
public class PersonController {

    private final PersonService personService;


    @Operation(
            summary = "Create a new person record",
            description = "You could save a new person record on H2 database.",
            tags = {"People"}
    )
    @PostMapping("")
    public ResponseEntity<PersonDetailsDTO> createPerson(
            @Valid @RequestBody CreatePersonRequestDTO request
    ) {
        return ResponseEntity.ok(personService.createPerson(request));
    }

    @Operation(
            summary = "Get all persons",
            description = "Get all the records of people, they don't have the Address entity",
            tags = {"People"}
    )
    @GetMapping("")
    public ResponseEntity<List<PeopleSummaryDTO>> getAll() {
        return ResponseEntity.ok(personService.getAll());
    }

    @Operation(
            summary = "Get a specific person",
            description = "Get person by uuid",
            tags = {"People"}
    )
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<PersonDetailsDTO> findByUUID(
            @PathVariable UUID uuid
    ) {
        return ResponseEntity.ok(personService.findByUUID(uuid));
    }


    @Operation(
            summary = "Add Address",
            description = "You can add the missing address to the person.",
            tags = {"People"}
    )
    @PostMapping("/address/add")
    public ResponseEntity<Object> addAddressToUser(
            @Valid @RequestBody AddAddressRequestDTO request
    ) {
        return ResponseEntity.ok(personService.addAddress(request));
    }


    @Operation(
            summary = "Update Person",
            description = "Update only the person attributes",
            tags = {"People"}
    )
    @PutMapping("")
    public ResponseEntity<PersonDetailsDTO> updatePerson(
            @Valid @RequestBody UpdatePersonRequestDTO request
    ) {
        return ResponseEntity.ok(personService.updatePerson(request));
    }

    @Operation(
            summary = "Update Address",
            description = "Update only the address attributes",
            tags = {"People"}
    )
    @PutMapping("/address")
    public ResponseEntity<AddressDetailsDTO> updateAddress(
            @Valid @RequestBody UpdateAddressRequestDTO request
    ) {
        return ResponseEntity.ok(personService.updateAddress(request));
    }

    @Operation(
            summary = "Delete Person",
            description = "Remove Person from the database",
            tags = {"People"}
    )
    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable UUID uuid
    ) {
        personService.deletePerson(uuid);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Delete Address",
            description = "Remove Address from the database",
            tags = {"People"}
    )
    @DeleteMapping("/address/uuid/{uuid}")
    public ResponseEntity<Void> removeAddress(
            @PathVariable UUID uuid
    ) {
        personService.removeAddress(uuid);
        return ResponseEntity.ok().build();
    }


}
