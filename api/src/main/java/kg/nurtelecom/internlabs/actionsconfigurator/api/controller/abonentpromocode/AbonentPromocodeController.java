package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.abonentpromocode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.abonentpromocode.AbonentPromocodeService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.abonentpromocode.AbonentPromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.MethodNotSupportedException;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.abonentpromocode.AbonentPromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abonent-promocode")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "Abonent promocode")
public class AbonentPromocodeController {

    AbonentPromocodeService abonentPromocodeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new abonent promocode", description = "Create new promocode for abonent")
    public ResponseMessage<List<AbonentPromocodeResponse>> create(@Valid @RequestBody AbonentPromocodeRequest abonentPromocodeRequest) {
        return new ResponseMessage<>(
                abonentPromocodeService.create(abonentPromocodeRequest),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/{promocodeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get promocode by id", description = "Get promocode by id")
    public ResponseMessage<AbonentPromocodeResponse> findById(@PathVariable Long promocodeId) {
        return new ResponseMessage<>(
                abonentPromocodeService.findById(promocodeId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/phone")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all promocode by phone number", description = "Get a list of all promocode by phone number")
    public ResponseMessage<List<AbonentPromocodeResponse>> findByPhoneNumber(@RequestParam("phone") String phone) throws MethodNotSupportedException {
        return new ResponseMessage<>(
                abonentPromocodeService.findByPhoneNumber(phone),
                ResultCode.SUCCESS
        );
    }
}
