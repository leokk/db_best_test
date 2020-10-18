package example.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataBase {
    public void writeSql(List<List<Integer>> dataList, StringBuilder template, Connection conn) throws SQLException {
        if (conn != null) {
            try {
                Statement statement = conn.createStatement();
                for (List<Integer> pipes : dataList) {
//                    collect data for query
                    StringBuilder query = new StringBuilder(template);
//                    global prefix is used to replace first coma with ''
                    String prefix = "";
                    for (Integer pipe : pipes) {
                        query.append(prefix);
                        prefix = ",";
                        query.append(pipe.toString());
                    }
                    query.append(")");
                    statement.executeUpdate(query.toString());
                }
                System.out.println("Successfully insert to db");
            } catch (SQLException e) {
                System.out.println("error occurred when execute statement");
                e.printStackTrace();
                conn.close();
            }
        }
    }
}
