package bb.com.donation.dto.donation;

import bb.com.donation.model.Donation;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DonationSaveDTO implements DonationGenericDTO {

    private Long id;
    private String name;
    private Long ownerId;
    private Long productId;

    public DonationSaveDTO(Donation donation) {
        this.id = donation.getId();
        this.name = donation.getName();
        this.ownerId = donation.getPersonOwner ().getId ();
        this.productId = donation.getProduct ().getId ();

    }


    public Donation toDonation() {

        Donation donation = new Donation ();
        donation.setId (this.id);
        donation.setName(name);
        donation.setPersonOwner (Person.builder ().id (ownerId).build ());
        donation.setProduct (Product.builder ().id (productId).build ());
        return donation;
    }
}
