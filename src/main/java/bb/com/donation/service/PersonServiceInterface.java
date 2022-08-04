package bb.com.donation.service;

import bb.com.donation.model.Person;
import org.springframework.stereotype.Service;

@Service
public interface PersonServiceInterface {
    Person getById(Long id);
}
