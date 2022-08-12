package bb.com.donation.service;

import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService extends GenericService<Post, Long, PostSaveDTO> {
    Page<Post> getByName(String filtro, Pageable pageable);
    List<Post> getAll();

    Post getById(Long aLong);

}