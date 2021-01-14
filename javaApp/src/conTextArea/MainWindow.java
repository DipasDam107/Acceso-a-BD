package conTextArea;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;
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
		
		JTextArea detallesTextArea = new JTextArea();
		detallesTextArea.setEditable(false);
		frame.getContentPane().add(detallesTextArea, BorderLayout.EAST);
		busquedaTextField = new JTextField();
		
		
		
		JButton peliculasButton = new JButton("Peliculas");
		peliculasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Listar Peliculas*/
				detallesTextArea.setText("");
				//String valorBusqueda = busquedaTextField.getText();
				
				try {
					Connection conn=connect();
				    PreparedStatement ps;
					/*
				    if(valorBusqueda.equals(""))
				    	 
					else {
						ps = conn.prepareStatement("SELECT film_id, title, release_year, length FROM film WHERE title LIKE ?;");
						ps.setString(1, valorBusqueda);
					}
					*/
				    ps = conn.prepareStatement("SELECT film_id, title, release_year, length FROM film;");
					ResultSet rs = ps.executeQuery();
					while(rs.next())
						{
							String linea = "ID: "+ rs.getInt(1) + "\t" + "Nombre: " + rs.getString(2) + "\t" + "Año: " + rs.getInt(3) + "\t" + "Minutos: " + rs.getInt(4);
							detallesTextArea.append(linea+"\n");
						}
					rs.close();
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
			detallesTextArea.setText("");
			//String valorBusqueda = busquedaTextField.getText();
			try {
				
			Connection conn=connect();
		    PreparedStatement ps;
			/*
		    if(valorBusqueda.equals(""))
				ps = conn.prepareStatement("SELECT actor_id, first_name, last_name FROM actor;");
			else {
				ps = conn.prepareStatement("SELECT actor_id, first_name, last_name FROM actor WHERE first_name LIKE ?;");
				ps.setString(1, valorBusqueda);
			}
			*/
		    ps = conn.prepareStatement("SELECT actor_id, first_name, last_name FROM actor;");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				{
					String linea = "ID: "+ rs.getInt(1) + "\t" + "Nombre: " + rs.getString(2)+ "\t" + " Apellido: " + rs.getString(3);
					detallesTextArea.append(linea+"\n");
				}
			rs.close();
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
				detallesTextArea.setText("");
				String consulta = busquedaTextField.getText();
				try {
				Connection conn=connect();
			    PreparedStatement ps;
			    ps = conn.prepareStatement(consulta);
			    ResultSet rs = ps.executeQuery();
			    while(rs.next()) {
			    	int columnas = rs.getMetaData().getColumnCount();
			    	for(int i=1; i<=columnas; i++) {
			    		detallesTextArea.append(rs.getMetaData().getColumnName(i) + " : " + rs.getString(i) + " ");
			    	}
			    	detallesTextArea.append("\n");
			    }
			    
				}catch(SQLException e1) {
					detallesTextArea.setText("Se ha producido un error en la consulta, comprueba que es valida");
					
				}
			}
		});
		toolBar.add(ejecutarButton);
		
		tablaResultados = new JTable();
		frame.getContentPane().add(tablaResultados, BorderLayout.CENTER);
		
		
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
