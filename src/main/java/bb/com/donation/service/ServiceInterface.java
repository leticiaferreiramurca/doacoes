package bb.com.donation.service;

import bb.com.donation.model.Person;
import org.springframework.stereotype.Service;

@Service
public interface ServiceInterface {
    Person getById(Long id);

    void delete(Long id);
}
