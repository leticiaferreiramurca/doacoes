package bb.com.donation.service;

import bb.com.donation.dto.person.PersonGenericDTO;
import bb.com.donation.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService extends GenericService<Person, Long, PersonGenericDTO> {


    List<Person> getByName(String name);
}
