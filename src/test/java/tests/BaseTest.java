package tests;
import API.BaseAPI;
import org.testng.annotations.BeforeSuite;
import utility.Authentication;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected BaseAPI baseAPI;
    public JsonNode testData;

    @BeforeSuite
    public void loadData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/data/data.json");
        testData = objectMapper.readTree(file);
    }

    @BeforeClass
    public void BaseSetup(){

        JsonNode authorizationNode = testData.get("authorization");
        String clientId = authorizationNode.get("client_id").asText();
        String clientSecret = authorizationNode.get("client_secret").asText();
        Authentication auth = new Authentication(clientId, clientSecret);
        baseAPI = new BaseAPI(auth);
    }
}

