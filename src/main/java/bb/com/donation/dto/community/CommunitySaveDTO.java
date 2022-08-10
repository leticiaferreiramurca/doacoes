package bb.com.donation.dto.community;

import bb.com.donation.model.Community;
import bb.com.donation.model.Person;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class CommunitySaveDTO implements CommunityGenericDTO {
    private String name;
    private Long idPerson;




    @Override
    public Community toCommunity() {
        return Community.builder()
                .id(null)
                .name(name)
                .personOwner (
                    Person.builder()
                            .id(idPerson)
                            .build()
                )
                .build();
    }
}
