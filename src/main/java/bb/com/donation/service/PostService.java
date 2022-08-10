package bb.com.donation.service;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService extends GenericService<Post, Long, PostGenericDTO> {

}