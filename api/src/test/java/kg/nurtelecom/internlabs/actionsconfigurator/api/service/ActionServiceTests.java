//package kg.nurtelecom.internlabs.actionsconfigurator.api.service;
//
//import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.action.ActionRepository;
//import kg.nurtelecom.internlabs.actionsconfigurator.api.service.action.impl.ActionServiceImpl;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.exception.ActionNotFoundException;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.mapper.ActionMapper;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.payload.request.create.ActionRequest;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.payload.response.ActionResponse;
//import org.assertj.core.api.Condition;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mapstruct.factory.Mappers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//import java.util.List;
//import java.util.ArrayList;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ActionServiceTests {
//
//    @InjectMocks
//    ActionServiceImpl actionService;
//
//    @Mock
//    ActionRepository actionRepository;
//
//    @Mock
//    ActionMapper actionMapper = Mappers.getMapper(ActionMapper.class);
//
//    @Test
//    public void testSave() {
//        Action savedEntity = new Action(
//                1L,
//                "Action Name",
//                "Action Description",
//                null
//        );
//        Action savingEntity = Action.builder()
//                .name(savedEntity.getName())
//                .description(savedEntity.getDescription())
//                .build();
//        ActionRequest request = ActionRequest.builder()
//                .name(savedEntity.getName())
//                .description(savedEntity.getDescription())
//                .build();
//        ActionResponse response = ActionResponse.builder()
//                .id(savedEntity.getId())
//                .name(savedEntity.getName())
//                .description(savedEntity.getDescription())
//                .build();
//
//        when(actionMapper.requestToEntity(request))
//                .thenReturn(savingEntity);
//        when(actionMapper.entityToResponse(savedEntity))
//                .thenReturn(response);
//        when(actionRepository.save(savingEntity))
//                .thenReturn(savedEntity);
//
//        response = actionService.save(request);
//        assertThat(response)
//                .isNotNull()
//                .is(
//                        new Condition<>(
//                                (responseModel) ->
//                                        responseModel.name().equals(request.name())
//                                                && responseModel.description().equals(request.description()),
//                                "Asserting that saved model and saving model has same content"
//                        )
//                );
//    }
//
//    @Test
//    public void testFindIfDataExist() {
//        Long existingId = 1L;
//
//        Action savedEntity = new Action(
//                1L,
//                "Action Name",
//                "Action Description",
//                null
//        );
//        ActionResponse actionResponse = ActionResponse.builder()
//                .id(savedEntity.getId())
//                .name(savedEntity.getName())
//                .description(savedEntity.getDescription())
//                .build();
//
//        when(actionMapper.entityToResponse(savedEntity))
//                .thenReturn(actionResponse);
//        when(actionRepository.findById(existingId))
//                .thenReturn(Optional.of(savedEntity));
//
//        try {
//            ActionResponse response = actionService.findById(existingId);
//            assertThat(response)
//                    .isNotNull()
//                    .isEqualTo(actionResponse);
//        } catch (Exception e) {
//
//        }
//    }
//
//    @Test
//    public void testFindIfDataNotExist() {
//        try {
//            Long nonExistingId = 2L;
//
//            when(actionRepository.findById(nonExistingId))
//                    .thenReturn(Optional.empty());
//
//            actionService.findById(nonExistingId);
//        } catch (Exception e) {
//            assertThat(e)
//                    .isInstanceOf(ActionNotFoundException.class);
//        }
//    }
//
//    @Test
//    public void testFindAllIfDataExist() {
//        List<Action> actions = getActions();
//
//        List<ActionResponse> expectedList = actions.stream()
//                .map(actionMapper::entityToResponse)
//                .toList();
//
//        when(actionRepository.findAll())
//                .thenReturn(actions);
//
//        List<ActionResponse> actionResponses = actionService.findAll();
//        assertThat(actionResponses)
//                .isEqualTo(expectedList);
//    }
//
//    @Test
//    public void testFindAllIfDataNotExist() {
//        when(actionRepository.findAll())
//                .thenReturn(new ArrayList<>());
//
//        List<ActionResponse> actionResponses = actionService.findAll();
//        assertThat(actionResponses)
//                .isNotNull()
//                .isEmpty();
//    }
//
//    @Test
//    public void testUpdateIfDataExist() throws ActionNotFoundException {
//        Long existingId = 1L;
//
//        Action savedAction = new Action(
//                existingId,
//                "Action Name",
//                "Action Description",
//                null
//        );
//        Action updatedAction = new Action(
//                existingId,
//                "New Action Name",
//                "New Action Description",
//                null
//        );
//        ActionRequest actionRequest = ActionRequest.builder()
//                .name(updatedAction.getName())
//                .description(updatedAction.getDescription())
//                .build();
//        ActionResponse actionResponse = ActionResponse.builder()
//                .id(updatedAction.getId())
//                .name(updatedAction.getName())
//                .description(updatedAction.getDescription())
//                .build();
//
//        when(actionRepository.findById(existingId))
//                .thenReturn(Optional.of(savedAction));
//        when(actionRepository.save(any(Action.class)))
//                .thenReturn(updatedAction);
//        when(actionMapper.entityToResponse(updatedAction))
//                .thenReturn(actionResponse);
//
//        ActionResponse response = actionService.updateById(existingId, actionRequest);
//        assertThat(response)
//                .isNotNull()
//                .isEqualTo(actionResponse);
//    }
//
//    @Test
//    public void testUpdateIfDataNotExist() {
//        try {
//            Long nonExistingId = 2L;
//
//            when(actionRepository.findById(nonExistingId))
//                    .thenReturn(Optional.empty());
//
//            actionService.updateById(
//                    nonExistingId,
//                    new ActionRequest(
//                            "Action Name",
//                            "Action Description"
//                    )
//            );
//        } catch (Exception e) {
//            assertThat(e)
//                    .isInstanceOf(ActionNotFoundException.class);
//        }
//    }
//
//    @Test
//    public void testDeleteIfDataExist() throws ActionNotFoundException {
//        Long existingId = 1L;
//
//        Action savedAction = new Action(
//                existingId,
//                "Action Name",
//                "Action Description",
//                null
//        );
//        ActionResponse expectedResponse = ActionResponse.builder()
//                .id(savedAction.getId())
//                .name(savedAction.getName())
//                .description(savedAction.getDescription())
//                .build();
//
//        when(actionRepository.findById(existingId))
//                .thenReturn(Optional.of(savedAction));
//        when(actionMapper.entityToResponse(savedAction))
//                .thenReturn(expectedResponse);
//
//        ActionResponse response = actionService.deleteById(existingId);
//        assertThat(response)
//                .isNotNull()
//                .isEqualTo(expectedResponse);
//    }
//
//    @Test
//    public void testDeleteIfDataNotExist() {
//        try {
//            Long nonExistingId = 2L;
//
//            when(actionRepository.findById(nonExistingId))
//                    .thenReturn(Optional.empty());
//
//            actionService.deleteById(nonExistingId);
//        } catch (Exception e) {
//            assertThat(e)
//                    .isInstanceOf(ActionNotFoundException.class);
//        }
//    }
//
//    private static List<Action> getActions() {
//        Action action0 = new Action(
//                1L,
//                "Action Name",
//                "Action Description",
//                null
//        );
//        Action action1 = new Action(
//                2L,
//                "Action Name",
//                "Action Description",
//                null
//        );
//        Action action2 = new Action(
//                3L,
//                "Action Name",
//                "Action Description",
//                null
//        );
//
//        List<Action> actions = new ArrayList<>();
//        actions.add(action0);
//        actions.add(action1);
//        actions.add(action2);
//        return actions;
//    }
//}
//
