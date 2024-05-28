//package kg.nurtelecom.internlabs.actionsconfigurator.api.controller;
//
//import kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth.AuthenticationService;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.Action;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.payload.request.AuthenticationRequest;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.payload.request.StageRequest;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.payload.response.AuthenticationResponse;
//import kg.nurtelecom.internlabs.actionsconfigurator.common.response.ResultCode;
//import org.json.JSONObject;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//import javax.annotation.PostConstruct;
//
//import java.net.URI;
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class StageControllerTest {
//    @LocalServerPort
//    private int port;
//
//    public String BASE_URL;
//    public String accessToken;
//    private Integer createdActionID;
//    private Integer createdStageID;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//
//    public StageControllerTest(@Autowired AuthenticationService authenticationService) {
//        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
//                .email("admin@nurtelecom.kg")
//                .password("qwe123!@#")
//                .build();
//        AuthenticationResponse authenticate = authenticationService.authenticate(authenticationRequest);
//        accessToken = authenticate.getAccessToken();
//        System.out.println(this);
//    }
//
//    @PostConstruct
//    public void initUrl() {
//        BASE_URL = "http://localhost:" + port + "/api";
//    }
//
//
//    @Test
//    @Order(value = 1)
//    void createStage() {
//        Action action = Action.builder()
//                .name("Test action")
//                .description("Test description")
//                .startDate(LocalDateTime.of(2024, 2, 28, 19, 0, 1))
//                .endDate(LocalDateTime.of(2024, 3, 5, 20, 0, 1))
//                .build();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<Action> requestAction = new HttpEntity<>(action, headers);
//
//        assertDoesNotThrow(() -> {
//            ResponseEntity<String> responseAction = restTemplate.exchange(new URI(BASE_URL + "/action" + "/create"), HttpMethod.POST, requestAction, String.class);
//
//            JSONObject responseBody = new JSONObject(responseAction.getBody());
//            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));
//            System.out.println("response Action status code = " + responseAction.getStatusCode());
//            System.out.println("response Action body = " + responseAction.getBody());
//
//            assertNotNull(responseAction);
//            assertNotNull(result.get("id"));
//            assertEquals(HttpStatus.CREATED, responseAction.getStatusCode());
//
//            createdActionID = (Integer) result.get("id");
//            System.out.println(result.get("id"));
//        });
//
//
//        StageRequest stageRequest = StageRequest.builder()
//                .startDate(LocalDateTime.of(2024, 2, 28, 19, 0, 1))
//                .endDate(LocalDateTime.of(2024, 3, 5, 20, 0, 1))
//                .actionId(Long.valueOf(createdActionID))
//                .build();
//
//        System.out.println("Stage = " + stageRequest);
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<StageRequest> requestStage = new HttpEntity<>(stageRequest, headers);
//
//        assertDoesNotThrow(() ->{
//            ResponseEntity<String> responseStage = restTemplate.exchange(new URI(BASE_URL + "/stage" + "/create"), HttpMethod.POST, requestStage,  String.class);
//
//            assertNotNull(responseStage);
//            assertNotNull(responseStage.getBody());
//            assertEquals(HttpStatus.CREATED, responseStage.getStatusCode());
//
//            System.out.println("responseStage = " + responseStage);
//            System.out.println("responseStage body = " + responseStage.getBody());
//
//            JSONObject responseBody = new JSONObject(responseStage.getBody());
//            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));
//
//            assertEquals(stageRequest.startDate().toString(), result.get("startDate"));
//            assertEquals(stageRequest.endDate().toString(), result.get("endDate"));
//            assertEquals(createdActionID, result.get("actionId"));
//
//            createdStageID = (Integer) result.get("id");
//
////            getStageById(result);
//        });
//    }
//
//    @Test
//    @Order(value = 2)
//    void getStageById(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<HttpHeaders> httpRequest = new HttpEntity<>(headers);
//
//        assertDoesNotThrow(() -> {
//            ResponseEntity<String> stageResponse = restTemplate.exchange(new URI(BASE_URL + "/action" + "/find/" + createdStageID), HttpMethod.GET, httpRequest, String.class);
//
//            System.out.println("stageResponse = " + stageResponse);
//            assertNotNull(stageResponse);
//            assertNotNull(stageResponse.getBody());
//
//            JSONObject responseBody = new JSONObject(stageResponse.getBody());
//            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));
//
//            System.out.println("result = " + result);
//
//            assertNotNull(result);
//            assertNotNull(result.get("startDate"));
//            assertNotNull(result.get("endDate"));
////            assertNotNull(result.get("actionId"));
//            assertNotNull(result.get("createdAt"));
//            assertNotNull(result.get("authorId"));
////            assertEquals(createdStage.get("startDate"), result.get("startDate"));
////            assertEquals(createdStage.get("endDate"), result.get("endDate"));
////            assertEquals(createdStage.get("actionId"), result.get("actionId"));
////            assertEquals(createdStage.get("authorId"), result.get("authorId"));
////            assertEquals(createdStage.get("createdAt"), result.get("createdAt"));
//        });
//    }
//
//    @Test
//    @Order(value = 4)
//    void updateById() {
//        Action action = Action.builder()
//                .name("Updated action")
//                .description("Updated description to test the updating of stages")
//                .startDate(LocalDateTime.of(2024, 4, 28, 19, 0, 1))
//                .endDate(LocalDateTime.of(2024, 5, 28, 19, 0, 1))
//                .build();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<Action> requestAction = new HttpEntity<>(action, headers);
//
//        assertDoesNotThrow(() -> {
//            ResponseEntity<String> responseAction = restTemplate.exchange(new URI(BASE_URL + "/action" + "/create"), HttpMethod.POST, requestAction, String.class);
//
//            JSONObject responseBody = new JSONObject(responseAction.getBody());
//            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));
//            System.out.println("response Action status code = " + responseAction.getStatusCode());
//            System.out.println("response Action body = " + responseAction.getBody());
//
//            assertNotNull(responseAction);
//            assertNotNull(result.get("id"));
//            assertEquals(HttpStatus.CREATED, responseAction.getStatusCode());
//
//            createdActionID = (Integer) result.get("id");
//            System.out.println(result.get("id"));
//        });
//
//
//        StageRequest updatedStageRequest = StageRequest.builder()
//                .startDate(LocalDateTime.of(2024, 3, 28, 19, 0, 1))
//                .endDate(LocalDateTime.of(2024, 4, 28, 19, 0, 1))
//                .actionId(Long.valueOf(createdActionID))
//                .build();
//
//        System.out.println("Stage = " + updatedStageRequest);
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<StageRequest> requestStage = new HttpEntity<>(updatedStageRequest, headers);
//
//        assertDoesNotThrow(() ->{
//            ResponseEntity<String> responseStage = restTemplate.exchange(new URI(BASE_URL + "/stage" + "/update/" + createdStageID), HttpMethod.PUT, requestStage,  String.class);
//
//            System.out.println(responseStage);
//
//            assertNotNull(responseStage);
//            assertNotNull(responseStage.getBody());
//            assertEquals(HttpStatus.OK, responseStage.getStatusCode());
//
//            System.out.println("updated responseStage = " + responseStage);
//            System.out.println("updated responseStage body = " + responseStage.getBody());
//
//            JSONObject responseBody = new JSONObject(responseStage.getBody());
//            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));
//
//            assertEquals(updatedStageRequest.startDate().toString(), result.get("startDate"));
//            assertEquals(updatedStageRequest.endDate().toString(), result.get("endDate"));
//            assertEquals(createdActionID, result.get("actionId"));
//            assertNotNull(result.get("updatedAt"));
//            assertNotNull(result.get("updaterId"));
//            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));
//        });
//    }
//
//
//    @Test
//    @Order(value = 3)
//    void deleteStageById() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<HttpHeaders> requestStage = new HttpEntity<>(headers);
//
//        assertDoesNotThrow(() -> {
//            ResponseEntity<String> responseStage = restTemplate.exchange(new URI(BASE_URL + "/stage" + "/delete/" + createdStageID), HttpMethod.DELETE, requestStage,  String.class);
//
//            System.out.println("deleted responseStage = " + responseStage);
//
//            assertNotNull(responseStage);
//            assertNotNull(responseStage.getBody());
//            assertEquals(HttpStatus.OK, responseStage.getStatusCode());
//
//            System.out.println("deleted responseStage body = " + responseStage.getBody());
//
//            JSONObject responseBody = new JSONObject(responseStage.getBody());
//            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));
//
//            assertNotNull(result.get("updaterId"));
//            assertTrue((Boolean) result.get("deleted"));
//            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));
//        });
//    }
//
//}