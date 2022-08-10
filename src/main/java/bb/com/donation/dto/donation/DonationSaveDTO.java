package bb.com.donation.dto.donation;

import bb.com.donation.dto.product.ProductSaveDTO;
import bb.com.donation.model.Donation;
import bb.com.donation.model.Person;
import bb.com.donation.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DonationSaveDTO implements DonationGenericDTO {

    private String name;
    private Set<Long> requerimentIds;
    private Long ownerId;
    private Set<Map<ProductSaveDTO, Integer>> products = new HashSet<>();


    public Donation toDonation() {
        Donation donation = new Donation ();
        donation.setId (null);
        donation.setName(name);
        donation.setPersonOwner (Person.builder ().id (ownerId).build ());

        return donation;
    }
}
