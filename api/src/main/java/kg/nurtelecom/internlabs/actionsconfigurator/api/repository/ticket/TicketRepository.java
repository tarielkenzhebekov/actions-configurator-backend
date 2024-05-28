package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.ticket;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The TicketRepository class is responsible for managing actions in the application.
 * It provides methods for retrieving, creating, updating, and deleting actions.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
