package bb.com.donation.controller;

import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.dto.donation.DonationSetInterestSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
import bb.com.donation.model.Donation;
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
    public ResponseEntity<Page<Donation>> list(@RequestParam(value = "name", required = false) Optional<String> name, Pageable pageable) {

        try {
            Page<Donation> donations = donationService.getAllOrByName(name.orElse (""), pageable);
            return ResponseEntity.ok(donations);
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
    public ResponseEntity<Page<Donation>> getByStatus(@RequestParam(required = false) Optional<String> status, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getAllOrByDonationStatus (status.orElse (""), pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/set-interest/")
    @Operation(summary = "Set interest of donation")
    public ResponseEntity<Donation> setInterest(@RequestParam @Valid Long id, @RequestParam @Valid Long personId) {
        try {
            return ResponseEntity.ok (donationService.setInterest (id, personId));
        }catch (ValidacaoException e){
            return ResponseEntity.status (HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}


