package bb.com.donation;

import bb.com.donation.model.Person;
import bb.com.donation.repository.PersonRepository;
import bb.com.donation.service.impl.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonPersonServiceTest {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	PersonService personService ;

	@Test
	void getPersonsTest() {

		Person newPerson = new Person( 1L,"Igor");
		personRepository.save(newPerson);
		assertThat(personService.getById(1L).getName()).isEqualTo("Igor");
	}

	@Test
	void savePersonTest() {
		Person newPerson = new Person( 1L,"Igor");
		personService.save(newPerson);
		assertThat(personService.getById(1L).getName()).isEqualTo("Igor");
	}


	@Test
	void getById() {
		Person newPerson = new Person( 1L,"Igor");
		personRepository.save(newPerson);
		assertThat(personService.getById(1L).getName()).isEqualTo("Igor");
	}

	@Test
	void getAllPersons() {
		Person newPerson = new Person( 1L,"Igor");
		personRepository.save(newPerson);
		assertThat(personService.getAllPersons()).hasSize(1);

	}
}
