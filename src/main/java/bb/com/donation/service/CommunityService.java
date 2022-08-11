package bb.com.donation.service;

import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import bb.com.donation.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService extends GenericService<Community, Long, CommunitySaveDTO> {

    Page<Community> filtrar(String filtro, Pageable pageable);
}
