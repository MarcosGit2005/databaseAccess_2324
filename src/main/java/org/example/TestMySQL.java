package org.example;

public class TestMySQL {
    public static void main(String[] args) {


        AlmacenDatosDB empleados = new EmpleadoDB();
        //MyDataSource.conectarMySql();
        //System.out.println(empleados.getEmpleadosMySQL());

        MyDataSource.conectarOracle();
        //System.out.println(empleados.getEmpleadosMySQL());
    }
}
