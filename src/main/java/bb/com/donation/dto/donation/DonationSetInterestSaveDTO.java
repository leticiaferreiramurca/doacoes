package bb.com.donation.dto.donation;

import bb.com.donation.model.Donation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
public class DonationSetInterestSaveDTO {

    private Long id;
    private Long personId;



}
