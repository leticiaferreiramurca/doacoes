package bb.com.donation.controller;


import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.service.CommunityService;
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
@RequestMapping("/community")
@RestController
@Tag(name = "Comunidades", description = "Comunidades para seguir/registar produto de acordo com o interesse de cada pessoa.")
public class CommunityControlller {

    private final CommunityService communityService;



    public CommunityControlller(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping()
    @Operation(summary = "List All Communities", tags = {"Comunidades"})
    public ResponseEntity<Page<Community>> list(@RequestParam("name") Optional<String> name, Pageable pageable) {
        try {
            return ResponseEntity.ok(communityService.getAllOrByName(name.orElse(""), pageable));
        } catch (Exception e){
            log.error (e.getMessage());
            return ResponseEntity.status (HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Community by ID")
    public Community getById(@PathVariable @Valid Long id) {
        return communityService.getById (id);
    }

    @PostMapping()
    @Operation(summary = "Save Community", tags = {"Comunidades"})
    public ResponseEntity<Community> save(@RequestBody @Valid CommunitySaveDTO communitySaveDTO) {
        Community community = communityService.save (communitySaveDTO);
        return ResponseEntity.ok (community);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Community", tags = {"Comunidades"})
    public void delete(@PathVariable @Valid Long id) {
        communityService.delete (id);
    }

}
