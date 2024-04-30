package org.example;

import org.example.model.AlmacenDatosDB;
import org.example.model.Empleado;
import org.example.model.EmpleadoDB;

import java.sql.Date;

public class TestMySQL {
    public static void main(String[] args) {
        MyDataSource.conectarMySql();

        AlmacenDatosDB empleados = new EmpleadoDB();
        System.out.println(empleados.getEmpleadosMySQL());

        Empleado empleado = empleados.getEmpleadosMySQL().get(0);
        empleado.setCargo("superJefe");
        System.out.println(empleados.updateEmpleadoMySQL(empleado)!=0?"Empleado actualizado.":"Empleado no encontrado.");
        System.out.println(empleados.deleteEmpleadoMySQL("6X")!=0?"Empleado borrado.":"Empleado no encontrado.");
        System.out.println(empleados.addEmpleadoMySQL(new Empleado(33,"6X","primerito","dia","46185",
                "cuaccuacuaccuac@xinxon.com",new Date(1990,8,21),"empleado","calle odsao")));

        System.out.println(empleados.getEmpleadosMySQL());
    }
}
