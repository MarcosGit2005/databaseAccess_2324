package org.example.model;

import java.util.List;

public interface AlmacenDatosDB {
    List<Empleado> getEmpleadosMySQL();
    List<Empleado> getEmpleadosOracle();
    int updateEmpleadoMySQL(Empleado enpleado);
    int deleteEmpleadoMySQL(String DNI);
    Empleado addEmpleadoMySQL(Empleado empleado);
    Empleado getEmpleadoMySQL(String DNI);
    boolean authenticateMySQL(String login,String password);
}
