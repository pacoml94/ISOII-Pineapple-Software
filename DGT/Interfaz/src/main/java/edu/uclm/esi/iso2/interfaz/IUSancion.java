package edu.uclm.esi.iso2.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.uclm.esi.iso2.persistencia.MySQLBD;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class IUSancion extends JFrame {

	private JPanel contentPane;
	private int numExp;
	private MySQLBD bd;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel pnlSanciones = new JPanel();
	private final JPanel pnlPago = new JPanel();
	private final JPanel pnlCambiop = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JList<String> lstMatriculas = new JList<String>();
	private final JLabel lblIdDelConductor = new JLabel("ID del conductor: ");
	private final JLabel lblPuntosDelConductor = new JLabel("Puntos del conductor:");
	private DefaultListModel<String> modeloLista = new DefaultListModel<>();
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUSancion frame = new IUSancion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public IUSancion() {
		//Prueba momentanea
		cargarConductores();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			contentPane.add(tabbedPane, BorderLayout.CENTER);
		}
		{
			tabbedPane.addTab("Sanciones", null, pnlSanciones, null);
		}
		pnlSanciones.setLayout(null);
		{
			scrollPane.setBounds(10, 11, 178, 359);
			pnlSanciones.add(scrollPane);
		}
		{
			scrollPane.setViewportView(lstMatriculas);
		}
		{
			lblIdDelConductor.setBounds(298, 128, 151, 14);
			pnlSanciones.add(lblIdDelConductor);
		}
		{
			lblPuntosDelConductor.setBounds(298, 167, 132, 14);
			pnlSanciones.add(lblPuntosDelConductor);
		}
		tabbedPane.addTab("Pago de multas", null, pnlPago, null);
		{
			tabbedPane.addTab("Cambiar propietario de vehículo", null, pnlCambiop, null);
		}
		{
			numExp = (int) (Math.random()*9999999+1);
		}
		
	}

	private void cargarConductores() {
		try {
			bd = new MySQLBD();
			bd.conectar();
			leerBD(bd);
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error general");
			e.printStackTrace();
		}
	}
	
	private void leerBD(MySQLBD bd) throws SQLException{
    	ResultSet rs;
    	String sql = "SELECT * FROM hibernate_sequences";
    	rs = bd.consulta(sql);
    	
    	while(rs.next()){
    		modeloLista.addElement(rs.getString("sequence_name"));
    	}
    	lstMatriculas.setModel(modeloLista);
    	rs.close();
    }
}
