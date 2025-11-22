package tests.artists;

import API.artists.GetArtist;
import POJO.artists.ArtistPOJO;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class GetArtistTests extends BaseTest {

    JsonNode artistNode;

    @BeforeClass
    public void setup() {
        artistNode = testData.get("artist");
    }

    @Test
    public void testGetArtistResponseCode() {

        String artistId = artistNode.get("id1").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given(baseAPI.getRequestSpecs()).
        when().
                get(getArtist.getEndPoint()).
        then().
                log().body().
                assertThat().
                statusCode(200);
    }

    @Test
    public void testGetArtistResponseHeaders() {

        String artistId = artistNode.get("id1").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given(baseAPI.getRequestSpecs()).
        when().
                get(getArtist.getEndPoint()).
        then()
                .log().body().
                assertThat().
                statusCode(200).
                headers("content-type","application/json; charset=utf-8");
    }


    @Test void testGetArtistResponseTime() {

        String artistId = artistNode.get("id1").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given(baseAPI.getRequestSpecs()).
                when().
                get(getArtist.getEndPoint()).
        then().
                statusCode(200).
                time(lessThan(1000L));
    }


    @Test
    public void testGetArtistResponseBodySchema() {

        String artistId = artistNode.get("id1").asText();

        GetArtist getArtist = new GetArtist(artistId);
        given(baseAPI.getRequestSpecs()).
        when().
                get(getArtist.getEndPoint()).
        then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/artist-schema-types.json"));
    }

    @Test
    public void testGetArtistIfResponseIDEqualToRequestID() {

        String artistId = artistNode.get("id1").asText();

        GetArtist myGetArtist = new GetArtist(artistId);

        ArtistPOJO artistPojo;
       artistPojo = given(baseAPI.getRequestSpecs()).
                when().
                get(myGetArtist.getEndPoint()).
        then().
                statusCode(200).
                extract().as(ArtistPOJO.class);

        assertThat(myGetArtist.getId()).isEqualTo(artistPojo.getId());
    }


    @Test
    public void testGetArtistIfResponseHasEmptyStrings(){

        String artistId = artistNode.get("id1").asText();

        GetArtist getArtist = new GetArtist(artistId);

        given(baseAPI.getRequestSpecs()).
        when().
                get(getArtist.getEndPoint()).
        then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/artist-schema-content.json"));
    }



    @Test
    public void testGetArtistIfArtistNameIsMatching() {

        String artistId = artistNode.get("id1").asText();

        GetArtist getArtist = new GetArtist(artistId);

        ArtistPOJO artistPojo;
        artistPojo = given(baseAPI.getRequestSpecs()).
                when().
                get(getArtist.getEndPoint()).
        then().
                statusCode(200).
                extract().as(ArtistPOJO.class);

        assertThat(artistPojo.getName()).isEqualTo(artistNode.get("name1").asText());
    }

    @Test
    public void testGetArtistInvalidIDResponseCode() {
        String artistId = artistNode.get("invalid_id").asText();
        GetArtist getArtist = new GetArtist(artistId);

        given(baseAPI.getRequestSpecs()).
       when().
                get(getArtist.getEndPoint()).
       then().
                log().body().
                assertThat().
                statusCode(404);

    }

    @Test
    public void testGetArtistInvalidIDResponseMessage() {
        String artistId = artistNode.get("invalid_id").asText();
        GetArtist getArtist = new GetArtist(artistId);
       Response resp = given(baseAPI.getRequestSpecs()).
        when().
                get(getArtist.getEndPoint()).
        then().
                log().body().
                statusCode(404).extract().response();

       assertThat(resp.jsonPath().getString("error.message")).isEqualTo("Resource not found");
    }


}



