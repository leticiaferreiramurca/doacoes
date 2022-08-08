package bb.com.donation.service;

import bb.com.donation.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService extends GenericService<Post, Long> {
    Post update(Post post, Long id);
}