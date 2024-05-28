package kg.nurtelecom.internlabs.actionsconfigurator.api.service.report;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.report.Report;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    List<Report> findAll();

    Resource loadById(Long id) throws IOException;

    int save(String path, String name);
}
