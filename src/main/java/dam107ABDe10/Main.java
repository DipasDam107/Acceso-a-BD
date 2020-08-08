/*
Partiendo de la clase Empleado definida en un ejercicio anterior, crea una clase DAO con las
operaciones de:
• Conexión a la base de datos, con patrón Singleton y fichero Properties con usuario/pass.
• Buscar un empleado: se le pasará ‘id’ y devolverá un objeto Empleado.
• Borrar empleado: se le pasará ‘id’ y devolverá boolean según lo haya encontrado y borrado
o no.
• Insertar empleado: se le pasarán todos los datos y y devolverá boolean según lo haya
encontrado e insertado o no.
• Listar empleados: devuelve un ArrayList de Empleados, con todos los registros de la tabla.
Finalmente, hacer un programa con un menú, que permita: buscar, borrar, insertar y listar,
empleando la clase anterior. 
 */
package dam107ABDe10;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner teclado;
    public static void main(String[] args) {
        int op;
        teclado = new Scanner(System.in);
        try {
            DAOEmpresa dao = DAOEmpresa.getInstance();
            do{
                menu();
                op = teclado.nextInt();
                teclado.nextLine();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                if(op==1) listar(dao);
                if(op==2) crear(dao);
                if(op==3) buscar(dao);
                if(op==4) borrar(dao);
            }while(op!=0);
            dao.cerrarCon();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void menu(){
        System.out.println("\n-----------------------");
        System.out.println("MENU");
        System.out.println("-----------------------");
        System.out.println("1 - Listar Empleados");
        System.out.println("2 - Crear Empleado");
        System.out.println("3 - Buscar Empleado");
        System.out.println("4 - Borrar Empleado");
        System.out.println("0 - Salir");
        System.out.println("-----------------------");
        System.out.println("Dime opcion: ");
    }
    
    public static void listar(DAOEmpresa dao) throws SQLException{
        System.out.println("LISTA DE EMPLEADOS");
        System.out.println("---------------------------");
        ArrayList <Empleado> emps = dao.RecogeEmpleados();
        for(Empleado x : emps){
            System.out.println(x.toString());
        }
    }
    public static void crear(DAOEmpresa dao) throws SQLException{
        try{
            System.out.println("Dime ID: ");
            int id = teclado.nextInt();
            teclado.nextLine();
            if(!dao.getId(id)){
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
                dao.introEmpleado(new Empleado(id,nombre,salario,fechaNac,cat));
                System.out.println("Empleado creado con exito");
            }
            else System.out.println("Ya existe un empleado con ese ID");
            }catch(Exception e){
                    System.out.println("Se ha producido un error al crear empleado. Revisa que los datos sean válidos");
            }
        
    }
    public static void buscar(DAOEmpresa dao) throws SQLException{
        System.out.println("Dime ID a buscar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        Empleado x = dao.recogeEmpleado(id);
        if(x != null)
            System.out.println("Empleado encontrado - " + x.toString());
        else System.out.println("Empleado no encontrado");
        
    }
    public static void borrar(DAOEmpresa dao) throws SQLException{
        System.out.println("Dime ID a buscar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        if(dao.borraEmpleado(id)) System.out.println("Empleado de ID " + id + " borrado con exito");
        else System.out.println("No se ha borrado nada");
    }
}
