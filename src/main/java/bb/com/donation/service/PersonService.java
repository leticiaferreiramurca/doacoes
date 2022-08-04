package bb.com.donation.service;

import bb.com.donation.model.Person;
import bb.com.donation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Optional<Person> getUser() {
        return personRepository.findById(1L);

    }
}
