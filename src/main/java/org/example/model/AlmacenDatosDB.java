package org.example.model;

import java.util.List;

public interface AlmacenDatosDB {
    List<Empleado> getEmpleadosMySQL();
    List<Empleado> getEmpleadosOracle();
    int updateEmpleadoMySQL(Empleado enpleado);
    int deleteEmpleadoMySQL(String DNI);
    Empleado addEmpleado(Empleado empleado);
    Empleado getEmpleado(String DNI);
    boolean authenticate(String login,String password);
}
