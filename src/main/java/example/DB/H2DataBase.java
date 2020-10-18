package example.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class H2DataBase extends DataBase {
    private Connection conn = null;

    //    call method from super class
    public void writeSql(List<List<Integer>> dataList, StringBuilder template) throws SQLException {
        super.writeSql(dataList, template, conn);
    }

    public H2DataBase() {//manual test constructor
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.h2.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:h2:file:C:/Users/Eugene/test", "sa", "");

            System.out.println("good");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public H2DataBase(String path) {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:h2:file:" + path, "sa", "");

            System.out.println("good");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
