
package dam107ABDe8;

import dam107ABDe7.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


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
    
    public int actuEmps(){
        try{
        String consulta = "DELETE FROM empleado WHERE timestampdiff(year,fechaNacimiento,curdate())>=60";
        PreparedStatement ps = con.prepareStatement(consulta);
        return ps.executeUpdate();
        }catch(SQLException e){
           return -1; 
//        }
    }
}}
