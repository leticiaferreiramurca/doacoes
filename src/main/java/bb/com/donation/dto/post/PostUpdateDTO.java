package bb.com.donation.dto.post;

import bb.com.donation.model.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class PostUpdateDTO implements PostGenericDTO {
    private Long id;
    private String name;
    private String description;
    private String urlImg;
    @Override
    public Post toPost() {
        return Post.builder()
                .id(id)
                .name(name)
                .description(description)
                .urlImg(urlImg)
                .build();
    }
}
