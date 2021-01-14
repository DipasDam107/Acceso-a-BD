package comboBD;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTable datosTable;
	JComboBox tablasComboBox;
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
		
		tablasComboBox = new JComboBox();
		tablasComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablasComboBox.getSelectedItem() != null) {
					String tablaElegida = tablasComboBox.getSelectedItem().toString();
					try {
					Connection conn=connect();
				    PreparedStatement ps;
	
				    ps = conn.prepareStatement("SELECT * FROM " + tablaElegida);
					ResultSet rs = ps.executeQuery();
					rellenaTabla(rs);
					ps.close();
					
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(tablasComboBox, BorderLayout.NORTH);
		
		datosTable = new JTable();
		frame.getContentPane().add(datosTable, BorderLayout.CENTER);
		
		JButton salirButton = new JButton("Salir");
		salirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		frame.getContentPane().add(salirButton, BorderLayout.SOUTH);
		try {
			cargarTablas();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
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
	
	private void cargarTablas() throws SQLException {
		Connection conn=connect();
	    PreparedStatement ps;
	    ps = conn.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE';");
	    ResultSet rs = ps.executeQuery();
		
	    while(rs.next()) {
	    	tablasComboBox.addItem(rs.getString(1));
	    }
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
		
		datosTable.setModel(dtm);
		dtm.fireTableDataChanged();
		
		rs.close();
		
		
	}

}
