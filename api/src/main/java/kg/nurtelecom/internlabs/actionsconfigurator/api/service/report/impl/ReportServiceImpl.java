package kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.report.ReportRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.FileService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.ReportService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.report.Report;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ReportServiceImpl implements ReportService {
    ReportRepository reportRepository;
    FileService fileService;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Resource loadById(Long id) throws IOException {
        Optional<Report> reportOptional = findById(id);
        if (reportOptional.isPresent()) {
            Report report = reportOptional.get();
            return fileService.getFileAsResource(report.getPath(), report.getName());
        }
        return null;
    }

    @Override
    public int save(String path, String name) {
        return reportRepository.save(path, name);
    }

    private Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }
}
