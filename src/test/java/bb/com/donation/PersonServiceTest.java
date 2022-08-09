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
    private PersonController personController;

    @Test
    void savePersonTest() {
        Person newPerson = new Person( 1L,"Igor");
        personController.save(newPerson);
        assertThat(personController.getById(1L).getBody().getName()).isEqualTo("Igor");
    }

    @Test
    void getPersonsTest() {

        Person newPerson = new Person( 1L,"Igor");
        personController.save(newPerson);
        assertThat(personController.getById(1L).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void updatePersonTest() {
        Person oldPerson = new Person( 3L,"Igor");
        personRepository.save(oldPerson);
        Person newPerson = new Person(3L, "Igor Rhamon");
        personController.save(newPerson);
        assertThat(personController.getById(newPerson.getId()).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void deletePersonTest() {

        Person newPerson = new Person(null, "Igor");
        Person savePerson = personController.save(newPerson).getBody ();
        Long id = savePerson.getId();
        personController.delete(savePerson.getId());
        try {
            personController.getById(savePerson.getId());
        } catch (Exception e) {
            assertThatThrownBy(() -> personController.getById(id))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("Não foi encontrado nenhum usuário com o id "+ 1L);
        }
    }

}
