package ua.testing.repairagency.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.controller.ManagerController;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.region.currency.CurrencyConverter;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.repository.RepairRequestRepository;


import java.util.List;
import java.util.Locale;


@Service
public class RepairRequestService {

    Logger logger = LoggerFactory.getLogger(RepairRequestService.class);

    public static final long REPAIR_PRICE_DEFAULT_VALUE = 0L;
    public static final String CANCELLATION_REASON_DEFAULT_VALUE = "not canceled";
    public static final String USER_COMMENT_DEFAULT_VALUE = "no comment";

    private final RepairRequestRepository repairRequestRepository;
    private CurrencyConversion currencyConversion = new CurrencyConversion();

    public RepairRequestService(RepairRequestRepository repairRequestRepository) {
        this.repairRequestRepository = repairRequestRepository;
    }



    @Transactional
    public RepairRequest createNewRepairRequest(RepairRequestDTO repairRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return repairRequestRepository.save(RepairRequest.builder()
                .username(authentication.getName())
                .description(repairRequestDTO.getDescription())
                .accepted(false)
                .performed(false)
                .phoneNumber(repairRequestDTO.getPhoneNumber())
                .address(repairRequestDTO.getAddress())
                .cancellationReason(CANCELLATION_REASON_DEFAULT_VALUE)
                .userComment(USER_COMMENT_DEFAULT_VALUE)
                .repairPriceUah(REPAIR_PRICE_DEFAULT_VALUE)
                .repairPriceUsd(REPAIR_PRICE_DEFAULT_VALUE)
                .build());

    }

    public void setRepairPriceWithExchangeRate(RepairRequestDTO repairRequestDTO, RepairRequest repairRequest){
            repairRequest.setRepairPriceUah(currencyConversion
                    .convert(NameTransliteration.UA_LOCALE,repairRequestDTO.getRepairPriceUsd()));

            repairRequest.setRepairPriceUsd(currencyConversion
                    .convert(NameTransliteration.EN_LOCALE,repairRequestDTO.getRepairPriceUsd()));
    }


    public RepairRequest cancelUserRequest(Long id, RepairRequestDTO repairRequestDTO){
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(false);
        request.setPerformed(false);
        request.setRepairPriceUah(REPAIR_PRICE_DEFAULT_VALUE);
        request.setRepairPriceUsd(REPAIR_PRICE_DEFAULT_VALUE);
        request.setCancellationReason(repairRequestDTO.getCancellationReason());
        logger.warn("Cancel message: " + repairRequestDTO.getCancellationReason());

        return repairRequestRepository.save(request);
    }


    public void acceptUserRequestById(Long id, RepairRequestDTO repairRequestDTO){
        RepairRequest request = repairRequestRepository.findById(id).get();
        request.setAccepted(true);
        setRepairPriceWithExchangeRate(repairRequestDTO, request);
        repairRequestRepository.save(request);
    }


    public void deleteUserRequestById(Long id){
        repairRequestRepository.deleteById(id);
    }

    public List<RepairRequest> findAll(){
        return repairRequestRepository.findAll();
    }

    public Page<RepairRequest> findAll(Pageable pageable){
        return repairRequestRepository.findAll(pageable);
    }

    public RepairRequest findById(long id){
        return repairRequestRepository.findById(id).get();
    }

    public List<RepairRequest> findExecutedRequestsByUsername(String username){
        return repairRequestRepository.findByUsernameEqualsAndAcceptedTrueAndPerformedTrue(username);
    }

    public List<RepairRequest> findAcceptedRequests(){
        return repairRequestRepository.findByAcceptedTrueAndPerformedFalse();
    }

    public RepairRequest save( RepairRequest request){
        return repairRequestRepository.save(request);
    }




}
