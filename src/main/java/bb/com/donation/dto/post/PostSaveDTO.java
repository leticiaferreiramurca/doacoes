package bb.com.donation.dto.post;

import bb.com.donation.model.Person;
import bb.com.donation.model.Post;
import bb.com.donation.model.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostSaveDTO implements PostGenericDTO{
    private String name;
    private String description;

    private String urlImg;

    private Long idProduct;

    public Post toPost() {
        Post post = new Post();
        post.setName(name);
        post.setDescription (description);
        post.setUrlImg(urlImg);
        Product product = new Product ();
        product.setId(idProduct);
        post.setProduct(product);

        return post;
    }
}
