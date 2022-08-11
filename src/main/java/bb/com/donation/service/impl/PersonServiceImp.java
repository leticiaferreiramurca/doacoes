package bb.com.donation.service.impl;

import bb.com.donation.dto.person.PersonGenericDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Person;
import bb.com.donation.repository.PersonRepository;
import bb.com.donation.service.PersonService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    PersonRepository personRepository;

    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }



    @Override
    public Page<Person> filtrar(String filtro, Pageable pageable) {
        final Person personFiltro = new Person();
        personFiltro.setName(filtro);

        final ExampleMatcher exampleMatcher =
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        final Example<Person> personExample = Example.of(personFiltro, exampleMatcher);

        return personRepository.findAll(personExample, pageable);
    }


    @Override
    public Person save(PersonGenericDTO personGenericDTO) {
        return personRepository.save(personGenericDTO.toPerson());
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

    public Person findById(Long idPerson) {
        return personRepository.findById(idPerson).orElseThrow (() -> new ValidacaoException("Não foi encontrado nenhum usuário com o id " + idPerson));
    }
}
