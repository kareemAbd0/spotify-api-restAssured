package tests.artists;

import API.artists.GetSeveralArtists;
import POJO.artists.ArtistPOJO;
import POJO.artists.SeveralArtistsPOJO;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class GetSeveralArtistsTests extends BaseTest {

    JsonNode artistNode;
    List<String> validIDsList;
    @BeforeClass
    public void setup() {
        artistNode = testData.get("artist");
        validIDsList = new ArrayList<>();
        validIDsList.add(artistNode.get("id1").asText());
        validIDsList.add(artistNode.get("id2").asText());
        validIDsList.add(artistNode.get("id3").asText());
    }



    @Test
    void TestSeveralArtistsResponseCode() {


        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
        when().
                get(getSeveralArtists.getEndPoint()).
        then().
                log().body().
                assertThat().
                statusCode(200);
    }

    @Test
    void TestGetSeveralArtistsResponseHeaders(){

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
        when().
                get(getSeveralArtists.getEndPoint()).
        then().
                assertThat().
                headers("content-type","application/json; charset=utf-8");

    }

    @Test
    void getSeveralArtistsResponseTime(){
        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
        when().
                get(getSeveralArtists.getEndPoint()).
        then().
        assertThat().
                time(lessThan(1000L));

    }

    @Test
    void testGetSeveralArtistsResponseBodySchema(){

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
       when().
                get(getSeveralArtists.getEndPoint()).
       then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/several-artists-schema-types.json"));

    }

    @Test
    void testGetSeveralArtistsIDisMatching(){

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        SeveralArtistsPOJO response;

        response = given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
        when().
                get(getSeveralArtists.getEndPoint()).
        then().
                statusCode(200).
                extract().as(SeveralArtistsPOJO.class);

     assertThat(response.getArtists()).
             extracting(ArtistPOJO::getId).
             containsExactly(
                    artistNode.get("id1").asText(),
                    artistNode.get("id2").asText(),
                    artistNode.get("id3").asText()
            );


    }

    @Test
    void testGetSeveralArtistsNamesIsMatching(){

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        SeveralArtistsPOJO response;

        response = given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
        when().
                get(getSeveralArtists.getEndPoint()).
        then().
                statusCode(200).
                extract().as(SeveralArtistsPOJO.class);

        assertThat(response.getArtists()).
                extracting(ArtistPOJO::getName).
                containsExactly(
                        artistNode.get("name1").asText(),
                        artistNode.get("name2").asText(),
                        artistNode.get("name3").asText()
                );


    }

    @Test
    void testGetSeveralArtistsIfResponseHasEmptyStrings(){

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(validIDsList);

        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
        when().
                get(getSeveralArtists.getEndPoint()).
        then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/several-artists-schema-content.json"));
    }

    @Test
    void testGetSeveralArtistsInvalidResponseCode(){

        List<String> invalidIdsList = new ArrayList<>();
        invalidIdsList.add(artistNode.get("invalid_id").asText());
        invalidIdsList.add(artistNode.get("invalid_id").asText());
        invalidIdsList.add(artistNode.get("invalid_id").asText());

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(invalidIdsList);
        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
                when().
                get(getSeveralArtists.getEndPoint()).
                then().
                log().body().
                assertThat().
                statusCode(404);

    }


    @Test
    void testGetSeveralArtistsInvalidResponseCodeMessage(){

        List<String> invalidIdsList = new ArrayList<>();
        invalidIdsList.add(artistNode.get("invalid_id").asText());
        invalidIdsList.add(artistNode.get("invalid_id").asText());
        invalidIdsList.add(artistNode.get("invalid_id").asText());

        GetSeveralArtists getSeveralArtists = new GetSeveralArtists(invalidIdsList);
        given(baseAPI.getRequestSpecs()).
                queryParam("ids", getSeveralArtists.getCommaSeparatedIds()).
                when().
                get(getSeveralArtists.getEndPoint()).
                then().
                log().body().
                assertThat().
                statusCode(404);

    }
















}
