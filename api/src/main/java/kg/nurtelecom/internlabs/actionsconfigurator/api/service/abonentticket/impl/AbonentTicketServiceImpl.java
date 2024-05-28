package kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentticket.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.abonenttcket.AbonentTicketRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.rule.RuleRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.stage.StageRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.ticket.TicketRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentticket.AbonentTicketService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentticket.AbonentTicket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.abonentPromocode.AbonentPromocodeNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.abonentTicket.AbonentTicketNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.rule.RuleNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.stage.StageNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.ticket.TicketNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.AbonentTicketMapper;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentticket.AbonentTicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentticket.AbonentTicketRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AbonentTicketServiceImpl implements AbonentTicketService {

    AbonentTicketRepository abonentTicketRepository;
    TicketRepository ticketRepository;
    RuleRepository ruleRepository;
    StageRepository stageRepository;

    AbonentTicketMapper abonentTicketMapper;

    @Override
    public List<AbonentTicketResponse> create(AbonentTicketRequest abonentTicketRequest) {
        List<AbonentTicketResponse> abonentTickets = new ArrayList<AbonentTicketResponse>();

        Ticket ticket = getTicket(abonentTicketRequest.ticketId());
        Rule rule = getRule(ticket.getRule().getId());
        Stage stage = getStage(rule.getStage().getId());

        int availableTicketNumber = (int) (abonentTicketRequest.sum() / rule.getMinSum());
        List<AbonentTicket> allTickets = abonentTicketRepository.findAbonentTicketsByTicketId(abonentTicketRequest.ticketId());
        List<AbonentTicket> allTicketsOfOneAbonent = abonentTicketRepository.findByPhoneNumber(abonentTicketRequest.abonentPhoneNumber());
        allTicketsOfOneAbonent = allTicketsOfOneAbonent.stream()
                .filter(abonentTicket -> abonentTicket.getTicket().getId() == abonentTicketRequest.ticketId())
                .toList();

        if(!allTickets.isEmpty() && allTickets.size() >= stage.getTicketLimit()){
            throw new RuntimeException("Ticket limit is exceeded! " + stage.getTicketLimit());
        }
        if(rule.getMinSum() > abonentTicketRequest.sum()) {
            throw new RuntimeException("Insufficient funds to purchase a ticket");
        }
        if(!allTicketsOfOneAbonent.isEmpty() && allTicketsOfOneAbonent.size() >= rule.getAmount()) {
            throw new RuntimeException("Exceeded ticket limit for this abonent!");
        }
        while (allTickets.size() + availableTicketNumber > stage.getTicketLimit()) {
            availableTicketNumber--;
        }
        while (allTicketsOfOneAbonent.size() + availableTicketNumber > rule.getAmount()) {
            availableTicketNumber--;
        }
        for (int i = 0; i < availableTicketNumber; i++) {
            long sum = 0L;
            if(availableTicketNumber == 1) {
                sum = Math.toIntExact(abonentTicketRequest.sum());
            } else if (i + 1 == availableTicketNumber) {
                sum = (long) (abonentTicketRequest.sum() - rule.getMinSum() * (i));
            } else {
                sum = rule.getMinSum().longValue();
            }
            AbonentTicketRequest newRequest = new AbonentTicketRequest(abonentTicketRequest.ticketId(), sum ,abonentTicketRequest.abonentPhoneNumber());

            abonentTickets.add(createOneAbonentTicket(newRequest));
        }
        return abonentTickets;
    }

    @Override
    public AbonentTicketResponse findById(Long id) throws AbonentTicketNotFoundException {
        Optional<AbonentTicket> result = abonentTicketRepository.findById(id);

        if (result.isEmpty()) {
            throw new AbonentPromocodeNotFoundException("Cannot find stage by provided id: " + id);
        }

        AbonentTicket abonentTicket = result.get();

        return abonentTicketMapper.entityToResponse(abonentTicket);
    }

    @Override
    public List<AbonentTicketResponse> findByPhoneNumber(String phoneNumber) {

        List<AbonentTicket> promocodes = abonentTicketRepository.findByPhoneNumber(phoneNumber);

        return promocodes.stream()
                .map(abonentTicketMapper::entityToResponse)
                .toList();
    }

    @Override
    public AbonentTicket create(AbonentTicket abonentTicket) {
        return abonentTicketRepository.save(abonentTicket);
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

    private Ticket getTicket(Long ticketId) throws TicketNotFoundException {
        Ticket ticket = null;
        if(ticketId != null) {
            Optional<Ticket> ticketById = ticketRepository.findById(ticketId);

            if(ticketById.isPresent()) {
                ticket = ticketById.get();
            } else {
                throw  new TicketNotFoundException("Cannot find ticket by provided id: " + ticketId);
            }
        }
        return ticket;
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

    private AbonentTicketResponse createOneAbonentTicket(AbonentTicketRequest abonentTicketRequest) {
        Ticket ticket = getTicket(abonentTicketRequest.ticketId());
        Rule rule = getRule(ticket.getRule().getId());
        Stage stage = getStage(rule.getStage().getId());

        List<AbonentTicket> allTickets = abonentTicketRepository.findAbonentTicketsByTicketId(abonentTicketRequest.ticketId());
        List<AbonentTicket> allTicketsOfOneAbonent = abonentTicketRepository.findByPhoneNumber(abonentTicketRequest.abonentPhoneNumber());
        allTicketsOfOneAbonent = allTicketsOfOneAbonent.stream()
                .filter(abonentTicket -> abonentTicket.getTicket().getId() == abonentTicketRequest.ticketId())
                .toList();
        if(!allTickets.isEmpty() && allTickets.size() >= stage.getTicketLimit()){
            throw new RuntimeException("Ticket limit is exceeded! " + stage.getTicketLimit());
        }
        if(rule.getMinSum() > abonentTicketRequest.sum()) {
            throw new RuntimeException("Insufficient funds to purchase a ticket");
        }
        if(!allTicketsOfOneAbonent.isEmpty() && allTicketsOfOneAbonent.size() >= rule.getAmount()) {
            throw new RuntimeException("Exceeded ticket limit for this abonent!");
        }
        Integer cashback = 0;
        if(rule.getMaxSum() < abonentTicketRequest.sum()) {
            cashback = (int) (abonentTicketRequest.sum() - rule.getMaxSum());
        }
        AbonentTicket abonentTicket = abonentTicketMapper.requestToEntity(abonentTicketRequest);
        abonentTicket.setTicket(ticket);
        abonentTicket.setStatus(Status.NOT_USED);
        abonentTicket.setCashback(cashback);
        abonentTicketRepository.save(abonentTicket);
        return abonentTicketMapper.entityToResponse(abonentTicket);
    }
}
