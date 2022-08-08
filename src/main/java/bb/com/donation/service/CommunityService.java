package bb.com.donation.service;

import bb.com.donation.model.Community;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService extends GenericService<Community, Long> {

    Community getByName(String name);
}
