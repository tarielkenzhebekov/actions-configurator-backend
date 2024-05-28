package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.promocode;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The PromocodeRepository class is responsible for managing actions in the application.
 * It provides methods for retrieving, creating, updating, and deleting actions.
 */
@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {
}