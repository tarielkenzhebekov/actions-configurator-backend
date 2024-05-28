package kg.nurtelecom.internlabs.actionsconfigurator.common.dto.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.action.ActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration.ActionConfigurationResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.actionconfiguration.StageConfigurationResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.promocode.PromocodeResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.rule.RuleResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.stage.StageResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.ticket.TicketResponse;

import java.io.IOException;
import java.util.List;

public class ActionConfigurationResponseSerializer extends StdSerializer<ActionConfigurationResponse> {

    public ActionConfigurationResponseSerializer() {
        this(null);
    }

    public ActionConfigurationResponseSerializer(Class<ActionConfigurationResponse> t) {
        super(t);
    }

    @Override
    public void serialize(ActionConfigurationResponse actionConfigurationResponse,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();


        serializeAction(actionConfigurationResponse.getAction(), jsonGenerator);

        if (actionConfigurationResponse.getTicketTemplate() != null) {
            serializeTicket(actionConfigurationResponse.getTicketTemplate(), jsonGenerator);
        }
        if (actionConfigurationResponse.getPromcoodeTemplate() != null) {
            serializePromocode(actionConfigurationResponse.getPromcoodeTemplate(), jsonGenerator);
        }

        serializeStages(actionConfigurationResponse.getStages(), jsonGenerator);

        jsonGenerator.writeObjectField("deleted", actionConfigurationResponse.getAction().getDeleted());
        jsonGenerator.writeObjectField("createdAt", actionConfigurationResponse.getCreatedAt());
        jsonGenerator.writeObjectField("createdBy", actionConfigurationResponse.getCreatedBy());

        if (actionConfigurationResponse.getUpdatedAt() != null) {
            jsonGenerator.writeObjectField("updatedAt", actionConfigurationResponse.getUpdatedAt().toString());
        }
        if (actionConfigurationResponse.getUpdatedBy() != null) {
            jsonGenerator.writeObjectField("updatedBy", actionConfigurationResponse.getUpdatedBy().toString());
        }


        jsonGenerator.writeEndObject();
    }

    public void serializeAction(ActionResponse actionResponse, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart("action");

        jsonGenerator.writeObjectField("id", actionResponse.getId());
        jsonGenerator.writeObjectField("name", actionResponse.getName());
        jsonGenerator.writeObjectField("description", actionResponse.getDescription());
        jsonGenerator.writeObjectField("type", actionResponse.getType());
        jsonGenerator.writeObjectField("startDate", actionResponse.getStartDate());
        jsonGenerator.writeObjectField("endDate", actionResponse.getEndDate());
        jsonGenerator.writeObjectField("activated", actionResponse.getActivated());

        jsonGenerator.writeEndObject();
    }

    public void serializeTicket(TicketResponse ticketResponse, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart("ticketTemplate");

        jsonGenerator.writeObjectField("id", ticketResponse.getId());
        jsonGenerator.writeObjectField("startDate", ticketResponse.getStartDate());
        jsonGenerator.writeObjectField("endDate", ticketResponse.getEndDate());

        jsonGenerator.writeEndObject();
    }

    public void serializePromocode(PromocodeResponse promocodeResponse, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeObjectFieldStart("promocodeTemplate");

        jsonGenerator.writeObjectField("id", promocodeResponse.getId());
        jsonGenerator.writeObjectField("expiredDate", promocodeResponse.getExpiredDate());

        jsonGenerator.writeEndObject();
    }

    public void serializeStages(List<StageConfigurationResponse> stageConfigurationResponses, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeArrayFieldStart("stages");

        for (StageConfigurationResponse response : stageConfigurationResponses) {
            serializeStage(response, jsonGenerator);
        }

        jsonGenerator.writeEndArray();
    }

    public void serializeStage(StageConfigurationResponse stageConfigurationResponse, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        StageResponse stageResponse = stageConfigurationResponse.getStage();
        jsonGenerator.writeObjectField("id", stageResponse.getId());
        jsonGenerator.writeObjectField("startDate", stageResponse.getStartDate());
        jsonGenerator.writeObjectField("endDate", stageResponse.getEndDate());
        jsonGenerator.writeObjectField("activated", stageResponse.getActivated());

        serializeRules(stageConfigurationResponse.getRules(), jsonGenerator);

        jsonGenerator.writeEndObject();
    }

    public void serializeRules(List<RuleResponse> ruleResponses, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeArrayFieldStart("rules");

        for (RuleResponse response : ruleResponses) {
            serializeRule(response, jsonGenerator);
        }

        jsonGenerator.writeEndArray();
    }

    public void serializeRule(RuleResponse ruleResponse, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("id", ruleResponse.getId());
        jsonGenerator.writeObjectField("minSum", ruleResponse.getMinSum());
        jsonGenerator.writeObjectField("maxSum", ruleResponse.getMaxSum());

        jsonGenerator.writeEndObject();
    }
}
