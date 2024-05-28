package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentticket;

import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;

import java.time.LocalDateTime;

public record AbonentTicketResponse(
        Long id,
        String abonentPhoneNumber,
        Integer sum,
        Integer cashback,
        Status status,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
