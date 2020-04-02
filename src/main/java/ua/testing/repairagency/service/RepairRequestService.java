package ua.testing.repairagency.service;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.region.CurrencyConverter;
import ua.testing.repairagency.repository.RepairRequestRepository;

import java.util.Locale;


@Service
public class RepairRequestService {

    public static final long REPAIR_PRICE_DEFAULT_VALUE = 0L;
    public static final String CANCELLATION_REASON_DEFAULT_VALUE = "not canceled";
    public static final String USER_COMMENT_DEFAULT_VALUE = "no comment";

    private final RepairRequestRepository repairRequestRepository;

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
                .cancellationReason(CANCELLATION_REASON_DEFAULT_VALUE)
                .userComment(USER_COMMENT_DEFAULT_VALUE)
                .repairPriceUah(REPAIR_PRICE_DEFAULT_VALUE)
                .repairPriceUsd(REPAIR_PRICE_DEFAULT_VALUE)
                .build());

    }

    private CurrencyConverter currencyConverter = new CurrencyConverter();
    public void setRepairPriceWithExchangeRate(RepairRequestDTO repairRequestDTO, RepairRequest repairRequest){
        if(LocaleContextHolder.getLocale().equals(Locale.US)){
            repairRequest.setRepairPriceUsd(repairRequestDTO.getRepairPriceUsd());
            repairRequest.setRepairPriceUah(currencyConverter.UsdToUahConvert(repairRequestDTO.getRepairPriceUsd()));
        }else{
            repairRequest.setRepairPriceUah(repairRequestDTO.getRepairPriceUsd());
            repairRequest.setRepairPriceUsd(currencyConverter.UahToUsdConvert(repairRequestDTO.getRepairPriceUsd()));
        }
    }
}
