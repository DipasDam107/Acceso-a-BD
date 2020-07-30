/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam107ABDe2;

import dam107ABDe1.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DAOEmpresa {

    private static DAOEmpresa instance = null;
    private Connection con = null;

    private DAOEmpresa() throws SQLException, IOException {
        if (con == null) {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "");
        }
    }

    public static DAOEmpresa getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DAOEmpresa();
        }
        return instance;
    }
    
    public ArrayList<Empleado> RecogeEmpleados() throws SQLException{
        ArrayList <Empleado> emps = new ArrayList<>();
        String consulta = "SELECT id, nombre, salario, fechaNacimiento, categoria FROM empleado";
        PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombre = rs.getString("nombre");
                int id = rs.getInt("id");
                float salario = rs.getFloat("salario");
                LocalDate fechaNac= rs.getDate("fechaNacimiento").toLocalDate();
                String categoria = rs.getString("categoria");
                emps.add(new Empleado(id, nombre, salario, fechaNac, categoria));
            }
        return emps;
    }
    
    public ArrayList<Empleado> RecogeEmpleadosMenores(LocalDate fecha) throws SQLException{
        ArrayList <Empleado> emps = new ArrayList<>();
        System.out.println(java.sql.Date.valueOf(fecha));
        String consulta = "SELECT id, nombre, salario, fechaNacimiento, categoria FROM empleado WHERE fechaNacimiento >= '" + java.sql.Date.valueOf(fecha) + "'";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombre = rs.getString("nombre");
                int id = rs.getInt("id");
                float salario = rs.getFloat("salario");
                LocalDate fechaNac= rs.getDate("fechaNacimiento").toLocalDate();
                String categoria = rs.getString("categoria");
                emps.add(new Empleado(id, nombre, salario, fechaNac, categoria));
            }
        return emps;
    }
    
}
