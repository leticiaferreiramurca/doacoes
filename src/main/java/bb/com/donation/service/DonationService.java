package bb.com.donation.service;

import bb.com.donation.model.Donation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonationService  {


    Donation save(Donation donation);

    Donation getById(Long id);

    List<Donation> getAll();
}
