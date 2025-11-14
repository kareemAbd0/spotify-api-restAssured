package tests;

import API.artists.GetArtist;
import POJO.Artist;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

public class GetArtistTests extends BaseTest {

    JsonNode artistNode;

    @BeforeClass
    public void setup() {
        artistNode = testData.get("artist");
    }

    @Test
    public void testGetArtistResponseCode() {

        String artistId = artistNode.get("id").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(getArtist.getEndPoint()).
        then().
                log().body().
                assertThat().
                statusCode(200);
    }

    @Test
    public void testGetArtistResponseHeaders() {

        String artistId = artistNode.get("id").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(getArtist.getEndPoint()).
                then()
                .log().body().
                assertThat().
                statusCode(200).
                headers("content-type","application/json; charset=utf-8");
    }


    @Test void testGetArtistResponseTime() {

        String artistId = artistNode.get("id").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given().
                baseUri(baseURL).
                auth().oauth2(token).
                when().
                get(getArtist.getEndPoint()).
        then().
                statusCode(200).
                time(lessThan(1000L));


    }


    @Test
    public void testGetArtistResponseBodySchema() {


        String artistId = artistNode.get("id").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(getArtist.getEndPoint()).
                then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/artist-schema-types.json"));
    }

    @Test
    public void testIfResponseIDEqualToRequestID() {

        String artistId = artistNode.get("id").asText();

        GetArtist myGetArtist = new GetArtist(artistId);

        Artist artistPojo;
       artistPojo = given().
                baseUri(baseURL).
                auth().oauth2(token).
                when().
                get(myGetArtist.getEndPoint()).
        then().
                statusCode(200).
                extract().as(Artist.class);

        assertThat(myGetArtist.getId()).isEqualTo(artistPojo.getId());
    }


    @Test
    public void testIfResponseHasEmptyStrings(){

        String artistId = artistNode.get("id").asText();

        GetArtist getArtist = new GetArtist(artistId);

        given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(getArtist.getEndPoint()).
        then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/artist-schema-content.json"));
    }



    @Test
    public void testIfArtistNameIsMatching() {

        String artistId = artistNode.get("id").asText();

        GetArtist getArtist = new GetArtist(artistId);

        Artist artistPojo;
        artistPojo = given().
                baseUri(baseURL).
                auth().oauth2(token).
                when().
                get(getArtist.getEndPoint()).
                then().
                statusCode(200).
                extract().as(Artist.class);

        assertThat(artistPojo.getName()).isEqualTo("Pitbull");
    }

    @Test
    public void testInvalidIDResponseCode() {
        String artistId = artistNode.get("invalid_id").asText();
        GetArtist getArtist = new GetArtist(artistId);

        given().
                baseUri(baseURL).
                auth().oauth2(token).
       when().
                get(getArtist.getEndPoint()).
       then().
                log().body().
                assertThat().
                statusCode(404);

    }

    @Test
    public void testInvalidIDResponseMessage() {
        String artistId = artistNode.get("invalid_id").asText();
        GetArtist getArtist = new GetArtist(artistId);
       Response resp = given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(getArtist.getEndPoint()).
        then().
                log().body().
                statusCode(404).extract().response();

       assertThat(resp.jsonPath().getString("error.message")).isEqualTo("Resource not found");
    }


}



