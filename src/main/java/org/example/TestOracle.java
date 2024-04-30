package org.example;

import org.example.model.AlmacenDatosDB;
import org.example.model.EmpleadoDB;

public class TestOracle {
    public static void main(String[] args) {
        MyDataSource.conectarOracle();

        AlmacenDatosDB empleados = new EmpleadoDB();

        System.out.println(empleados.getEmpleadosOracle());
    }
}
