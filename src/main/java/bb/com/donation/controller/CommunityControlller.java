package bb.com.donation.controller;


import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/community")
@RestController
public class CommunityControlller {

    private final CommunityService communityService;

    public CommunityControlller(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/list")
    public List<Community> list() {
        return communityService.getAll ();
    }

    @GetMapping("/list/{id}")
    public Community getById(java.lang.Long id) {
        return communityService.getById (id);
    }

    @GetMapping("/list/name/{name}")
    public Community getByName(String name) {
        return communityService.getByName (name);
    }

    @PostMapping("/save")
    public Community save(CommunitySaveDTO communitySaveDTO) {
        return communityService.save (communitySaveDTO);
    }

    @GetMapping("/delete/{id}")
    public void delete(Long id) {
        communityService.delete (id);
    }

}
