package bb.com.donation;

import bb.com.donation.controller.PersonController;
import bb.com.donation.dto.PersonDTO;
import bb.com.donation.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonController personRepository;
    @Autowired
    private PersonController personController;

    @Test
    void savePersonTest() {
        Person newPerson = new Person( 1L,"Igor");
        PersonDTO personDTO = new PersonDTO(newPerson.getId (), newPerson.getName ());
        personController.save(personDTO);
        assertThat(personController.getById(1L).getBody().getName()).isEqualTo("Igor");
    }

    @Test
    void getPersonsTest() {

        Person newPerson = new Person( 1L,"Igor");
        PersonDTO personDTO = new PersonDTO(newPerson.getId (), newPerson.getName ());
        personController.save(personDTO);
        assertThat(personController.getById(1L).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void updatePersonTest() {
        Person oldPerson = new Person( 3L,"Igor");
        PersonDTO oldPersonDTO = new PersonDTO(oldPerson.getId (), oldPerson.getName ());
        personRepository.save(oldPersonDTO);
        Person newPerson = new Person(3L, "Igor Rhamon");
        PersonDTO newPersonDTO = new PersonDTO(newPerson.getId (), newPerson.getName ());
        personController.save(newPersonDTO);
        assertThat(personController.getById(newPerson.getId()).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void deletePersonTest() {

        Person newPerson = new Person(2L, "Igor");
        PersonDTO personDTO = new PersonDTO(newPerson.getId (), newPerson.getName ());
        personController.save(personDTO);
        personController.delete(1L);
        try {
            personController.getById(1L);
        } catch (Exception e) {
            assertThatThrownBy(() -> personController.getById(1L))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Person not found");
        }
    }

}
