package bb.com.donation.service.impl;

import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Person;
import bb.com.donation.repository.PersonRepository;
import bb.com.donation.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    PersonRepository personRepository;

    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person update(Person person, Long id) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getByName(String name) {
        return personRepository.findByName (name)
                .orElseThrow(() -> new ValidacaoException("ID nao encontrado"));
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getById(Long aLong) {
        return personRepository.findById(aLong).orElseThrow (() -> new ValidacaoException("Não foi encontrado nenhum usuário com o id " + aLong));
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        personRepository.deleteById(aLong);
    }
}
