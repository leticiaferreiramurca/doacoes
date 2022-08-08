package bb.com.donation.dto;

import bb.com.donation.model.Person;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonDTO {
    Long id;
    String name;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person toPerson() {
        return new Person(id, name);
    }
}
