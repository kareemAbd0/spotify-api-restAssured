package tests;

import API.artists.GetArtist;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetArtistTests extends BaseTest {

    @Test
    public void testGetArtistResponseCode() {

        GetArtist getArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");
        Response resp = given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(getArtist.getEndPoint()).
        then()
                .log().body()
                .statusCode(200).extract().response();
    }


}
