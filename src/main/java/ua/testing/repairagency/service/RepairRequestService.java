package ua.testing.repairagency.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.repository.RepairRequestRepository;

@Service
public class RepairRequestService {

    @Autowired
    private RepairRequestRepository repairRequestRepository;



    public RepairRequest createNewRepairRequest(RepairRequestDTO repairRequestDTO){
        RepairRequest repairRequest = new RepairRequest();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        repairRequest.setUsername(authentication.getName());
        repairRequest.setDescription(repairRequestDTO.getDescription());
        repairRequest.setAccepted(false);
        repairRequest.setPerformed(false);
        repairRequest.setCancellationReason("not canceled");
        repairRequest.setUserComment("no comment");
        repairRequest.setRepairPrice(0.0);
        return repairRequestRepository.save(repairRequest);

    }
}
