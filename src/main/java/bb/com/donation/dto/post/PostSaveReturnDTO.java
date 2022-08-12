package bb.com.donation.dto.post;

import bb.com.donation.model.Post;
import bb.com.donation.model.Product;

public class PostSaveReturnDTO implements PostGenericDTO{
    private String name;
    private String description;
    private Long productId;
    private String urlImg;

    public PostSaveReturnDTO(Post post) {
        this.name = post.getName();
        this.description = post.getDescription();
        this.productId = post.getProduct().getId();
        this.urlImg = post.getUrlImg();
    }

    @Override
    public Post toPost() {

        Product product = new Product();
        product.setId(productId);
        Post post = new Post();
        post.setName(name);
        post.setDescription(description);
        post.setProduct(product);
        post.setUrlImg(urlImg);
        return post;

    }
}
