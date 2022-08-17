package bb.com.donation.service;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.model.Post;
import bb.com.donation.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService extends GenericService<Post, Long, PostGenericDTO> {
    public Post save(PostGenericDTO postGenericDTO);

    public Post edit(Long postID,Integer opcao, String text) throws Exception;

    public Post getById(Long aLong);

    public List<Post> getAll();

    public void delete(Long aLong);
}
