package kg.nurtelecom.internlabs.actionsconfigurator.api.controller.promocode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.promocode.PromocodeService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResponseMessage;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.promocode.PromocodeRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocodes")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Tag(name = "Promocode")
@DependsOn("promocodeService")
public class PromocodeController {

    PromocodeService promocodeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new promocode", description = "Create new promocode")
    public ResponseMessage<PromocodeResponse> create(@Valid @RequestBody PromocodeRequest promocodeRequest) {
        return new ResponseMessage<>(
                promocodeService.create(promocodeRequest),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/{promocodeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get promocode by id", description = "Get promocode by id")
    public ResponseMessage<PromocodeResponse> findById(@PathVariable Long promocodeId) {
        return new ResponseMessage<>(
                promocodeService.findById(promocodeId),
                ResultCode.SUCCESS
        );
    }

    @GetMapping("/find/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a list of all promocode", description = "Get a list of all promocode")
    public ResponseMessage<List<PromocodeResponse>> findAll() {
        return new ResponseMessage<>(
                promocodeService.findAll(),
                ResultCode.SUCCESS
        );
    }

    @PutMapping("/update/{promocodeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Change promocode by id", description = "Change promocode by id")
    public ResponseMessage<PromocodeResponse> updateById(@PathVariable Long promocodeId,
                                                         @Valid @RequestBody PromocodeRequest promocodeRequest) {

        return new ResponseMessage<>(
                promocodeService.updateById(promocodeId, promocodeRequest),
                ResultCode.SUCCESS
        );
    }

    @DeleteMapping("/delete/{promocodeId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete promocode by id", description = "Delete promocode by id")
    public ResponseMessage<PromocodeResponse> deleteById(@PathVariable Long promocodeId) {
        return new ResponseMessage<>(
                promocodeService.deleteById(promocodeId),
                ResultCode.SUCCESS
        );
    }

}
