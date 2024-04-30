package org.example;

import java.util.List;

public interface AlmacenDatosDB {
    List<Empleado> getEmpleadosMySQL();
    List<Empleado> getEmpleadosOracle();
}
