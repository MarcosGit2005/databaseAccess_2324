package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class MyDataSource {
    public static DataSource getMySQLDataSource() {
        Properties props = new Properties();
        MysqlDataSource ds = null;

        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);

            ds = new MysqlDataSource();
            ds.setUrl(props.getProperty("MYSQL_DB_URL"));
            ds.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            ds.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));

        } catch (Exception e){
            e.printStackTrace();
        }

        return ds;
    }

    public static Connection conectarMySql(){
        Connection con = null;

        try {
            DataSource ds = getMySQLDataSource();
            con = ds.getConnection();

            if (con!=null)
                System.out.println("Conexión establecida.");
        } catch (Exception e){
            e.printStackTrace();
        }

        return con;
    }

}
