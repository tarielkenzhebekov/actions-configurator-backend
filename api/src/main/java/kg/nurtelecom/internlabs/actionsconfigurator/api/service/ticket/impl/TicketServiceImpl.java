package kg.nurtelecom.internlabs.actionsconfigurator.api.service.ticket.impl;


import jakarta.annotation.Nullable;
import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.ticket.TicketRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.rule.RuleService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.ticket.TicketService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.user.UserService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.ticket.TicketRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.ticket.TicketNotFoundException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.TicketMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The TicketServiceImpl class implements the TicketService interface and provides
 * methods for managing ticket data.
 */
@Service("ticketService")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@DependsOn({
        "ruleService",
        "userService"
})
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    TicketMapper ticketMapper;

    @Lazy
    RuleService ruleService;

    UserService userService;

    @Override
    public TicketResponse create(@NonNull TicketRequest ticketRequest) {
        Ticket ticket = mapToTicket_AndSetRule(ticketRequest);

        return ticketMapper.entityToResponse(
                create(
                        ticket,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public TicketResponse findById(@NonNull Long id) {
        return ticketMapper.entityToResponse(
                find(id)
        );
    }

    @Override
    public List<TicketResponse> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::entityToResponse)
                .toList();
    }

    @Override
    public TicketResponse updateById(@NonNull Long id, @NonNull TicketRequest ticketRequest) {
        Ticket ticket = mapToTicket_AndSetRule(ticketRequest);

        return ticketMapper.entityToResponse(
                update(
                        id,
                        ticket,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public TicketResponse deleteById(@NonNull Long id) {
        return ticketMapper.entityToResponse(
                delete(
                        id,
                        userService.getCurrentUser()
                )
        );
    }

    @Override
    public Long count() {
        return ticketRepository.count();
    }

    @Override
    public Boolean existsById(@NonNull Long id) {
        return ticketRepository.existsById(id);
    }

    @Override
    public Ticket create(@NonNull Ticket ticket, @Nullable User currentUser) {
        ticket.setCreatedBy(currentUser);
        ticket.setCreatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket find(@NonNull Long id) {
        Optional<Ticket> byId = ticketRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        }
        throw new TicketNotFoundException("Cannot find ticket by id: " + id);
    }

    @Override
    public Ticket update(@NonNull Long ticketId, @NonNull Ticket updatedTicket, @Nullable User currentUser) {
        Ticket ticket = find(ticketId);

        ticket.setStartDate(updatedTicket.getStartDate());
        ticket.setEndDate(updatedTicket.getEndDate());
        ticket.setRule(updatedTicket.getRule());

        ticket.setUpdatedBy(currentUser);
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket delete(@NonNull Long id, @Nullable User currentUser) {
        Ticket ticket = find(id);

        ticket.setDeleted(true);
        ticket.setUpdatedBy(currentUser);
        ticket.setUpdatedAt(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    private Ticket mapToTicket_AndSetRule(TicketRequest ticketRequest) {
        Ticket ticket = ticketMapper.requestToEntity(ticketRequest);

        if (ticketRequest.ruleId() != null) {
            ticket.setRule(
                    ruleService.find(ticketRequest.ruleId())
            );
        }

        return ticket;
    }
}
