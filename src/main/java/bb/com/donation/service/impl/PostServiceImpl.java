package bb.com.donation.service.impl;

import bb.com.donation.dto.post.PostGenericDTO;
import bb.com.donation.model.Post;
import bb.com.donation.repository.PostRepository;
import bb.com.donation.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



    @Override
    public Post save(PostGenericDTO postGenericDTO) {
        Post post = postGenericDTO.toPost();
        post.setId (null);
        return postRepository.save(post);
    }

    @Override
    public Post getById(Long aLong) {
        return postRepository.findById(aLong).orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Long aLong) {
        postRepository.deleteById(aLong);
    }
}
