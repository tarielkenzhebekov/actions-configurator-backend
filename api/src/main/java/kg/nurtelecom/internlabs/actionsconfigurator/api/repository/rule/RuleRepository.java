package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.rule;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The RuleRepository class is responsible for managing actions in the application.
 * It provides methods for retrieving, creating, updating, and deleting actions.
 */
@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> findAllByMinSum(Double minSum);

    List<Rule> findAllByMaxSum(Double maxSum);

    List<Rule> findAllByMinSumAndMaxSum(Double minSum, Double maxSum);
}
