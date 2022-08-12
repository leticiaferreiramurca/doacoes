package bb.com.donation.controller;

import bb.com.donation.dto.donation.DonationSaveDTO;
import bb.com.donation.exceptions.ValidacaoException;
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
    public ResponseEntity<List<Donation>> getAll() {
        try {
            return ResponseEntity.ok (donationService.getAll ());
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getById(@PathVariable @Valid Long id) {
        try {
            return ResponseEntity.ok (donationService.getById (id));
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @GetMapping("/filtro/{name}")
    @Operation(summary = "Search for donations by name")
    public ResponseEntity<Page<Donation>> getByName(@PathVariable("name") @Valid String name, Pageable pageable) {

        try {
            return ResponseEntity.ok(donationService.getByName (name, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    public ResponseEntity<Donation> save(@RequestBody @Valid DonationSaveDTO donation) {
        try {
            Donation donationSaved = donationService.save (donation);
            return ResponseEntity.ok (donationSaved);
        }catch (ValidacaoException e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.BAD_REQUEST).build ();
        }catch (Exception e){
            log.error (e.getMessage ());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build ();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a donation")
    public void delete(@PathVariable @Valid Long id) {
        Donation donation = donationService.getById(id);
        if (donation == null) throw new ValidacaoException ("Donation not found");
        donationService.delete(id);
    }
}
