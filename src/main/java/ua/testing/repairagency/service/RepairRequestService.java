package ua.testing.repairagency.service;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.repairagency.dto.RepairRequestDTO;
import ua.testing.repairagency.entity.RepairRequest;
import ua.testing.repairagency.region.currency.CurrencyConversion;
import ua.testing.repairagency.region.currency.CurrencyConverter;
import ua.testing.repairagency.region.transliteration.NameTransliteration;
import ua.testing.repairagency.repository.RepairRequestRepository;

import java.util.Locale;


@Service
public class RepairRequestService {

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
}
