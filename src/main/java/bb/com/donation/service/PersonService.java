package bb.com.donation.service;

import bb.com.donation.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService extends GenericService<Person, Long> {
    Person update(Person person, Long id);

    List<Person> getByName(String name);
}
