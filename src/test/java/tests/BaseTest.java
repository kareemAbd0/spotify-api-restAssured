package tests;
import utility.Authentication;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected String token;
    protected final String baseURL = "https://api.spotify.com/v1";

    @BeforeClass
    public void getToken(){
        Authentication auth = new Authentication("864ebfadf7a448c290bf03fc61b67248",
                "a483644ea6b74f379f7a2d5b9f6990b6");
         token = auth.getToken();
    }
}

