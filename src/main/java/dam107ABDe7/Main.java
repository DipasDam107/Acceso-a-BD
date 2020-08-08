/*
Realizar un programa que actualice el salario de los empleados para que no haya ningún
empleado que cobre menos de 1000 euros. (Hacerlo mediante UPDATE). El programa
informará de cuantos empleados se han visto afectados.
 */
package dam107ABDe7;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            int actus = dao.actuSueldos();
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
