package kg.nurtelecom.internlabs.actionsconfigurator.api.service.promocode.impl;

import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.promocode.PromocodeRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.promocode.PromocodeService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule.RuleService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.promocode.PromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.promocode.PromocodeNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.PromocodeMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The PromocodeServiceImpl class implements the PromocodeService interface and provides
 * methods for managing promocode data.
 */
@Service("promocodeService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@DependsOn({
        "ruleService",
        "userService"
})
public class PromocodeServiceImpl implements PromocodeService {

    PromocodeRepository promocodeRepository;

    PromocodeMapper promocodeMapper;

    RuleService ruleService;

    UserService userService;

    @Override
    public PromocodeResponse create(@NonNull PromocodeRequest promocodeRequest) {
        Promocode promocode = mapToPromocode_AndSetRule(promocodeRequest);

        return promocodeMapper.entityToResponse(
                create(
                        promocode,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public PromocodeResponse findById(@NonNull Long id) {
        return promocodeMapper.entityToResponse(
                find(id)
        );
    }

    @Override
    public List<PromocodeResponse> findAll() {
        return promocodeRepository.findAll()
                .stream()
                .map(promocodeMapper::entityToResponse)
                .toList();
    }

    @Override
    public PromocodeResponse updateById(@NonNull Long id, @NonNull PromocodeRequest promocodeRequest) {
        Promocode promocode = mapToPromocode_AndSetRule(promocodeRequest);

        return promocodeMapper.entityToResponse(
                update(
                        id,
                        promocode,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public PromocodeResponse deleteById(@NonNull Long id) {
        return promocodeMapper.entityToResponse(
                delete(
                        id,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public Long count() {
        return promocodeRepository.count();
    }

    @Override
    public Boolean existsById(@NonNull Long id) {
        return promocodeRepository.existsById(id);
    }

    @Override
    public Promocode create(@NonNull Promocode promocode, @Nullable User currentUser) {
        promocode.setCreatedBy(currentUser);
        promocode.setCreatedAt(LocalDateTime.now());

        return promocodeRepository.save(promocode);
    }

    @Override
    public Promocode find(@NonNull Long id) {
        Optional<Promocode> byId = promocodeRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        }
        throw new PromocodeNotFoundException("Cannot find promocode by provided id: " + id);
    }

    @Override
    public Promocode update(@NonNull Long promocodeId, @NonNull Promocode updatedPromocode, @Nullable User currentUser) {
        Promocode promocode = find(promocodeId);

        promocode.setExpiredDate(updatedPromocode.getExpiredDate());
        promocode.setRule(updatedPromocode.getRule());

        promocode.setUpdatedBy(currentUser);
        promocode.setUpdatedAt(LocalDateTime.now());

        return promocodeRepository.save(promocode);
    }

    @Override
    public Promocode delete(@NonNull Long promocodeId, @Nullable User currentUser) {
        Promocode promocode = find(promocodeId);

        promocode.setDeleted(true);
        promocode.setUpdatedBy(currentUser);
        promocode.setUpdatedAt(LocalDateTime.now());

        return promocodeRepository.save(promocode);
    }

    private Promocode mapToPromocode_AndSetRule(PromocodeRequest promocodeRequest) {
        Promocode promocode = promocodeMapper.requestToEntity(promocodeRequest);

        if (promocodeRequest.ruleId() != null) {
            promocode.setRule(
                    ruleService.find(promocodeRequest.ruleId())
            );
        }

        return promocode;
    }
}
