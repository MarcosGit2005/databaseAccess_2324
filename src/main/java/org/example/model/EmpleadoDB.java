package org.example.model;

import org.example.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDB implements AlmacenDatosDB{
    @Override
    public List<Empleado> getEmpleadosMySQL() {
        List<Empleado> empleados = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMySQLDataSource();

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLEADO")){

            Empleado empleado;
            while (resultSet.next()){
                empleado = new Empleado(
                        resultSet.getInt("idEmpleado"),
                        resultSet.getString("DNI"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("CP"),
                        resultSet.getString("email"),
                        resultSet.getDate("fechaNac"),
                        resultSet.getString("cargo"),
                        resultSet.getString("domicilio")
                );
                empleados.add(empleado);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return empleados;
    }
    public List<Empleado> getEmpleadosOracle() {
        List<Empleado> empleados = new ArrayList<>();
        DataSource dataSource = MyDataSource.getOracleDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLEADO")){

            Empleado empleado;
            while (resultSet.next()){
                empleado = new Empleado(
                        resultSet.getInt("idEmpleado"),
                        resultSet.getString("DNI"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("CP"),
                        resultSet.getString("email"),
                        resultSet.getDate("fechaNac"),
                        resultSet.getString("cargo"),
                        resultSet.getString("domicilio")
                );
                empleados.add(empleado);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public int updateEmpleadoMySQL(Empleado empleado) {
        DataSource ds = MyDataSource.getMySQLDataSource();
        int rowsAffected=0;

        try(Connection connection = ds.getConnection();
        Statement statement = connection.createStatement()){
            String query = "UPDATE EMPLEADO SET nombre='"+empleado.getNombre()+"',apellidos='"+empleado.getApellidos()
                    +"',CP='"+empleado.getCP()+"',email='"+empleado.getEmail()+"',fechaNac='"+empleado.getFechaNac()
                    +"',cargo='"+empleado.getCargo()+"',domicilio='"+empleado.getDomicilio()+"' WHERE DNI='"+empleado.getDNI()+"'";
            rowsAffected = statement.executeUpdate(query);

        } catch (Exception e){
            e.printStackTrace();
        }

        return rowsAffected;
    }

    @Override
    public int deleteEmpleadoMySQL(String DNI) {
        DataSource ds = MyDataSource.getMySQLDataSource();
        int rowsAffected=0;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()){
            String query = "DELETE FROM EMPLEADO WHERE DNI='"+DNI+"'";
            rowsAffected = statement.executeUpdate(query);

        } catch (Exception e){
            e.printStackTrace();
        }

        return rowsAffected;
    }

    @Override
    public Empleado addEmpleado(Empleado empleado) {
        DataSource ds = MyDataSource.getMySQLDataSource();

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()){
            String query = "INSERT INTO EMPLEADO VALUES ('"+empleado.getDNI()+"','"+empleado.getNombre()+"','"+
                    empleado.getApellidos()+"','"+empleado.getCP()+"','"+empleado.getEmail()+"','"+
                    empleado.getFechaNac()+"','"+empleado.getCargo()+"','"+empleado.getDomicilio()+"');";
            statement.executeUpdate(query);

        } catch (Exception e){
            e.printStackTrace();
        }

        return getEmpleado(empleado.getDNI());
    }

    @Override
    public Empleado getEmpleado(String DNI) {
        Empleado empleado = null;
        DataSource dataSource = MyDataSource.getOracleDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLEADO WHERE DNI='"+DNI+"';")){
            resultSet.next();
            empleado = new Empleado(
                    resultSet.getInt("idEmpleado"),
                    resultSet.getString("DNI"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellidos"),
                    resultSet.getString("CP"),
                    resultSet.getString("email"),
                    resultSet.getDate("fechaNac"),
                    resultSet.getString("cargo"),
                    resultSet.getString("domicilio")
            );

        } catch (Exception e){
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return false;
    }

}
