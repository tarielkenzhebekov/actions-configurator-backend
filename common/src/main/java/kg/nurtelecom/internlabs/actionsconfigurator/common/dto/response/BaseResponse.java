package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.user.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class BaseResponse {
    Long id;

    Boolean deleted;

    LocalDateTime createdAt;

    Long createdBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime updatedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long updatedBy;
}
