/*
Realizar un programa que se conecte a la base de datos ‘empresa’ y muestre el nombre de los
empleados de la empresa
 */
package dam107ABDe1;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            ArrayList<Empleado> emps = dao.RecogeEmpleados();
            for(Empleado x : emps){
                System.out.println("-------------------------");
                System.out.println("ID: "  + x.getId());
                System.out.println("Nombre: "  + x.getNombre());
                System.out.println("Categoria: "  + x.getCategoria());
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
