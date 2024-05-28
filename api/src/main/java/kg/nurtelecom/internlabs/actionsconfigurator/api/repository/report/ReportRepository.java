package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.report;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.report.Report;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ReportRepository {
    JdbcClient jdbcClient;

    public List<Report> findAll() {
        String sql = "SELECT * FROM reports";
        return jdbcClient.sql(sql).query(Report.class).list();
    }

    public Optional<Report> findById(Long id) {
        String sql = "SELECT * FROM reports WHERE id=:reportId";
        return jdbcClient.
                sql(sql)
                .param("reportId", id)
                .query(Report.class)
                .optional();
    }

    public int save(String path, String name) {
        String sql = "INSERT INTO reports (id, path, name, date_created)"
                + "VALUES (NEXTVAL('reports_seq'), :path, :name, :date_created)";
        return jdbcClient.sql(sql)
                .param("path", path)
                .param("name", name)
                .param("date_created", LocalDateTime.now())
                .update();
    }
}
