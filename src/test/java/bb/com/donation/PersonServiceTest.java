package bb.com.donation;

import bb.com.donation.controller.PersonController;
import bb.com.donation.dto.person.PersonSaveDTO;
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
        Person newPerson = new Person( ).builder ().id ( 1L ).name ( "Igor" ).build ();
        PersonSaveDTO personSaveDTO = new PersonSaveDTO();
        personSaveDTO.setName (newPerson.getName ());
        personController.save(personSaveDTO);
        assertThat(personController.getById(1L).getBody().getName()).isEqualTo("Igor");
    }

    @Test
    void getPersonsTest() {

        Person newPerson = new Person( ).builder ().id ( 1L ).name ( "Igor" ).build ();

        personController.save(new PersonSaveDTO (newPerson.getName ()));
        assertThat(personController.getById(1L).getBody().getName())
                .isEqualTo(newPerson.getName());
    }

    @Test
    void updatePersonTest() {
        PersonSaveDTO personSaveDTO = PersonSaveDTO.builder ()
                .name ("Igor")
                .build ();
        Person oldPerson = personController.save (personSaveDTO).getBody ();
        personSaveDTO.setName ("Igor2");
        personController.save (personSaveDTO);
        Person newPerson = personController.getById (oldPerson.getId ()).getBody ();
        assertThat (newPerson.getName ()).isEqualTo (oldPerson.getName ());

    }

    @Test
    void deletePersonTest() {

        Person newPerson = new Person().builder().id(1L).name("Igor").build();
        Person savedPerson = personController.save( new PersonSaveDTO (newPerson.getName ())).getBody ();

        personController.delete(savedPerson.getId());
        assertThatThrownBy(() -> personController.getById(savedPerson.getId ()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Não foi encontrado nenhum usuário com o id "+ savedPerson.getId ());
    }

}
