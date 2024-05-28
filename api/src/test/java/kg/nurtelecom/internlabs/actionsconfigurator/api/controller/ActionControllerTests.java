package kg.nurtelecom.internlabs.actionsconfigurator.api.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ActionControllerTests {

//    @InjectMocks
//    ActionController controller;
//
//    @Mock
//    ActionService service;
//
//    @Test
//    public void testSave() {
//        ActionResponse actionResponse = new ActionResponse(
//                1L,
//                "Action Name",
//                "Action Description"
//        );
//        ActionRequest actionRequest = ActionRequest.builder()
//                .name(actionResponse.name())
//                .description(actionResponse.description())
//                .build();
//
//        when(service.save(actionRequest))
//                .thenReturn(actionResponse);
//
//        ResponseMessage<ActionResponse> responseMessage = controller.save(actionRequest);
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isEqualTo(actionResponse);
//    }
//
//    @Test
//    public void testFindByIdIfDataExist() throws ActionNotFoundException {
//        Long existingId0 = 1L;
//        Long existingId1 = 2L;
//        Long existingId2 = 3L;
//
//        ActionResponse actionResponse0 = new ActionResponse(
//                existingId0,
//                "First Action Name",
//                "First Action Description"
//        );
//        ActionResponse actionResponse1 = new ActionResponse(
//                existingId1,
//                "Second Action Name",
//                "Second Action Description"
//        );
//        ActionResponse actionResponse2 = new ActionResponse(
//                existingId2,
//                "Third Action Name",
//                "Third Action Description"
//        );
//
//        when(service.findById(existingId0))
//                .thenReturn(actionResponse0);
//        when(service.findById(existingId1))
//                .thenReturn(actionResponse1);
//        when(service.findById(existingId2))
//                .thenReturn(actionResponse2);
//
//        ResponseMessage<ActionResponse> responseMessage = controller.findById(existingId0);
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isEqualTo(actionResponse0);
//
//        responseMessage = controller.findById(existingId1);
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isEqualTo(actionResponse1);
//
//        responseMessage = controller.findById(existingId2);
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isEqualTo(actionResponse2);
//    }
//
//    @Test
//    public void testFindByIdIfDataNotExist() {
//        try {
//            Long nonExistingId = 4L;
//
//            when(service.findById(nonExistingId))
//                    .thenThrow(new ActionNotFoundException());
//
//            controller.findById(nonExistingId);
//        } catch (Exception e) {
//            assertThat(e)
//                    .isInstanceOf(ActionNotFoundException.class);
//        }
//    }
//
//    @Test
//    public void testFindAllIfDataExist() throws MethodNotSupportedException {
//        List<ActionResponse> actionModelResponses = getActionModels();
//
//        when(service.findAll())
//                .thenReturn(actionModelResponses);
//
//        ResponseMessage<List<ActionResponse>> responseMessage = controller.findAll();
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isNotEmpty()
//                .isEqualTo(actionModelResponses);
//    }
//
//    @Test
//    public void testFindAllIfDataNotExist() throws MethodNotSupportedException {
//        when(service.findAll())
//                .thenReturn(new ArrayList<>());
//
//        ResponseMessage<List<ActionResponse>> responseMessage = controller.findAll();
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isNotNull()
//                .isEmpty();
//    }
//
//    @Test
//    public void testUpdateIfDataExist() throws ActionNotFoundException {
//        Long existingId = 1L;
//
//        ActionResponse actionResponse = new ActionResponse(
//                existingId,
//                "New Action Name",
//                "New Action Description"
//        );
//        ActionRequest actionRequest = ActionRequest.builder()
//                .name(actionResponse.name())
//                .description(actionResponse.description())
//                .build();
//
//        when(service.updateById(existingId, actionRequest))
//                .thenReturn(actionResponse);
//
//        ResponseMessage<ActionResponse> responseMessage = controller.updateById(existingId, actionRequest);
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isEqualTo(actionResponse);
//    }
//
//    @Test
//    public void testUpdateIfDataNotExist() {
//        try {
//            Long nonExistingId = 2L;
//
//            ActionRequest actionRequest = new ActionRequest(
//                    "Action Name",
//                    "Action Description"
//            );
//
//            when(service.updateById(nonExistingId, actionRequest))
//                    .thenThrow(new ActionNotFoundException());
//
//            controller.updateById(nonExistingId, actionRequest);
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
//        ActionResponse actionResponse = new ActionResponse(
//                existingId,
//                "Action Name",
//                "Action Description"
//        );
//
//        when(service.deleteById(existingId))
//                .thenReturn(actionResponse);
//
//        ResponseMessage<ActionResponse> responseMessage = controller.deleteById(existingId);
//        assertThat(responseMessage)
//                .isNotNull();
//        assertThat(responseMessage.getResultCode())
//                .isEqualTo(ResultCode.SUCCESS);
//        assertThat(responseMessage.getResult())
//                .isEqualTo(actionResponse);
//    }
//
//    @Test
//    public void testDeleteIfDataNotExist() {
//        try {
//            Long nonExistingId = 2L;
//
//            when(service.deleteById(nonExistingId))
//                    .thenThrow(new ActionNotFoundException());
//
//            controller.deleteById(nonExistingId);
//        } catch (Exception e) {
//            assertThat(e)
//                    .isInstanceOf(ActionNotFoundException.class);
//        }
//    }
//
//    private static List<ActionResponse> getActionModels() {
//        ActionResponse actionResponse0 = new ActionResponse(
//                1L,
//                "First Action Name",
//                "First Action Description"
//        );
//        ActionResponse actionResponse1 = new ActionResponse(
//                2L,
//                "Second Action Name",
//                "Second Action Description"
//        );
//        ActionResponse actionResponse2 = new ActionResponse(
//                3L,
//                "Third Action Name",
//                "Third Action Description"
//        );
//
//        List<ActionResponse> actionModelRequests = new ArrayList<>();
//        actionModelRequests.add(actionResponse0);
//        actionModelRequests.add(actionResponse1);
//        actionModelRequests.add(actionResponse2);
//        return actionModelRequests;
//    }
}

