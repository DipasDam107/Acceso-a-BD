package javaApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;

public class MainWindow {

	private JFrame frame;
	private JTextField busquedaTextField;
	private JTable tablaResultados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JButton salirButton = new JButton("Salir");
		salirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		frame.getContentPane().add(salirButton, BorderLayout.SOUTH);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		busquedaTextField = new JTextField();
		
		tablaResultados = new JTable();
		frame.getContentPane().add(tablaResultados, BorderLayout.CENTER);
		
		JButton peliculasButton = new JButton("Peliculas");
		peliculasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection conn=connect();
				    PreparedStatement ps;		    
				    ps = conn.prepareStatement("SELECT film_id, title, release_year, length FROM film;");
					ResultSet rs = ps.executeQuery();
					rellenaTabla(rs);
					ps.close();
				
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(peliculasButton);
		
		JButton actoresButton = new JButton("Actores");
		actoresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			/*Listar Actores*/
			
			//String valorBusqueda = busquedaTextField.getText();
			try {
				
			Connection conn=connect();
		    PreparedStatement ps;

		    ps = conn.prepareStatement("SELECT actor_id, first_name, last_name FROM actor;");
			ResultSet rs = ps.executeQuery();
			rellenaTabla(rs);
			ps.close();
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		toolBar.add(actoresButton);
		toolBar.add(busquedaTextField);
		busquedaTextField.setColumns(10);
		
		JButton ejecutarButton = new JButton("Ejecutar");
		ejecutarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String consulta = busquedaTextField.getText();
				try {
				Connection conn=connect();
			    PreparedStatement ps;
			    ps = conn.prepareStatement(consulta);
			    ResultSet rs = ps.executeQuery();
			    rellenaTabla(rs);
			    ps.close();
			    
				}catch(SQLException e1) {
					e1.printStackTrace();
					
				}
			}
		});
		toolBar.add(ejecutarButton);
		
		
		
		
	}
	
	private void rellenaTabla(ResultSet rs) throws SQLException {
		DefaultTableModel dtm = new DefaultTableModel();
		
		int columnas = rs.getMetaData().getColumnCount();
		
		for(int i=1; i<=columnas; i++) {
			dtm.addColumn(rs.getMetaData().getColumnName(i));
			
		}
		
		while(rs.next())
			{
			Object [] fila = new Object[columnas];
			
			for(int i=0; i<columnas; i++) {
	    		fila[i] = rs.getObject(i+1);
	    	}
			dtm.addRow(fila);
		}
		
		tablaResultados.setModel(dtm);
		dtm.fireTableDataChanged();
		
		rs.close();
		
		
	}
	private Connection connect() throws SQLException
    {
	     String url = "jdbc:postgresql://192.168.56.101/PAGILA";
	     Properties props = new Properties();
	     props.setProperty("user","postgres");
	     props.setProperty("password","1234abc.");
	     props.setProperty("ssl","false");
	     return DriverManager.getConnection(url, props);     
    }

}
