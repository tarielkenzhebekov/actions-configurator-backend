package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.statistics;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StatisticsRepository {
    JdbcTemplate jdbcTemplate;

    public Long findAmountOfActions() {
        String sql = "SELECT COUNT(*) FROM actions WHERE deleted=FALSE";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findAmountOfActivatedActions() {
        String sql = "SELECT COUNT(*) FROM actions WHERE activated=TRUE";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findTotalIncomePromocodes() {
        String sql = "SELECT CASE WHEN SUM(sum) IS NOT NULL THEN SUM(sum) ELSE 0 END " +
                "FROM  abonent_promocodes";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findTotalIncomeTickets() {
        String sql = "SELECT CASE WHEN SUM(sum) IS NOT NULL THEN SUM(sum) ELSE 0 END " +
                "FROM  abonent_tickets";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findTotalPromocodesSold() {
        String sql = "SELECT COUNT(*) FROM abonent_promocodes";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findTotalTicketsSold() {
        String sql = "SELECT COUNT(*) FROM abonent_tickets";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    public Long findTotalPromocodesUsed() {
        String sql = "SELECT COUNT(*) FROM abonent_promocodes WHERE status='USED'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findTotalTicketsUsed() {
        String sql = "SELECT COUNT(*) FROM abonent_tickets WHERE status='USED'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findAmountOfPromocodes() {
        String sql = "SELECT CASE WHEN SUM(S.promocode_limit) IS NOT NULL THEN SUM(S.promocode_limit) ELSE 0 END " +
                "FROM Actions A " +
                "JOIN Stages S ON A.id = S.action_id " +
                "WHERE A.type='PROMOCODE'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long findAmountOfTickets() {
        String sql = "SELECT CASE WHEN SUM(S.ticket_limit) IS NOT NULL THEN SUM(S.ticket_limit) ELSE 0 END " +
                "FROM Actions A " +
                "JOIN Stages S ON A.id = S.action_id " +
                "WHERE A.type='TICKET'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public List<Long> findAmountPerStage(Long actionId) {
        String sql = "SELECT \n" +
                "CASE WHEN S.ticket_limit IS NOT NULL THEN S.ticket_limit ELSE 0 END " +
                "+ " +
                "CASE WHEN S.promocode_limit IS NOT NULL THEN S.promocode_limit ELSE 0 END " +
                "FROM stages S " +
                "JOIN actions A on A.id = S.action_id " +
                "WHERE A.id = ? " +
                "ORDER BY S.start_date";
        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }

    public List<Long> findPromocodesSoldPerStage(Long actionId) {
        String sql = "SELECT COUNT(AP.id) " +
                "FROM actions A " +
                "JOIN stages S on A.id = S.action_id " +
                "LEFT JOIN rules R on S.id = R.stage_id " +
                "LEFT JOIN promocodes P on R.id = P.rule_id " +
                "LEFT JOIN abonent_promocodes AP on P.id = AP.promocode_id " +
                "WHERE A.id = ? " +
                "GROUP BY S.start_date " +
                "ORDER BY S.start_date";

        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }

    public List<Long> findTicketsSoldPerStage(Long actionId) {
        String sql = "SELECT COUNT(AP.id) " +
                "FROM actions A " +
                "JOIN stages S on A.id = S.action_id " +
                "LEFT JOIN rules R on S.id = R.stage_id " +
                "LEFT JOIN tickets P on R.id = P.rule_id " +
                "LEFT JOIN abonent_tickets AP on P.id = AP.ticket_id " +
                "WHERE A.id = ? " +
                "GROUP BY S.start_date " +
                "ORDER BY S.start_date";

        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }

    public List<Long> findPromocodesIncomePerStage(Long actionId) {
        String sql = "SELECT CASE WHEN SUM(AP.sum) IS NOT NULL THEN SUM(AP.sum) ELSE 0 END " +
                "FROM actions A " +
                "JOIN stages S on A.id = S.action_id " +
                "LEFT JOIN rules R on S.id = R.stage_id " +
                "LEFT JOIN promocodes P on R.id = P.rule_id " +
                "LEFT JOIN abonent_promocodes AP on P.id = AP.promocode_id " +
                "WHERE A.id = ? " +
                "GROUP BY S.start_date " +
                "ORDER BY S.start_date";

        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }

    public List<Long> findTicketsIncomePerStage(Long actionId) {
        String sql = "SELECT CASE WHEN SUM(AT.sum) IS NOT NULL THEN SUM(AT.sum) ELSE 0 END " +
                "FROM actions A " +
                "JOIN stages S on A.id = S.action_id " +
                "LEFT JOIN rules R on S.id = R.stage_id " +
                "LEFT JOIN tickets T on R.id = T.rule_id " +
                "LEFT JOIN abonent_tickets AT on T.id = AT.ticket_id " +
                "WHERE A.id = ? " +
                "GROUP BY S.start_date " +
                "ORDER BY S.start_date";

        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }

    public List<Long> findPromocodesUsedPerStage(Long actionId) {
        String sql = "SELECT CASE WHEN COUNT(AP.id) > 0 THEN COUNT(AP.id) ELSE 0 END " +
                "FROM actions A " +
                "JOIN stages S ON A.id = S.action_id " +
                "LEFT JOIN rules R ON S.id = R.stage_id " +
                "LEFT JOIN promocodes P ON R.id = P.rule_id " +
                "LEFT JOIN abonent_promocodes AP ON P.id = AP.promocode_id AND AP.status = 'USED' " +
                "WHERE A.id = ? " +
                "GROUP BY S.start_date " +
                "ORDER BY S.start_date";

        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }

    public List<Long> findTicketsUsedPerStage(Long actionId) {
        String sql = "SELECT CASE WHEN COUNT(AT.id) > 0 THEN COUNT(AT.id) ELSE 0 END " +
                "FROM actions A " +
                "JOIN stages S ON A.id = S.action_id " +
                "LEFT JOIN rules R ON S.id = R.stage_id " +
                "LEFT JOIN tickets P ON R.id = P.rule_id " +
                "LEFT JOIN abonent_tickets AT ON P.id = AT.ticket_id AND AT.status = 'USED' " +
                "WHERE A.id = ? " +
                "GROUP BY S.start_date " +
                "ORDER BY S.start_date";

        return jdbcTemplate.queryForList(sql, Long.class, actionId);
    }
}
