package bb.com.donation.controller;

import bb.com.donation.dto.donation.DonationSaveDTO;
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
@Tag(name = "Donations", description = "Manegement of donations")
public class DonationController {

    private final DonationServiceImp donationService;

    public DonationController(DonationServiceImp donationService) {
        this.donationService = donationService;
    }

    @GetMapping()
    @Operation(summary = "Get all donations", description = "Get all donations", tags = {"Donations"})
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
    @Operation(summary = "Get donation by id", description = "Get donation by id", tags = {"Donations"})
    public Donation getById(@PathVariable @Valid Long id) {
        return donationService.getById(id);
    }

    @GetMapping("/filtro")
    @Operation(summary = "Search for donations by name", description = "Search for donations by name", tags = {"Donations"})
    public ResponseEntity<Page<Donation>> getByName(String name, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getByName (name, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    @Operation(summary = "Save a donation", description = "Save a donation", tags = {"Donations"})
    public Donation save(@RequestBody @Valid DonationSaveDTO donation) {
        return donationService.save(donation);
    }

    @PutMapping("next-status/{idDonation}/{status}" )
    @Operation(summary = "Change status of donation", description = "Change status of donation", tags = {"Donations"})
    public ResponseEntity<Donation> changeStatus(@PathVariable() @Valid Long id, @PathVariable @Valid String status) {
                return ResponseEntity.ok (donationService.changeStatus (id, status));
    }

    @GetMapping("/status")
    @Operation(summary = "Get all donations by status", description = "Get all donations by status", tags = {"Donations"})
    public ResponseEntity<Page<Donation>> getByStatus(@RequestParam(required = false) Optional<String> status, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getAllOrByDonationStatus (status.orElse (""), pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/set-interest/")
    @Operation(summary = "Set interest of donation", description = "Set interest of donation", tags = {"Donations"})
    public ResponseEntity<Donation> setInterest(@RequestParam @Valid Long id, @RequestParam @Valid Long personId) {
        try {
            Donation donation = donationService.setInterest (id, personId);
            if(donation.getPersonOwner ().getId ().equals (personId)) {
                return ResponseEntity.status (HttpStatus.FORBIDDEN).build ();
            }
            return ResponseEntity.ok (donation);
        }catch (ValidacaoException e){
            return ResponseEntity.status (HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/remove-interest/")
    @Operation(summary = "Remove interest of donation", description = "Remove interest of donation", tags = {"Donations"})
    public ResponseEntity<Donation> removeInterest(@RequestParam @Valid Long id, @RequestParam @Valid Long personId) {
        try {
            return ResponseEntity.ok (donationService.removeInterest (id, personId));
        }catch (ValidacaoException e){
            return ResponseEntity.status (HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}


