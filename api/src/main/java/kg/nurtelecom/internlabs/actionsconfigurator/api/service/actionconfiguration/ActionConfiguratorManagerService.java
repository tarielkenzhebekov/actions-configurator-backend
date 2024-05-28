package kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.actionconfiguration.ActionConfigurationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration.ActionConfigurationResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface ActionConfiguratorManagerService extends
        BaseService<ActionConfigurationResponse, ActionConfigurationRequest, ActionConfigurationRequest, Long>,
        EntityService<ActionConfiguration, Long>
{

    List<ActionConfigurationResponse> findAll();

    List<ActionConfigurationResponse> findAllByStartDateAfter(LocalDateTime after);

    List<ActionConfiguration> find();

    List<ActionConfiguration> find(LocalDateTime after);

}
