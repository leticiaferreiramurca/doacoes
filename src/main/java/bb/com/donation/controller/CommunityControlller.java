package bb.com.donation.controller;


import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public List<Community> list() {
        return communityService.getAll ();
    }

    @GetMapping("/{id}")
    public Community getById(java.lang.Long id) {
        return communityService.getById (id);
    }

    @GetMapping("/{name}")
    public Community getByName(String name) {
        return communityService.getByName (name);
    }

    @PostMapping()
    public Community save(CommunitySaveDTO communitySaveDTO) {
        return communityService.save (communitySaveDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        communityService.delete (id);
    }

}
