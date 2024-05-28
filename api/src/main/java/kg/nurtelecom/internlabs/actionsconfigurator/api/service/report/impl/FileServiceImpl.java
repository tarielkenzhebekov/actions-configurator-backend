package kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    public Resource getFileAsResource(String path, String name) throws IOException {
        Path dirPath = Paths.get(path);
        Path foundFile;

        foundFile = Files.list(dirPath)
                .filter(file -> file.getFileName().toString().startsWith(name))
                .findFirst()
                .orElse(null);

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
