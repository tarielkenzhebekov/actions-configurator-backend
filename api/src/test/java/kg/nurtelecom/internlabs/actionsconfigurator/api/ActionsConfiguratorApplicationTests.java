package kg.nurtelecom.internlabs.actionsconfigurator.api;

import static org.assertj.core.api.Assertions.assertThat;

import kg.nurtelecom.internlabs.actionsconfigurator.api.controller.action.ActionController;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.action.ActionRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.ActionService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@AllArgsConstructor
class ActionsConfiguratorApplicationTests {

    private final ApplicationContext context;

    @Test
    void contextLoads() {
        ActionController controller = context.getBean(ActionController.class);
        assertThat(controller).isNotNull();

        ActionRepository repository = context.getBean(ActionRepository.class);
        assertThat(repository).isNotNull();

        ActionService service = context.getBean(ActionService.class);
        assertThat(service).isNotNull();
    }
}
