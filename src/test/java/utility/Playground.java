package utility;

public class Playground {

    static void main() {

        Authentication auth = new Authentication("864ebfadf7a448c290bf03fc61b67248",
                                              "a483644ea6b74f379f7a2d5b9f6990b6");
        String token = auth.getToken();
        System.out.println(token);
    }


}
