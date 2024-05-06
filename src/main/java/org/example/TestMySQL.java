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
        System.out.println(empleados.addEmpleadoFuncionMySQL(new Empleado(33,"6X","empleado6X","dia","46185",
                "email@gmail.com",new Date(1990,8,21),"empleado","calle odsao")));

        System.out.println(empleados.authenticateMySQL("6X","1111")?"Autenticado.":"No autenticado.");
        System.out.println(empleados.authenticateFunctionMySQL("6x","1111")?"Autenticado.":"No autenticado.");

        System.out.println(empleados.getEmpleadosMySQL());

        System.out.println(empleados.getEmpleadosPorCargoMySQL("superJefe"));
        System.out.println(empleados.getEmpleadosPorCargoFuncionMySQL("superJefe"));

    }
}
