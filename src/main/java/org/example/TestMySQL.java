package org.example;

public class TestMySQL {
    public static void main(String[] args) {
        MyDataSource.conectarMySql();
        AlmacenDatosDB empleados = new EmpleadoDB();

        System.out.println(empleados.getEmpleados());
    }
}
