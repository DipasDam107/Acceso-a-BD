/*
Realizar un programa que actualice el salario de los empleados para que no haya ningún
empleado que cobre menos de 1100 euros. Hacerlo actualizando un ResultSet. A continuación,
volver al principio ese ResultSet y mostrar nombre y salario de todos los empleados.
 */
package dam107ABDe9;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            int actus = dao.actuSalario();
            if(actus==-1) System.out.println("Se ha producido un error");
            else if(actus==0) System.out.println("No ha habido actualizaciones");
            else System.out.println("Se han producido actualizaciones: "+ actus);
            dao.cerrarCon();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
