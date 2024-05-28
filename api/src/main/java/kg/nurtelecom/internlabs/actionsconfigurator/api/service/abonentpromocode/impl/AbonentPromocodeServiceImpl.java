package kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentpromocode.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.abonentpromocode.AbonentPromocodeRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.promocode.PromocodeRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.rule.RuleRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.stage.StageRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentpromocode.AbonentPromocodeService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentpromocode.AbonentPromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentpromocode.AbonentPromocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.abonentPromocode.AbonentPromocodeNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.promocode.PromocodeNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.rule.RuleNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.stage.StageNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.AbonentPromocodeMapper;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentpromocode.AbonentPromocodeResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AbonentPromocodeServiceImpl implements AbonentPromocodeService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    AbonentPromocodeRepository abonentPromocodeRepository;
    PromocodeRepository promocodeRepository;
    RuleRepository ruleRepository;
    StageRepository stageRepository;

    AbonentPromocodeMapper abonentPromocodeMapper;

    @Override
    public List<AbonentPromocodeResponse> create(AbonentPromocodeRequest abonentPromocodeRequest) {
        List<AbonentPromocodeResponse> abonentPromocodes = new ArrayList<>();
        Promocode promocode = getPromocode(abonentPromocodeRequest.promocodeId());
        Rule rule = getRule(promocode.getRule().getId());
        Stage stage = getStage(rule.getStage().getId());

        int availablePromocodeNumber = (int) (abonentPromocodeRequest.sum() / rule.getMinSum());
        List<AbonentPromocode> allPromocodes = abonentPromocodeRepository.findAbonentPromocodesByPromocodeId(abonentPromocodeRequest.promocodeId());
        List<AbonentPromocode> allPromocodesOfOneAbonent = abonentPromocodeRepository.findByPhoneNumber(abonentPromocodeRequest.abonentPhoneNumber());
        allPromocodesOfOneAbonent = allPromocodesOfOneAbonent.stream().filter(abonentPromocode -> abonentPromocode.getPromocode().getId() == abonentPromocodeRequest.promocodeId()).collect(Collectors.toList());
        if(!allPromocodes.isEmpty() && allPromocodes.size() >= stage.getTicketLimit()){
            throw new RuntimeException("Promocode limit is exceeded! " + stage.getTicketLimit());
        }
        if(rule.getMinSum() > abonentPromocodeRequest.sum()) {
            throw new RuntimeException("Insufficient funds to purchase a promocode");
        }
        if(!allPromocodesOfOneAbonent.isEmpty() && allPromocodesOfOneAbonent.size() >= rule.getAmount()) {
            throw new RuntimeException("Exceeded ticket limit for this abonent!");
        }

        while (allPromocodes.size() + availablePromocodeNumber > stage.getTicketLimit()) {
            availablePromocodeNumber--;
        }
        while (allPromocodesOfOneAbonent.size() + availablePromocodeNumber > rule.getAmount()) {
            availablePromocodeNumber--;
        }
        for (int i = 0; i < availablePromocodeNumber; i++) {
            long sum = 0L;
            if(availablePromocodeNumber == 1) {
                sum = Math.toIntExact(abonentPromocodeRequest.sum());
            } else if (i + 1 == availablePromocodeNumber) {
                sum = (long) (abonentPromocodeRequest.sum() - rule.getMinSum() * (i));
            } else {
                sum = rule.getMinSum().longValue();
            }
            AbonentPromocodeRequest newRequest = new AbonentPromocodeRequest(abonentPromocodeRequest.promocodeId(), sum ,abonentPromocodeRequest.abonentPhoneNumber());

            abonentPromocodes.add(createOneAbonentPromocode(newRequest));
        }
        return abonentPromocodes;
    }

    @Override
    public AbonentPromocodeResponse findById(Long id) throws AbonentPromocodeNotFoundException {
        Optional<AbonentPromocode> result = abonentPromocodeRepository.findById(id);

        if (result.isEmpty()) {
            throw new AbonentPromocodeNotFoundException("Cannot find stage by provided id: " + id);
        }

        AbonentPromocode abonentPromocode = result.get();

        return abonentPromocodeMapper.entityToResponse(abonentPromocode);
    }

    @Override
    public List<AbonentPromocodeResponse> findByPhoneNumber(String phoneNumber) {

        List<AbonentPromocode> promocodes = abonentPromocodeRepository.findByPhoneNumber(phoneNumber);

        return promocodes.stream()
                .map(abonentPromocodeMapper::entityToResponse)
                .toList();
    }

    @Override
    public AbonentPromocode create(AbonentPromocode abonentPromocode) {
        return abonentPromocodeRepository.save(abonentPromocode);
    }

    private Rule getRule(Long ruleId) throws RuleNotFoundException {
        Rule rule = null;
        if (ruleId != null) {
            Optional<Rule> ruleById = ruleRepository.findById(ruleId);

            if (ruleById.isPresent()) {
                rule = ruleById.get();
            } else {
                throw new RuleNotFoundException("Cannot find rule by provided id: " + ruleId);
            }
        }
        return rule;
    }

    private Promocode getPromocode(Long promocodeId) throws PromocodeNotFoundException {
        Promocode promocode = null;
        if(promocodeId != null) {
            Optional<Promocode> promocodeById = promocodeRepository.findById(promocodeId);

            if(promocodeById.isPresent()) {
                promocode = promocodeById.get();
            } else {
                throw  new PromocodeNotFoundException("Cannot find ticket by provided id: " + promocodeId);
            }
        }
        return promocode;
    }

    private Stage getStage(Long stageId) throws StageNotFoundException {
        Stage stage = null;
        if(stageId != null) {
            Optional<Stage> stageById = stageRepository.findById(stageId);

            if(stageById.isPresent()) {
                stage = stageById.get();
            } else {
                throw new StageNotFoundException("Cannot find stage by provided id: " + stageId);
            }
        }
        return stage;
    }

    private AbonentPromocodeResponse createOneAbonentPromocode(AbonentPromocodeRequest abonentPromocodeRequest) {
        Promocode promocode = getPromocode(abonentPromocodeRequest.promocodeId());
        Rule rule = getRule(promocode.getRule().getId());
        Stage stage = getStage(rule.getStage().getId());

        List<AbonentPromocode> allPromocodes = abonentPromocodeRepository.findAbonentPromocodesByPromocodeId(abonentPromocodeRequest.promocodeId());
        List<AbonentPromocode> allPromocodesOfOneAbonent = abonentPromocodeRepository.findByPhoneNumber(abonentPromocodeRequest.abonentPhoneNumber());
        allPromocodesOfOneAbonent = allPromocodesOfOneAbonent.stream()
                .filter(abonentPromocode -> abonentPromocode.getPromocode().getId() == abonentPromocodeRequest.promocodeId())
                .collect(Collectors.toList());
        if(!allPromocodes.isEmpty() && allPromocodes.size() >= stage.getPromocodeLimit()){
            throw new RuntimeException("Promocode limit is exceeded! " + stage.getPromocodeLimit());
        }
        if(rule.getMinSum() > abonentPromocodeRequest.sum()) {
            throw new RuntimeException("Insufficient funds to purchase a promocode");
        }
        if(!allPromocodesOfOneAbonent.isEmpty() && allPromocodesOfOneAbonent.size() >= rule.getAmount()) {
            throw new RuntimeException("Exceeded promocode limit for this abonent!");
        }
        Integer cashback = 0;
        if(rule.getMaxSum() < abonentPromocodeRequest.sum()) {
            cashback = (int) (abonentPromocodeRequest.sum() - rule.getMaxSum());
        }
        AbonentPromocode abonentPromocode = abonentPromocodeMapper.requestToEntity(abonentPromocodeRequest);
        abonentPromocode.setPromocode(promocode);
        abonentPromocode.setCode(generateRandomString());
        abonentPromocode.setStatus(Status.NOT_USED);
        abonentPromocode.setCashback(cashback);
        abonentPromocodeRepository.save(abonentPromocode);
        return abonentPromocodeMapper.entityToResponse(abonentPromocode);
    }

    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
