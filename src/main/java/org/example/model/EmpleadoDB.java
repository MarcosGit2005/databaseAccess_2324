package org.example.model;

import org.example.MyDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public Empleado addEmpleadoMySQL(Empleado empleado) {
        DataSource ds = MyDataSource.getMySQLDataSource();
        int rowsAffected=0;

        try(Connection connection = ds.getConnection();
            Statement statement = connection.createStatement()){
            String query = "INSERT INTO EMPLEADO(DNI,nombre,apellidos,CP,email,fechaNac,cargo,domicilio) VALUES ('"+
                    empleado.getDNI()+"','"+empleado.getNombre()+"','"+
                    empleado.getApellidos()+"','"+empleado.getCP()+"','"+empleado.getEmail()+"','"+
                    empleado.getFechaNac()+"','"+empleado.getCargo()+"','"+empleado.getDomicilio()+"');";
            rowsAffected = statement.executeUpdate(query);

        } catch (Exception e){
            e.printStackTrace();
        }

        if (rowsAffected>0)
            return getEmpleadoMySQL(empleado.getDNI());
        return null;
    }

    @Override
    public Empleado getEmpleadoMySQL(String DNI) {
        Empleado empleado = null;
        DataSource dataSource = MyDataSource.getMySQLDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLEADO WHERE DNI='"+DNI+"'")){
            resultSet.next();
            empleado = new Empleado(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7),
                    resultSet.getString(8),
                    resultSet.getString(11)
            );

        } catch (Exception e){
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public boolean authenticateMySQL(String login, String password) {
        boolean autenticado = false;
        DataSource dataSource = MyDataSource.getOracleDataSource();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM EMPLEADO WHERE DNI='"+login+
                     "' AND PASSWORD='"+password+"'")){
            resultSet.next();
            if (resultSet.getInt(1)>0)
                autenticado=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return autenticado;
    }

    public List<Empleado> getEmpleadosPorCargo(String cargo){
        List<Empleado> empleados = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMySQLDataSource();

        String query = "SELECT * FROM EMPLEADO WHERE CARGO = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,cargo);

            ResultSet resultSet = ps.executeQuery();
            Empleado empleado;

            while(resultSet.next()){
                empleado = new Empleado(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getString(11)
                );
                empleados.add(empleado);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return empleados;
    }
}
