package bb.com.donation.dto.person;

import bb.com.donation.model.Person;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PersonSaveDTO implements PersonGenericDTO{
    private String name;
    @Override
    public Person toPerson() {

        return Person.builder()
                .id(null)
                .name(name)
                .build();
    }
}
