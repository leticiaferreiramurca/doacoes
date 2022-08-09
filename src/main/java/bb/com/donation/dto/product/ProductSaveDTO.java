package bb.com.donation.dto.product;

import bb.com.donation.model.Community;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class ProductSaveDTO implements ProductGenericDTO {
    private String name;
    private Long owner;
    private Boolean isValid;
    private Long idCommunity;

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.getName());
        Person person = new Person ();
        person.setId(this.getOwner());
        product.setPerson (person);
        product.setIsValid (this.getIsValid() );
        Community community = new Community ();
        community.setId(this.getIdCommunity ());
        product.setCommunity (community);



        return product;


    }

}
