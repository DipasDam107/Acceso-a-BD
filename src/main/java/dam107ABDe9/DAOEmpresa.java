
package dam107ABDe9;

import dam107ABDe8.*;
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
    
    public int actuSalario(){
        int contador=0;
        try{
        String consulta = "SELECT id, salario FROM empleado WHERE salario < 1100";
        PreparedStatement ps = con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            rs.updateFloat(2, 1100f);
            rs.updateRow();
            contador++;
        }
        return contador;
        }catch(SQLException e){
           e.printStackTrace();
           return -1; 
        }
}
public void cerrarCon() throws SQLException{
        con.close();
    }
}
