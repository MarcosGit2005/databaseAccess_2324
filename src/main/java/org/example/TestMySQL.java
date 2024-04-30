package org.example;

import org.example.model.AlmacenDatosDB;
import org.example.model.Empleado;
import org.example.model.EmpleadoDB;

public class TestMySQL {
    public static void main(String[] args) {
        MyDataSource.conectarMySql();

        AlmacenDatosDB empleados = new EmpleadoDB();
        System.out.println(empleados.getEmpleadosMySQL());

        Empleado empleado = empleados.getEmpleadosMySQL().get(0);
        empleado.setCargo("superJefe");
        System.out.println(empleados.updateEmpleadoMySQL(empleado)!=0?"Empleado actualizado.":"Empleado no encontrado.");
        System.out.println(empleados.deleteEmpleadoMySQL("")!=0?"Empleado borrado.":"Empleado no encontrado.");

        System.out.println(empleados.getEmpleadosMySQL());
    }
}
