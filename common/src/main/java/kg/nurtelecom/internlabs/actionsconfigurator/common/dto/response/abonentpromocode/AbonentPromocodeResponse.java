package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentpromocode;

import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;

import java.time.LocalDateTime;

// TODO: Add base response extension here
public record AbonentPromocodeResponse(
        Long id,
        String abonentPhoneNumber,
        String code,
        LocalDateTime usedAt,
        Integer sum,
        Integer cashback,
        Status status,
        LocalDateTime expiredDate
) {
}
