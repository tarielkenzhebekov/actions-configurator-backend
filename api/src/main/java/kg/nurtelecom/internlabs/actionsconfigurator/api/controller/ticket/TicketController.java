package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.ticket.TicketService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.ticket.TicketRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Tag(name = "Ticket")
@DependsOn("ticketService")
public class TicketController {

    TicketService ticketService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new ticket", description = "Create new ticket")
    public ResponseMessage<TicketResponse> create(@Valid @RequestBody TicketRequest ticketRequest) {
        return new ResponseMessage<>(
                ticketService.create(ticketRequest),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get ticket by id", description = "Get ticket by id")
    public ResponseMessage<TicketResponse> findById(@PathVariable Long ticketId) {
        return new ResponseMessage<>(
                ticketService.findById(ticketId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all ticket", description = "Get a list of all ticket")
    public ResponseMessage<List<TicketResponse>> findAll() throws MethodNotSupportedException {
        return new ResponseMessage<>(
                ticketService.findAll(),
                ResultCode.SUCCESS
        );
    }

    @PutMapping("/update/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change ticket by id", description = "Change ticket by id")
    public ResponseMessage<TicketResponse> updateById(@PathVariable Long ticketId,
                                                      @Valid @RequestBody TicketRequest ticketRequest) {

        return new ResponseMessage<>(
                ticketService.updateById(ticketId, ticketRequest),
                ResultCode.SUCCESS
        );
    }

    @DeleteMapping("/delete/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete ticket by id", description = "Delete ticket by id")
    public ResponseMessage<TicketResponse> deleteById(@PathVariable Long ticketId) {
        return new ResponseMessage<>(
                ticketService.deleteById(ticketId),
                ResultCode.SUCCESS
        );
    }
}

