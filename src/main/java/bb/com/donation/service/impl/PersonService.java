package bb.com.donation.service.impl;

import bb.com.donation.model.Person;
import bb.com.donation.repository.PersonRepository;
import bb.com.donation.service.ServiceInterface;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PersonService implements ServiceInterface {

    PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getUser() {
        return personRepository.findById(1L);

    }
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
