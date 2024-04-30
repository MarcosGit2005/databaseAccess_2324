package org.example.model;

import java.util.List;

public interface AlmacenDatosDB {
    List<Empleado> getEmpleadosMySQL();
    List<Empleado> getEmpleadosOracle();
    boolean updateEmpleado(Empleado enpleado);
}
