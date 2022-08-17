package bb.com.donation.service;

import bb.com.donation.dto.community.CommunitySaveDTO;
import bb.com.donation.model.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService extends GenericService<Community, Long, CommunitySaveDTO> {

    Page<Community> filtrar(String filtro, Pageable pageable);

    Page<Community> getAllOrByName(String name, Pageable pageable);
}
