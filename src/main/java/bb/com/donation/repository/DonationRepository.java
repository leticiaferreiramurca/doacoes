package bb.com.donation.repository;

import bb.com.donation.enums.DonationStatus;
import bb.com.donation.model.Donation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("select d from Donation d where d.donationStatus = ?1")
    Page<List<Donation>> findByDonationStatus(DonationStatus donationStatus, Pageable pageable);
    Optional<Donation> findByName(String name);

    Donation findByProductId(Long productId);
}