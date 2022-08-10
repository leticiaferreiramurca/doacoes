package bb.com.donation.controller;

import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.model.Donation;
import bb.com.donation.service.DonationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping()
    public List<Donation> getAll() {
        return donationService.getAll();
    }

    @GetMapping("/{id}")
    public Donation getById(@PathVariable Long id) {
        return donationService.getById(id);
    }

    @GetMapping("/{name}")
    public Donation getByName(@PathVariable String name) {
        return donationService.getByName(name);
    }

    @PostMapping()
    public Donation save(@RequestBody DonationSaveDTO donation) {
        return donationService.save(donation);
    }
}
