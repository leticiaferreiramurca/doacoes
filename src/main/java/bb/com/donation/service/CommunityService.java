package bb.com.donation.service;

import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService extends GenericService<Community, Long, CommunitySaveDTO> {

    Community getByName(String name);
}
