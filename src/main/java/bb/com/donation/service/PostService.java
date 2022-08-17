package bb.com.donation.service;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.model.Post;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public interface PostService extends GenericService<Post, Long, PostGenericDTO> {
    public Post save(PostGenericDTO postGenericDTO);

    public Post edit(Post post) throws ValidationException;

    public Post getById(Long aLong);

    public List<Post> getAll();

    public void delete(Long aLong);
}
