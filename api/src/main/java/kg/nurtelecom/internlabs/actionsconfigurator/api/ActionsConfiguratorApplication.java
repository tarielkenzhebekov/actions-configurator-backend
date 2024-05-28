package kg.nurtelecom.internlabs.actionsconfigurator.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "kg.nurtelecom.internlabs.actionsconfigurator.*")
@EntityScan("kg.nurtelecom.internlabs.actionsconfigurator.*")
@EnableScheduling
public class ActionsConfiguratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionsConfiguratorApplication.class, args);
    }
}
