/*
Realizar un programa que solicite al usuario una categoría laboral, y que muestre la cantidad
de empleados que hay con esa categoría. Pista: función agregada en SQL. 
 */
package dam107ABDe3;

import dam107ABDe2.*;
import dam107ABDe1.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dime Categoria: ");
        String cat=teclado.nextLine();
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            int emps = dao.empPorCategoria(cat);
            if (emps == 0) System.out.println("No existe la categoria "+ cat);
            else System.out.println("En la categoria " +cat + " hay "+emps+ " empleados" );
            
            dao.cerrarCon();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
