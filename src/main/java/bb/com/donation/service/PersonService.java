package bb.com.donation.service;

import bb.com.donation.model.Person;
import bb.com.donation.repository.PersonRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonService implements PersonServiceInterface {

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
}
