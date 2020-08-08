/*
Realizar un programa que solicite al usuario todos los datos de un empleado y que inserte una
nueva fila en la tabla Empleado. Después de introducir un empleado, preguntará si se desea
insertar más, repitiendo el proceso mientras el usuario lo desee.
 */
package dam107ABDe5;

import dam107ABDe4.*;
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
                Empleado x = introducirEmpleado();
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
    public static Empleado introducirEmpleado(){
        try{
            System.out.println("Dime ID: ");
            int id=teclado.nextInt();
            teclado.nextLine();
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
            return new Empleado(id,nombre,salario,fechaNac,cat);
        }catch(Exception e){
            System.out.println("Se ha producido un error en la inserción");
            return null;
        }
    }
}
