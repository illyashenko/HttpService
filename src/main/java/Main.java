import static spark.Spark.*;

public class Main {

    private static final int CODE_OK = 200;

    public static void main(String[] args) {
        port(5002);
        post("/api", (request, response) -> {
            var stringResponse = Service.GetDataFromSql(request.body());
            response.status(CODE_OK);
            response.type("application/json");
            return stringResponse;
        });
        get("/about", ((request, response) -> {
            response.status(CODE_OK);
            return "it's work";
        }));
    }
}
