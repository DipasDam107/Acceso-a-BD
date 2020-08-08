/*
Crear una clase llamada Empleado cuyos atributos son los campos de la tabla ‘Empleado’.
Realizar un programa que cargue un ArrayList de clase Empleado con todos los empleados de
la tabla. Mostrar a continuación todo el contenido del ArrayList. Pista: Implementa el método
toString() en la clase Empleado 
 */
package dam107ABDe4;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            ArrayList <Empleado> emps = dao.RecogeEmpleados();
            for(Empleado x : emps)
                System.out.println(x.toString());
            
            dao.cerrarCon();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
