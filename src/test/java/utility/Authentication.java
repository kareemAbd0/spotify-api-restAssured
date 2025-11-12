package utility;

import POJO.CreateTokenPOJO;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;

public class Authentication{

    private static final Logger log = LoggerFactory.getLogger(Authentication.class);
    CreateTokenPOJO tokenBody;
    private String token;

    public Authentication(String clientId, String clientSecret) {
        tokenBody = new CreateTokenPOJO();
        tokenBody.setClient_id(clientId);
        tokenBody.setClient_secret(clientSecret);
        tokenBody.setGrant_type("client_credentials");
    }

    public String getToken() {

        Response resp = given().
                header("content-type", "application/x-www-form-urlencoded;").
                formParam("grant_type", "client_credentials").
                formParam("client_id", tokenBody.getClient_id()).
                formParam("client_secret", tokenBody.getClient_secret()).
                log().all().
        when().
                post("https://accounts.spotify.com/api/token").

        then().
                log().body().extract().response();

        token = resp.jsonPath().get("access_token");

        return token;
    }
}
