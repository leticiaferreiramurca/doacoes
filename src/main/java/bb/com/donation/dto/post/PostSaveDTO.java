package bb.com.donation.dto.post;

import bb.com.donation.model.Donation;
import bb.com.donation.model.Post;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostSaveDTO implements PostGenericDTO{
    private String name;
    private String description;

    private String urlImg;

    private Long idDonation;

    public Post toPost() {
        Post post = new Post();
        post.setName(name);
        post.setDescription (description);
        post.setUrlImg(urlImg);
        Donation donation = new Donation();
        donation.setId(idDonation);
        post.setDonation(donation);

        return post;
    }
}
