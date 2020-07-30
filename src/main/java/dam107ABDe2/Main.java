/*
Realizar un programa que solicite al usuario una fecha y obtenga de la base de datos los
empleados nacidos después de esa fecha y muestre su nombre y edad. Pista: hay que convertir
de LocalDate a java.sql.Date. 
 */
package dam107ABDe2;

import dam107ABDe1.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime Fecha(aaaa-mm-dd): ");
        String fecha=teclado.nextLine();
        LocalDate fec = LocalDate.parse(fecha);
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            ArrayList<Empleado> emps = dao.RecogeEmpleadosMenores(fec);
            
            for(Empleado x : emps){
                System.out.println("-------------------------");
                System.out.println("Fecha Nac: "  + x.getFechaNac());
                System.out.println("Nombre: "  + x.getNombre());
                System.out.println("-------------------------");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
