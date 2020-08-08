/*
Realizar una versión del programa anterior en la que el usuario no introduce el ‘id’. El sistema
la calcula como el máximo almacenado en la tabla + 1. Pista: Habrá dos PreparedStatemnt,
uno para obtener el max (i) y otro para el insert
 */
package dam107ABDe6;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    static Scanner teclado;
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            int opcion=0;
            do{
                int idMax = dao.getMaxId();
                Empleado x = introducirEmpleado(idMax);
                if(x != null)
                   dao.introEmpleado(x);
                        
                do{
                System.out.println("Quieres introducir otro empleado? (1- Si 0- No)");
                opcion=teclado.nextInt();
                teclado.nextLine();
                if(opcion!=0 && opcion!=1)
                        System.out.println("Opcion incorrecta");
                }while(opcion!=0 && opcion!=1);
            }while(opcion!=0);
           
            dao.cerrarCon();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Empleado introducirEmpleado(int idMax){
        try{
            System.out.println("Dime nombre: ");
            String nombre = teclado.nextLine();
            System.out.println("Dime categoria: ");
            String cat = teclado.nextLine();
            System.out.println("Dime salario: ");
            Float salario = teclado.nextFloat();
            teclado.nextLine();
            System.out.println("Dime Fecha Nac (aaaa-mm-dd): ");
            String fecha=teclado.nextLine();
            LocalDate fechaNac = LocalDate.parse(fecha);
            return new Empleado(++idMax,nombre,salario,fechaNac,cat);
        }catch(Exception e){
            System.out.println("Se ha producido un error en la inserción");
            return null;
        }
    }
    
}
