package bb.com.donation;

import bb.com.donation.controller.PersonController;
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
    private PersonController personService;

    @Test
    void savePersonTest() {
        Person newPerson = new Person( 1L,"Igor");
        personService.save(newPerson);
        assertThat(personService.getById(1L).getBody().getName()).isEqualTo("Igor");
    }

    @Test
    void getPersonsTest() {

        Person newPerson = new Person( 1L,"Igor");
        personRepository.save(newPerson);
        assertThat(personService.getById(1L).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void updatePersonTest() {
        Person oldPerson = new Person( 3L,"Igor");
        personRepository.save(oldPerson);
        Person newPerson = new Person(3L, "Igor Rhamon");
        personService.save(newPerson);
        assertThat(personService.getById(newPerson.getId()).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void deletePersonTest() {

        Person newPerson = new Person(2L, "Igor");
        personRepository.save(newPerson);

        personService.delete(1L);
        try {
            personService.getById(1L);
        } catch (Exception e) {
            assertThatThrownBy(() -> personService.getById(1L))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Person not found");
        }
    }

}
