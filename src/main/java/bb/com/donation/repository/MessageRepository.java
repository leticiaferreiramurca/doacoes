package bb.com.donation.repository;

import bb.com.donation.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {


}
