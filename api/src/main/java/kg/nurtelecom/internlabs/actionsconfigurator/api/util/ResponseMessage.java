package kg.nurtelecom.internlabs.actionsconfigurator.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ResponseMessage<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    T result;

    ResultCode resultCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String details;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;

    public ResponseMessage(ResultCode resultCode) {
        this(null, resultCode, null);
    }

    public ResponseMessage(ResultCode resultCode, String details) {
        this(null, resultCode, details);
    }

    public ResponseMessage(T result, ResultCode resultCode) {
        this(result, resultCode, null);
    }

    public ResponseMessage(T result, ResultCode resultCode, String details) {
        this.result = result;
        this.resultCode = resultCode;
        this.details = details;
        this.message = null;
    }

    public ResponseMessage(T result, ResultCode resultCode, String details, String message) {
        this.result = result;
        this.resultCode = resultCode;
        this.details = details;
        this.message = message;
    }

    public boolean hasResult() {
        return result != null;
    }
}
