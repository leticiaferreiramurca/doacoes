package bb.com.donation.service;

import bb.com.donation.dto.person.PersonGenericDTO;
import bb.com.donation.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PersonService extends GenericService<Person, Long, PersonGenericDTO> {
    Page<Person> filtrar(String filtro, Pageable pageable);
}
