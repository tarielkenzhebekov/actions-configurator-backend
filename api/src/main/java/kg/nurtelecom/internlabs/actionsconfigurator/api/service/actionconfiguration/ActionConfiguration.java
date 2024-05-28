package kg.nurtelecom.internlabs.actionsconfigurator.api.service.actionconfiguration;


import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class ActionConfiguration {
    Action action;
    Ticket ticketTemplate;
    Promocode promocodeTemplate;
    List<StageWrapper> stageWrappers;

    public List<Stage> unwrapStages() {
        List<Stage> stages = new ArrayList<>();

        for (StageWrapper stageWrapper : stageWrappers) {
            stages.add(
                    stageWrapper.stage
            );
        }

        return stages;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Getter
    @Setter
    public static class StageWrapper implements Comparable<StageWrapper> {

        Stage stage;
        List<Rule> rules;

        @Override
        public int compareTo(StageWrapper stageWrapper) {
            return stage.compareTo(stageWrapper.stage);
        }
    }
}