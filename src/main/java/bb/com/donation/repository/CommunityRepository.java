package bb.com.donation.repository;

import bb.com.donation.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, java.lang.Long> {
    Community findByName(String name);
}