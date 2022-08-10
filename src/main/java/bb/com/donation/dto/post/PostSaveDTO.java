package bb.com.donation.dto.post;

import bb.com.donation.model.Post;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostSaveDTO implements PostGenericDTO{
    private String name;
    private String description;

    public Post toPost() {
        Post post = new Post();
        post.setName(name);
        post.setDescription (description);
        return post;
    }
}
