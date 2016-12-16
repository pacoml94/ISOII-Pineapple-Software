package edu.uclm.esi.iso2.interfaz.IU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import edu.uclm.esi.iso2.multas.domain.Manager;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IUSancion extends JFrame {

	private JPanel contentPane;
	private final JPanel pnlSancion = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JList lstSanciones = new JList();
	private final JLabel lblDniDelInfractor = new JLabel("DNI del infractor:");
	private final JTextField txtDNI = new JTextField();
	private Manager manager;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public IUSancion() {
		txtDNI.setBounds(457, 175, 86, 20);
		txtDNI.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			contentPane.add(pnlSancion, BorderLayout.CENTER);
		}
		pnlSancion.setLayout(null);
		{
			scrollPane.setBounds(28, 21, 287, 520);
			pnlSancion.add(scrollPane);
		}
		{
			lstSanciones.setBorder(new TitledBorder(null, "Lista de sanciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
			scrollPane.setViewportView(lstSanciones);
		}
		{
			lblDniDelInfractor.setBounds(352, 178, 95, 14);
			pnlSancion.add(lblDniDelInfractor);
		}
		{
			pnlSancion.add(txtDNI);
		}
	}
	
	private void leerSanciones() {
		
	}
}
