/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam107ABDe10;

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
    
    public Empleado recogeEmpleado(int ID) throws SQLException{
        String consulta = "SELECT * FROM empleado where id = ?";
        PreparedStatement ps = con.prepareStatement(consulta);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            String nombre = rs.getString("nombre");
            int id = rs.getInt("id");
            float salario = rs.getFloat("salario");
            LocalDate fechaNac= rs.getDate("fechaNacimiento").toLocalDate();
            String categoria = rs.getString("categoria");
            return new Empleado(id, nombre, salario, fechaNac, categoria);
        }
        else return null;
    }
   
    public boolean borraEmpleado(int ID) throws SQLException{
        String sentencia = "DELETE FROM empleado WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sentencia);
        ps.setInt(1, ID);
        int res = ps.executeUpdate();
        if(res!=0) return true;
        else return false;
    }
    
     public boolean introEmpleado(Empleado emp) throws SQLException{
        try{
        String consulta = "INSERT INTO empleado (id,nombre,categoria,salario,fechaNacimiento) VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(consulta);
        ps.setInt(1, emp.getId());
        ps.setString(2, emp.getNombre());
        ps.setString(3, emp.getCategoria());
        ps.setFloat(4, emp.getSalario());
        ps.setDate(5, java.sql.Date.valueOf(emp.getFechaNac()));
        if(ps.executeUpdate()!=0) return true;
        else return false;
        }catch(SQLException e){
            System.out.println("Error, no se han introducido filas");
            return false;
        }
    }
    public boolean getId(int ID) throws SQLException{
        String consulta = "SELECT * FROM empleado WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(consulta);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) return true;
        else return false;
        
    }
    
    public void cerrarCon() throws SQLException{
        con.close();
    }
    
}
