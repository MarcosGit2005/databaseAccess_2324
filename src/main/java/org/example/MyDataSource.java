package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;
import oracle.jdbc.datasource.impl.OracleDataSource;

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
    public static DataSource getOracleDataSource() {
        // Propiedades donde tenemos los datos de acceso a la BD
        Properties props = new Properties();
        // Objeto DataSource que devolveremos
        OracleDataSource oracleDS = null;
        try (FileInputStream fis = new FileInputStream("db.properties");) {
            // Cargamos las propiedades
            props.load(fis);
            // Generamos el DataSource con los datos URL, user y passwd necesarios
            oracleDS = new OracleDataSource();
            oracleDS.setURL(props.getProperty("ORACLE_DB_URL"));
            oracleDS.setUser(props.getProperty("ORACLE_DB_USERNAME"));
            oracleDS.setPassword(props.getProperty("ORACLE_DB_PASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oracleDS;
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
    public static Connection conectarOracle(){
        Connection con = null;

        try {
            DataSource ds = getOracleDataSource();
            con = ds.getConnection();

            if (con!=null)
                System.out.println("Conexión establecida.");
        } catch (Exception e){
            e.printStackTrace();
        }

        return con;
    }

}
