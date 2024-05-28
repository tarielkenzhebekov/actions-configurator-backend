package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.abonenttcket;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentticket.AbonentTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbonentTicketRepository extends JpaRepository<AbonentTicket, Long> {
    @Query("SELECT abonentTicket FROM AbonentTicket abonentTicket WHERE abonentTicket.abonentPhoneNumber LIKE %:phoneNumber%")
    List<AbonentTicket> findByPhoneNumber(String phoneNumber);

    List<AbonentTicket> findAbonentTicketsByTicketId(Long id);
}
