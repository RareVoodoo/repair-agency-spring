package ua.testing.repairagency.repository;

import org.springframework.data.repository.CrudRepository;
import ua.testing.repairagency.entity.RepairRequest;

public interface RepairRequestRepository extends CrudRepository< RepairRequest,Long> {
    RepairRequest getRepairRequestByUsername(String username);
}
