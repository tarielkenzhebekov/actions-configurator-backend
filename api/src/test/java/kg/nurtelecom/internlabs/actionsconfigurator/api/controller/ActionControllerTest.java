package kg.nurtelecom.internlabs.actionsconfigurator.api.controller;

import kg.nurtelecom.internlabs.actionsconfigurator.api.service.auth.AuthenticationService;
import kg.nurtelecom.internlabs.actionsconfigurator.api.util.ResultCode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.request.auth.AuthenticationRequest;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.auth.AuthenticationResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestingWebApplicationTest {
    @LocalServerPort
    private int port;

    public String BASE_URL;
    public String accessToken;
    private static Integer createdActionID;

    @Autowired
    private TestRestTemplate restTemplate;


    public TestingWebApplicationTest(@Autowired AuthenticationService authenticationService) {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("admin@nurtelecom.kg")
                .password("123")
                .build();
        AuthenticationResponse authenticate = authenticationService.authenticate(authenticationRequest);
        accessToken = authenticate.getAccessToken();
        System.out.println(this);
    }

    @PostConstruct
    public void initUrl() {
        BASE_URL = "http://localhost:" + port + "/api";
    }

    @Test
    @Order(value = 1)
    void createAndGetAction(){
        Action action = Action.builder()
                .name("Test action")
                .description("Test description")
                .startDate(LocalDateTime.of(2024, 2, 28, 19, 0, 1))
                .endDate(LocalDateTime.of(2024, 3, 5, 20, 0, 1))
                .build();

//        System.out.println("action = " + action);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // You need to first get the accessToken for user that needed and paste it on the top;
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<Action> request = new HttpEntity<>(action, headers);
//        System.out.println("request = " + request);


        assertDoesNotThrow(() -> {
            ResponseEntity<String> exchange = restTemplate.exchange(new URI(BASE_URL + "/action" + "/create"), HttpMethod.POST, request, String.class);

            System.out.println("exchange body = " + exchange.getBody());
            System.out.println("exchange status code = " + exchange.getStatusCode());

            assertNotNull(exchange);
            assertEquals(HttpStatus.CREATED, exchange.getStatusCode());

            JSONObject responseBody = new JSONObject(exchange.getBody());
            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));

            assertNotNull(responseBody);
            assertEquals(action.getName(), result.get("name"));
            assertEquals(action.getDescription(), result.get("description"));
            assertEquals(action.getStartDate().toString(), result.get("startDate"));
            assertEquals(action.getEndDate().toString(), result.get("endDate"));
            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));

            createdActionID = (Integer) result.get("id");
            System.out.println(createdActionID);
        });

        assertDoesNotThrow(() -> {

//            System.out.println(actionService.findById(Long.valueOf(createdActionID)));

            ResponseEntity<String> exchange = restTemplate.exchange(new URI(BASE_URL + "/action" + "/find" + "/" + createdActionID), HttpMethod.GET, request, String.class);

            System.out.println("getAction body = " + exchange.getBody());
            System.out.println("getAction status code = " + exchange.getStatusCode());

            assertNotNull(exchange);
            assertEquals(HttpStatus.OK, exchange.getStatusCode());

            JSONObject responseBody = new JSONObject(exchange.getBody());
            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));

            assertNotNull(responseBody);
            assertEquals(action.getName(), result.get("name"));
            assertEquals(action.getDescription(), result.get("description"));
            assertEquals(action.getStartDate().toString(), result.get("startDate"));
            assertEquals(action.getEndDate().toString(), result.get("endDate"));
            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));
        });
    }

    @Test
    @Order(value = 2)
    void updateAndGetAction(){
        Action updatedAction = Action.builder()
                .name("Updated action")
                .description("updated description")
                .startDate(LocalDateTime.of(2024, 4, 25, 0, 0, 1))
                .endDate(LocalDateTime.of(2024, 6, 5, 0, 0, 1))
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<Action> request = new HttpEntity<>(updatedAction, headers);
//        System.out.println("created action id = " + createdActionID);

        assertDoesNotThrow(() -> {
            ResponseEntity<String> exchange = restTemplate.exchange(new URI(BASE_URL + "/action" + "/update" + "/" + createdActionID), HttpMethod.PUT, request, String.class);

            System.out.println("updated Action body = " + exchange.getBody());
            System.out.println("updated Action status code = " + exchange.getStatusCode());

            assertNotNull(exchange);
            assertEquals(HttpStatus.OK, exchange.getStatusCode());

            JSONObject responseBody = new JSONObject(exchange.getBody());
            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));

            assertNotNull(responseBody);
            assertEquals(updatedAction.getName(), result.get("name"));
            assertEquals(updatedAction.getDescription(), result.get("description"));
            assertEquals(updatedAction.getStartDate().toString(), result.get("startDate"));
            assertEquals(updatedAction.getEndDate().toString(), result.get("endDate"));
            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));
        });

        assertDoesNotThrow(() -> {
            ResponseEntity<String> exchange = restTemplate.exchange(new URI(BASE_URL + "/action" + "/find" + "/" + createdActionID), HttpMethod.GET, request, String.class);

            System.out.println("getAction body = " + exchange.getBody());
            System.out.println("getAction status code = " + exchange.getStatusCode());

            assertNotNull(exchange);
            assertEquals(HttpStatus.OK, exchange.getStatusCode());

            JSONObject responseBody = new JSONObject(exchange.getBody());
            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));

            assertNotNull(responseBody);
            assertEquals(updatedAction.getName(), result.get("name"));
            assertEquals(updatedAction.getDescription(), result.get("description"));
            assertEquals(updatedAction.getStartDate().toString(), result.get("startDate"));
            assertEquals(updatedAction.getEndDate().toString(), result.get("endDate"));
            assertNotNull(result.get("updatedAt"));
            assertNotNull(result.get("updaterId"));
            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));
        });
    }

    @Test
    @Order(value = 3)
    void deleteAction(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<Action> request = new HttpEntity<>(headers);

        assertDoesNotThrow(() -> {
            ResponseEntity<String> exchange = restTemplate.exchange(new URI(BASE_URL + "/action" + "/delete" + "/" + createdActionID), HttpMethod.DELETE, request, String.class);

            System.out.println("created action id = " + createdActionID);
            System.out.println("url = " + new URI(BASE_URL + "/action" + "/delete" + "/" + createdActionID));
            System.out.println("deleted Action body = " + exchange.getBody());
            System.out.println("deleted Action status code = " + exchange.getStatusCode());

            assertNotNull(exchange);
            assertEquals(HttpStatus.OK, exchange.getStatusCode());

            JSONObject responseBody = new JSONObject(exchange.getBody());
            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));

            System.out.println(result.get("deleted").getClass().getName());

            assertNotNull(responseBody);
            assertTrue((Boolean) result.get("deleted"));
            assertEquals(ResultCode.SUCCESS.toString(), responseBody.get("resultCode"));
        });

        assertDoesNotThrow(() -> {
            ResponseEntity<String> exchange = restTemplate.exchange(new URI(BASE_URL + "/action" + "/find" + "/" + createdActionID), HttpMethod.GET, request, String.class);

            System.out.println("get deleted Action body = " + exchange.getBody());
            System.out.println("get deleted Action status code = " + exchange.getStatusCode());

            assertNotNull(exchange);
            assertEquals(HttpStatus.OK, exchange.getStatusCode());

            JSONObject responseBody = new JSONObject(exchange.getBody());
            JSONObject result = new JSONObject(String.valueOf(responseBody.getJSONObject("result")));

            assertNotNull(responseBody);
            assertTrue((Boolean) result.get("deleted"));
        });
    }

}

