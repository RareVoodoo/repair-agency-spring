package ua.testing.repairagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.repairagency.entity.RepairRequest;

import java.util.List;
import java.util.Optional;

public interface RepairRequestRepository extends JpaRepository< RepairRequest,Long> {
    List <RepairRequest> findByUsernameEqualsAndAcceptedTrueAndPerformedTrue(String username);
    List<RepairRequest> findByAcceptedTrueAndPerformedFalse();

}
