package ua.testing.repairagency.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.testing.repairagency.entity.RepairRequest;

import java.util.List;

public interface RepairRequestRepository extends JpaRepository< RepairRequest,Long> {
    RepairRequest getRepairRequestByUsername(String username);
    List<RepairRequest> findByUsernameEqualsAndAcceptedTrueAndPerformedTrue(String username);

}
