package kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentpromocode;

import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentpromocode.AbonentPromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentpromocode.AbonentPromocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentpromocode.AbonentPromocodeResponse;

import java.util.List;

public interface AbonentPromocodeService {

    List<AbonentPromocodeResponse> create(AbonentPromocodeRequest abonentPromocodeRequest);

    AbonentPromocodeResponse findById(Long id);

    default List<AbonentPromocodeResponse> findByPhoneNumber(String phoneNumber) throws MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    AbonentPromocode create(AbonentPromocode abonentPromocode);
}
