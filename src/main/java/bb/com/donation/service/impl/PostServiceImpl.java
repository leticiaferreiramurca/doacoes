package bb.com.donation.service.impl;

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
    public Post update(Post post, Long id) {
        return postRepository.save(post);
    }

    @Override
    public Post save(Post post) {
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
