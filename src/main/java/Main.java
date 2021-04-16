import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(5002);
        post("/api", (request, response) -> {
            var stringResponse = Service.GetDataFromSql(request.body());
            response.status(200);
            response.type("application/json");
            return stringResponse;
        });
    }
}
