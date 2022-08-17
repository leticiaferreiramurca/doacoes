package bb.com.donation.dto.person;

import bb.com.donation.model.Person;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private final Long id;
    private final String name;

    public Person toPerson() {
        return Person.builder()
                .id(id)
                .name(name)
                .build();
    }
}
