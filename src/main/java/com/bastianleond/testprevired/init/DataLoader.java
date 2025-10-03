package com.bastianleond.testprevired.init;

import com.bastianleond.testprevired.entity.Address;
import com.bastianleond.testprevired.entity.Person;
import com.bastianleond.testprevired.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        Person person1 = new Person();
        person1.setName("Bastián");
        person1.setLastName("León");
        person1.setRut("18528708-4");
        person1.setBirthDate(LocalDate.of(1993, Month.JUNE, 4));

        Address address1 = new Address();
        address1.setStreet("Cuadro verde");
        address1.setCommune("Estacion Central");
        address1.setRegion("RM");

        person1.addAddress(address1);

        personRepository.save(person1);
        log.info("Data was saved");
    }
}
