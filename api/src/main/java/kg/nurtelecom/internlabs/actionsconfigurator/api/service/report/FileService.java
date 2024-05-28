package kg.nurtelecom.internlabs.actionsconfigurator.api.service.report;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface FileService {

    Resource getFileAsResource(String path, String name) throws IOException;
}
