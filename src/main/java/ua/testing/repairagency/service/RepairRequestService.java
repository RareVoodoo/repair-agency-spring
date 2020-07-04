package ua.testing.repairagency.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.dto.RepairRequestDto;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.repository.RepairRequestRepository;
import ua.testing.repairagency.util.Constants;


import java.util.List;


@Service
public class RepairRequestService {

    Logger logger = LoggerFactory.getLogger(RepairRequestService.class);

    private final RepairRequestRepository repairRequestRepository;
    private CurrencyConversion currencyConversion = new CurrencyConversion();

    public RepairRequestService(RepairRequestRepository repairRequestRepository) {
        this.repairRequestRepository = repairRequestRepository;
    }


    /**
     * Builds and saves completed by user repair request object
     *
     * @param repairRequestDto repair request object
     */
    public void createNewRepairRequest(RepairRequestDto repairRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        repairRequestRepository.save(RepairRequest.builder()
                .username(authentication.getName())
                .description(repairRequestDto.getDescription())
                .accepted(false)
                .performed(false)
                .phoneNumber(repairRequestDto.getPhoneNumber())
                .address(repairRequestDto.getAddress())
                .cancellationReason(Constants.CANCELLATION_REASON_DEFAULT_VALUE)
                .userComment(Constants.USER_COMMENT_DEFAULT_VALUE)
                .repairPriceUah(Constants.REPAIR_PRICE_DEFAULT_VALUE)
                .repairPriceUsd(Constants.REPAIR_PRICE_DEFAULT_VALUE)
                .build());

    }

    /**
     * Converts input repair request price to the corresponding currency
     *
     * @param repairRequestDTO repair request object with filled out price
     * @param repairRequest    original repair request with default request price
     */
    public void setRepairPriceWithExchangeRate(RepairRequestDto repairRequestDTO, RepairRequest repairRequest) {
        repairRequest.setRepairPriceUah(currencyConversion
                .convert(Constants.UA_LOCALE, repairRequestDTO.getRepairPriceUsd()));

        repairRequest.setRepairPriceUsd(currencyConversion
                .convert(Constants.EN_LOCALE, repairRequestDTO.getRepairPriceUsd()));
    }


    /**
     * Cancels user's repair request with cancellation reason
     *
     * @param id               repair request id
     * @param repairRequestDTO repair request object
     */
    public void cancelUserRequest(Long id, RepairRequestDto repairRequestDTO) {
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(false);
        request.setPerformed(false);
        request.setRepairPriceUah(Constants.REPAIR_PRICE_DEFAULT_VALUE);
        request.setRepairPriceUsd(Constants.REPAIR_PRICE_DEFAULT_VALUE);
        request.setCancellationReason(repairRequestDTO.getCancellationReason());
        logger.warn("Cancel message: " + repairRequestDTO.getCancellationReason());

        repairRequestRepository.save(request);
    }


    /**
     * Accepts user's repair request
     *
     * @param id               repair request id
     * @param repairRequestDTO repair request object
     */
    @Transactional
    public void acceptUserRequest(Long id, RepairRequestDto repairRequestDTO) {
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(true);
        setRepairPriceWithExchangeRate(repairRequestDTO, request);
        repairRequestRepository.save(request);
    }


    /**
     * Performs accepted user's repair request
     *
     * @param id repair request id
     */
    public void performRequest(Long id) {
        RepairRequest request = findById(id);

        if (!request.isPerformed()) {
            request.setPerformed(true);
        } else {
            request.setPerformed(false);
        }
        save(request);
    }


    /**
     * Adds user comment for executed repair request
     *
     * @param repairRequestDto repair request object
     * @param id               repair request id
     */
    public void setUserComment(RepairRequestDto repairRequestDto, Long id) {
        RepairRequest request = findById(id);
        request.setUserComment(repairRequestDto.getUserComment());
        save(request);
    }


    public void deleteUserRequestById(Long id) {
        repairRequestRepository.deleteById(id);
    }

    public List<RepairRequest> findAll() {
        return repairRequestRepository.findAll();
    }

    public Page<RepairRequest> findAll(Pageable pageable) {
        return repairRequestRepository.findAll(pageable);
    }

    public RepairRequest findById(long id) {
        return repairRequestRepository.findById(id).get();
    }


    public List<RepairRequest> findExecutedRequestsByUsername(String username) {
        return repairRequestRepository.findByUsernameEqualsAndAcceptedTrueAndPerformedTrue(username);
    }

    public List<RepairRequest> findAcceptedRequests() {
        return repairRequestRepository.findByAcceptedTrueAndPerformedFalse();
    }

    public RepairRequest save(RepairRequest request) {
        return repairRequestRepository.save(request);
    }

}
