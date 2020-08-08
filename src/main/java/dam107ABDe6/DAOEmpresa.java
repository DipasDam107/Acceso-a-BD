/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam107ABDe6;

import dam107ABDe5.*;
import dam107ABDe4.*;
import dam107ABDe3.*;
import dam107ABDe2.*;
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
    
    public int introEmpleado(Empleado emp) throws SQLException{
        try{
        String consulta = "INSERT INTO empleado (id,nombre,categoria,salario,fechaNacimiento) VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(consulta);
        ps.setInt(1, emp.getId());
        ps.setString(2, emp.getNombre());
        ps.setString(3, emp.getCategoria());
        ps.setFloat(4, emp.getSalario());
        ps.setDate(5, java.sql.Date.valueOf(emp.getFechaNac()));
        return ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error, no se han introducido filas");
            return 0;
        }
    }
    public int getMaxId() throws SQLException{
        String consulta = "SELECT MAX(id) FROM empleado";
        PreparedStatement ps = con.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public void cerrarCon() throws SQLException{
        con.close();
    }
}
