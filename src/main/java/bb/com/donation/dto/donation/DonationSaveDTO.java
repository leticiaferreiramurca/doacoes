package bb.com.donation.dto.donation;

import bb.com.donation.model.Donation;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DonationSaveDTO implements DonationGenericDTO {

    private String name;
    private List<Long> requerimentIds;
    private Long ownerId;
    private Long productId;

    public DonationSaveDTO(Donation donation) {
        this.name = donation.getName();
        this.ownerId = donation.getPersonOwner ().getId ();
        this.productId = donation.getProduct ().getId ();

    }


    public Donation toDonation() {
        Donation donation = new Donation ();
        donation.setId (null);
        donation.setName(name);
        donation.setPersonOwner (Person.builder ().id (ownerId).build ());
        donation.setProduct (Product.builder ().id (productId).build ());
        return donation;
    }
}
