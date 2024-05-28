package kg.nurtelecom.internlabs.actionsconfigurator.api.service.promocode;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.BaseService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.EntityService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.promocode.PromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;

// TODO: Check javadoc in this file.
/**
 * The PromocodeService interface provides methods for managing promocode data.
 */
public interface PromocodeService extends
        BaseService<PromocodeResponse, PromocodeRequest, PromocodeRequest, Long>,
        EntityService<Promocode, Long>
{
}
