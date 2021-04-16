import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SqlDataBaseConnection {

    private final String ConnectionString;
    private final String QueryString;

    public SqlDataBaseConnection(String ConnectionString, String QueryString) {
        this.ConnectionString = ConnectionString;
        this.QueryString = QueryString;
    }

    public ArrayList<Map<String, String>> Execute(){
        var dataList = new ArrayList<Map<String,String>>();
        try {
            var sqlConnection = DriverManager.getConnection(ConnectionString);
            var stmt= sqlConnection.createStatement();
            var result = stmt.executeQuery(QueryString);
            var data = result.getMetaData();
            var count = data.getColumnCount();

            while (result.next()) {
                var dataMap = new HashMap<String, String>();
                for (var i = 1; i <= count; i++) {
                    var columnName = data.getColumnName(i);
                    var dataName = result.getString(columnName);
                    dataMap.put(columnName, dataName);
                }
                dataList.add(dataMap);
            }
            sqlConnection.close();
        }
        catch (SQLException e){
            var str = e.getMessage();
            var dm = new HashMap<String, String>();
            dm.put("Error", str);
            dataList.add(dm);
        }
        return dataList;
    }
}
