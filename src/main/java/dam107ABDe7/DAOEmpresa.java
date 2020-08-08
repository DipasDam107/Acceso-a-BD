
package dam107ABDe7;

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
    
    public int actuSueldos(){
        try{
        String consulta = "UPDATE empleado SET salario = 1000 WHERE salario < 1000";
        PreparedStatement ps = con.prepareStatement(consulta);
        return ps.executeUpdate();
        }catch(SQLException e){
           return -1; 
//        }
    }
}
public void cerrarCon() throws SQLException{
        con.close();
    }
}
