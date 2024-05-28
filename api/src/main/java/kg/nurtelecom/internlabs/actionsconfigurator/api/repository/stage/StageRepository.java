package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.stage;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The StageRepository class is responsible for managing actions in the application.
 * It provides methods for retrieving, creating, updating, and deleting actions.
 */
@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    @Query("SELECT stage FROM Stage stage WHERE stage.startDate = :start")
    List<Stage> findByStartDate(LocalDateTime start);

    @Query("SELECT stage FROM Stage stage WHERE stage.endDate = :end")
    List<Stage> findByEndDate(LocalDateTime end);

    @Query("SELECT stage FROM Stage stage WHERE stage.startDate >= :start AND stage.endDate <= :end")
    List<Stage> findByDateRange(LocalDateTime start, LocalDateTime end);
}
