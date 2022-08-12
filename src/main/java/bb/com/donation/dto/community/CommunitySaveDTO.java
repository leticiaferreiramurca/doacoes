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
    private String description;

    @Override
    public Community toCommunity() {
        return Community.builder()
                .id(null)
                .name(name)
                .description(description)
                .build();
    }
}
