package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utility.Authentication;

public class BaseAPI {

    protected static final String baseURL = "https://api.spotify.com/v1";
    String token;

    public BaseAPI(Authentication authentication) {
        this.token = authentication.getToken();
    }


    public static String getBaseURL() {
        return baseURL;
    }

    public RequestSpecification getRequestSpecs() {
        return new RequestSpecBuilder().
                setBaseUri(baseURL).
                addHeader("content-type", "application/json ; charset=utf-8").
                setAuth(RestAssured.oauth2(token)).
                build();
    }
}
