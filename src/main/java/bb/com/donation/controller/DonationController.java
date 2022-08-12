package bb.com.donation.controller;

import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.model.Donation;
import bb.com.donation.service.DonationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Donation getById(@PathVariable @Valid Long id) {
        return donationService.getById(id);
    }

    @GetMapping("/filtro")
    @Operation(summary = "Search for donations by name")
    public ResponseEntity<Page<Donation>> getByName(String name, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getByName (name, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public Donation save(@RequestBody @Valid DonationSaveDTO donation) {
        return donationService.save(donation);
    }
}
