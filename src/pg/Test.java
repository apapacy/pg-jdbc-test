package pg;

import org.postgresql.*;
import java.sql.*;
import java.util.*;
/**
 * Created by apapacy on 7/8/17.
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++)
            (new MWorker(i)).start();
        System.out.println("OK");
    }

}

class MWorker extends Thread {
    int i;
    MWorker(int i) {
        this.i = i;
    }
    public void run() {
        try {
            Class.forName("org.postgresql.Driver");
            Class.forName("com.orientechnologies.orient.jdbc.OrientJdbcDriver");
            Class.forName("com.orientechnologies.orient.jdbc.OrientJdbcConnection");
            String url = "jdbc:postgresql://localhost/www";
            url = "jdbc:orient:remote:localhost/test";
            Properties props = new Properties();
            props.setProperty("user","admin");
            props.setProperty("password","admin");
            props.setProperty("ssl","true");
            Connection connection = DriverManager.getConnection(url, props);
            Statement statement = connection.createStatement();
            for (int i = 0; i < 1; i++)
                statement.execute(String.format("insert into test (id, name) values (%d, 'Харьков - %d')", i, i));
            System.out.println("End " + this.i);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
