package ua.testing.repairagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.testing.repairagency.util.Constants;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairRequestDto {


    private Long id;

    private String username;
    @Pattern(regexp = Constants.DESCRIPTION_VALIDATION_REGEX,message = Constants.DESCRIPTION_VALIDATION_PROPERTY)
    private String description;

    private boolean accepted;

    private boolean performed;

    @Pattern(regexp = Constants.CANCELLATION_REASON_VALIDATION_REGEX,
            message = Constants.CANCELLATION_REASON_VALIDATION_PROPERTY )
    private String cancellationReason;

    private Long repairPriceUah;

    @PositiveOrZero(message = Constants.POSITIVE_PRICE_VALIDATION_PROPERTY)
    @DecimalMax(value= Constants.PRICE_MAX_VALUE , message = Constants.MAX_PRICE_VALUE_VALIDATION_PROPERTY)
    private Long repairPriceUsd;

    @Pattern(regexp = Constants.COMMENT_VALIDATION_REGEX, message = Constants.COMMENT_VALIDATION_PROPERTY)
    private String userComment;

    @Pattern( message = Constants.ADDRESS_VALIDATION_PROPERTY  ,regexp = Constants.ADDRESS_VALIDATION_REGEX)
    private String address;

    @Pattern(message = Constants.PHONE_NUMBER_VALIDATION_PROPERTY,regexp = Constants.PHONE_NUMBER_VALIDATION_REGEX)
    private String phoneNumber;

}
