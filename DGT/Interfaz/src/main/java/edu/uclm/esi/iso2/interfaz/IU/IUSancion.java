package edu.uclm.esi.iso2.interfaz.IU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.uclm.esi.iso2.multas.domain.Manager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class IUSancion extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JLabel lblZonaDeLa = new JLabel("Zona de la sanción:");
	private final JTextField txtZona = new JTextField();
	private final JLabel lblIdDeLa = new JLabel("ID de la sanción:");
	private final JTextField txtID = new JTextField();
	private Manager manager = Manager.get();
	private int velocidadConductor, velocidad_max, idVehiculo, indiceCiudad;
	String [] ciudad = {"Madrid", "Barcelona", "Bilbao", "A Coruña", "Ciudad Real"};
	String ciudadSancion;
	private final JLabel lblFecha = new JLabel("Fecha:");
	private final JTextField textField = new JTextField();
	
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
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(124, 255, 131, 20);
		textField.setColumns(10);
		/**invocación del método para que los componentes estén inicializados*/
		abrirSancion();
		/***************************************************/
		txtID.setEditable(false);
		txtID.setBackground(Color.WHITE);
		txtID.setBounds(124, 184, 131, 20);
		txtID.setColumns(10);
		txtZona.setEditable(false);
		txtZona.setBackground(Color.WHITE);
		txtZona.setBounds(124, 99, 131, 20);
		txtZona.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			contentPane.add(panel, BorderLayout.CENTER);
		}
		panel.setLayout(null);
		{
			lblZonaDeLa.setBounds(10, 102, 132, 14);
			panel.add(lblZonaDeLa);
		}
		{
			panel.add(txtZona);
		}
		{
			lblIdDeLa.setBounds(10, 187, 92, 14);
			panel.add(lblIdDeLa);
		}
		{
			panel.add(txtID);
		}
		{
			lblFecha.setBounds(10, 258, 46, 14);
			panel.add(lblFecha);
		}
		{
			panel.add(textField);
		}
	}
	
	private void abrirSancion() {
		//Rango de multa de 130 a 200 km/h
		velocidadConductor = (int) (Math.random()*(200-130+1)+130);
		//Rango de velocidad max de 80 a 120
		velocidad_max = (int) (Math.random()*(120-80+1)+80);
		//Rango de matricula 
		idVehiculo = (int) (Math.random()*(3217-1+1)+1);
		//Rango ciudades
		indiceCiudad = (int) (Math.random()*(ciudad.length-1));
		ciudadSancion = ciudad[indiceCiudad];
		
		System.out.println("V conductor"+velocidadConductor);
		System.out.println("V max "+velocidad_max);
		System.out.println("Matricula "+idVehiculo);
		System.out.println("Ciudad "+ciudadSancion);
	}
}
