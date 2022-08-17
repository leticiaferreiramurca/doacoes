package bb.com.donation.service.impl;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.model.Post;
import bb.com.donation.repository.PostRepository;
import bb.com.donation.service.PostService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post save(PostGenericDTO postGenericDTO) {
        Post post = postGenericDTO.toPost();
        post.setId (null);
        return postRepository.save(post);
    }
    @Override
    @Transactional
    public Post edit(Post post) throws ValidationException {
        Post postByID = getById(post.getId ());
        if(Objects.isNull (postByID)){
            throw new ValidationException ("Não foi encontrado nenhum post com o id " + post.getId ());
        }
        postByID.setName (post.getName ());
        postByID.setDescription (post.getDescription ());
        postByID.setUrlImg (post.getUrlImg ());
        return postRepository.save(postByID);
    }

    @Override
    @Cacheable("post")
    public Post getById(Long aLong) {
        return postRepository.findById(aLong).orElse(null);
    }

    @Override
    @Cacheable("post")
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long aLong) {
        postRepository.deleteById(aLong);
    }
}
