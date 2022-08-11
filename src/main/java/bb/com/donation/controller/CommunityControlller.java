package bb.com.donation.controller;


import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.model.Person;
import bb.com.donation.service.CommunityService;
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
@RequestMapping("/community")
@RestController
public class CommunityControlller {

    private final CommunityService communityService;

    public CommunityControlller(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping()
    @Operation(summary = "List All Communities")
    public List<Community> list() {
        return communityService.getAll ();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Community by ID")
    public Community getById(@PathVariable @Valid Long id) {
        return communityService.getById (id);
    }

    @GetMapping("/filtro")
    @Operation(summary = "Get Community by Name")
    public ResponseEntity<Page<Community>> getByName(String name, Pageable pageable) {
        try {
            return ResponseEntity.ok(communityService.filtrar(name, pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping()
    @Operation(summary = "Save Community")
    public Community save(CommunitySaveDTO communitySaveDTO) {
        return communityService.save (communitySaveDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Community")
    public void delete(@PathVariable @Valid Long id) {
        communityService.delete (id);
    }

}
