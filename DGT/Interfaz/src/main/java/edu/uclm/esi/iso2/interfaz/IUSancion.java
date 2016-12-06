package edu.uclm.esi.iso2.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IUSancion extends JFrame {

	private JPanel contentPane;
	private final JPanel pnlSancion = new JPanel();
	private final JLabel lblNmeroDeExpediente = new JLabel();
	private final JLabel lblDni = new JLabel("DNI de la persona infractora:");
	private final JTextField txtDNI = new JTextField();
	private final JButton btnSancionar = new JButton("Sancionar");
	private int numExp;
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
		txtDNI.addFocusListener(new TxtDNIFocusListener());
		txtDNI.setBounds(187, 111, 86, 20);
		txtDNI.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			contentPane.add(pnlSancion, BorderLayout.CENTER);
		}
		pnlSancion.setLayout(null);
		{
			lblNmeroDeExpediente.setBounds(10, 45, 292, 14);
			numExp = (int) (Math.random()*9999999+1);
			lblNmeroDeExpediente.setText("Número de expediente de la sanción: "+String.valueOf(numExp));
			pnlSancion.add(lblNmeroDeExpediente);
		}
		{
			lblDni.setBounds(10, 114, 217, 14);
			pnlSancion.add(lblDni);
		}
		{
			pnlSancion.add(txtDNI);
		}
		{
			btnSancionar.addActionListener(new BtnSancionarActionListener());
			btnSancionar.setBounds(159, 182, 114, 23);
			pnlSancion.add(btnSancionar);
		}
	}
	private class TxtDNIFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			e.getComponent().setBackground(new Color(250, 250, 210));
		}
		@Override
		public void focusLost(FocusEvent e) {
			e.getComponent().setBackground(new Color(250, 250, 250));
		}
	}
	private class BtnSancionarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String DNI = txtDNI.getText();
			System.out.printf("Abriendo expediente %d a %s", numExp, DNI);
			IUPagarSancion pagarSancion = new IUPagarSancion(numExp, DNI);
			pagarSancion.setVisible(true);
			dispose();
		}
	}
}
