/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam107ABDe3;

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
    
    public int empPorCategoria(String cat) throws SQLException{
        String consulta = "SELECT COUNT(*) FROM empleado WHERE categoria LIKE ?";
        PreparedStatement ps = con.prepareStatement(consulta);
        ps.setString(1, cat);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public void cerrarCon() throws SQLException{
        con.close();
    }
}
