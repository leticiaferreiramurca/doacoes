package bb.com.donation.service;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.dto.post.PostSaveDTO;
import bb.com.donation.model.Post;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public interface PostService extends GenericService<Post, Long, PostGenericDTO> {
     Post save(PostSaveDTO postSaveDTO) throws ValidationException;

     Post edit(Post post) throws ValidationException;

     Post getById(Long aLong);

     List<Post> getAll();

     void delete(Long aLong);
}
