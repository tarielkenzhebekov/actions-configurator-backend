package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.abonentticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentticket.AbonentTicketService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentticket.AbonentTicketRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentticket.AbonentTicketResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abonent-ticket")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "Abonent ticket")
public class AbonentTicketController {

    AbonentTicketService abonentTicketService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new ticket for abonent", description = "Create new ticket for abonent")
    public ResponseMessage<List<AbonentTicketResponse>> create(@Valid @RequestBody AbonentTicketRequest abonentTicketRequest) {
        System.out.println("FOR EXAMPLE");
        return new ResponseMessage<>(
                abonentTicketService.create(abonentTicketRequest),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get abonent ticket by id", description = "Get abonent ticket by id")
    public ResponseMessage<AbonentTicketResponse> findById(@PathVariable Long ticketId) {
        return new ResponseMessage<>(
                abonentTicketService.findById(ticketId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/phone")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all ticket", description = "Get a list of all ticket")
    public ResponseMessage<List<AbonentTicketResponse>> findByPhoneNumber(@RequestParam("phone") String phone) throws MethodNotSupportedException {
        return new ResponseMessage<>(
                abonentTicketService.findByPhoneNumber(phone),
                ResultCode.SUCCESS
        );
    }
}
