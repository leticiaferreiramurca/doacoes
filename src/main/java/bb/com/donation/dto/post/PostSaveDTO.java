package bb.com.donation.dto.post;

import bb.com.donation.model.Post;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostSaveDTO implements PostGenericDTO{
    private String name;
    private String description;

    private Long productId;

    private String urlImg;

    public Post toPost() {
        Post post = new Post();
        post.setName(name);
        post.setDescription (description);
        return post;
    }
}
