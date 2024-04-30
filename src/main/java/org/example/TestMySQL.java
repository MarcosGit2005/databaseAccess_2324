package org.example;

import org.example.model.AlmacenDatosDB;
import org.example.model.EmpleadoDB;

public class TestMySQL {
    public static void main(String[] args) {


        AlmacenDatosDB empleados = new EmpleadoDB();
        //MyDataSource.conectarMySql();
        //System.out.println(empleados.getEmpleadosMySQL());

        MyDataSource.conectarOracle();
        //System.out.println(empleados.getEmpleadosMySQL());
    }
}
