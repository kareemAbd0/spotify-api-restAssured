package tests;

import API.artists.GetArtist;
import POJO.Artist;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
public class GetArtistTests extends BaseTest {


    @Test
    public void testGetArtistResponseCode() {

        GetArtist getArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");
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

        GetArtist getArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");
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

        GetArtist getArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");
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


        GetArtist getArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");
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

        GetArtist myGetArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");

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

        GetArtist myGetArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");

        given().
                baseUri(baseURL).
                auth().oauth2(token).
        when().
                get(myGetArtist.getEndPoint()).
        then().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("schemas/artist-schema-content.json"));
    }



    @Test
    public void testIfArtistNameIsMatching() {

        GetArtist myGetArtist = new GetArtist("0TnOYISbd1XYRBk9myaseg");

        Artist artistPojo;
        artistPojo = given().
                baseUri(baseURL).
                auth().oauth2(token).
                when().
                get(myGetArtist.getEndPoint()).
                then().
                statusCode(200).
                extract().as(Artist.class);

        assertThat(artistPojo.getName()).isEqualTo("Pitbull");
    }


}



