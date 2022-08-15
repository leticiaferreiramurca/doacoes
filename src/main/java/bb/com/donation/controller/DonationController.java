package bb.com.donation.controller;

import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.model.Donation;
import bb.com.donation.service.DonationService;
import bb.com.donation.service.impl.DonationServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/donation")
@Tag(name = "Doações", description = "Gerenciamento das doações")
public class DonationController {

    private final DonationServiceImp donationService;

    public DonationController(DonationServiceImp donationService) {
        this.donationService = donationService;
    }

    @GetMapping()
    @Operation(summary = "Get all donations")
    public ResponseEntity<Page<Donation>> list(@RequestParam("name") Optional<String> name, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getAllOrByName(name.orElse(""), pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get donation by id")
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
    @Operation(summary = "Save a donation")
    public Donation save(@RequestBody @Valid DonationSaveDTO donation) {
        return donationService.save(donation);
    }

    @PutMapping("next-status/{id}/{status}")
    @Operation(summary = "Change status of donation")
    public ResponseEntity<Donation> changeStatus(@PathVariable @Valid Long id, @PathVariable @Valid String status) {
                return ResponseEntity.ok (donationService.changeStatus (id, status));
    }

    @GetMapping("/status")
    @Operation(summary = "Get all donations by status")
    public ResponseEntity<Page<Donation>> getByStatus(@RequestParam("status") String status, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getAllOrByDonationStatus (status, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
