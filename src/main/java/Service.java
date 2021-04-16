import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class Service {
    public static String GetDataFromSql(String newPost){
        Gson gson = new Gson();
        SqlDataBaseConnection db = gson.fromJson(newPost, SqlDataBaseConnection.class);
        var listData = db.Execute();
        var strReturn = gson.toJson(listData);
        return strReturn;
    }
}
