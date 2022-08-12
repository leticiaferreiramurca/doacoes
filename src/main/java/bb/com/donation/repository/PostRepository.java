package bb.com.donation.repository;

import bb.com.donation.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("select p from Post p where p.name like ?1")
//    Optional<Post> findByName(String name);
}