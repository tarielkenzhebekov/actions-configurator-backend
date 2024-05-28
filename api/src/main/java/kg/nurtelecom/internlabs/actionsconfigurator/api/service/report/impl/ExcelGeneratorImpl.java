package kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.report.ExcelGenerator;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.statistics.StatisticsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ExcelGeneratorImpl implements ExcelGenerator {
    StatisticsService statisticsService;
    Logger logger = LoggerFactory.getLogger(ExcelGeneratorImpl.class);

    @Override
    public boolean generate(String path, String fileName) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Отчет");

            String fullPath = path + fileName;
            FileOutputStream outputStream = new FileOutputStream(fullPath);
            workbook.write(outputStream);
            workbook.close();

            return true;
        } catch (FileNotFoundException e) {
            logger.error("Couldn't find path " + path);
        } catch (IOException e) {
            logger.error("Couldn't save file " + fileName);
        }

        return false;
    }
}
