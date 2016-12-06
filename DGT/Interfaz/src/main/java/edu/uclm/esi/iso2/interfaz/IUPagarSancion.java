package edu.uclm.esi.iso2.interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IUPagarSancion extends JFrame {

	private JPanel contentPane;
	private final JPanel pnlSancion = new JPanel();
	private final JLabel lblConductor = new JLabel("Conductor");
	private final JButton btnPagarMulta = new JButton("Pagar multa");

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUPagarSancion frame = new IUPagarSancion();
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
	public IUPagarSancion(int numExpediente, String DNI) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			lblConductor.setText("Conductor con DNI: "+DNI+" Expediente: "+numExpediente);
			contentPane.add(pnlSancion, BorderLayout.CENTER);
		}
		pnlSancion.setLayout(null);
		{
			lblConductor.setBounds(62, 62, 352, 14);
			pnlSancion.add(lblConductor);
		}
		btnPagarMulta.addActionListener(new BtnPagarMultaActionListener());
		btnPagarMulta.setBounds(111, 105, 173, 23);
		
		pnlSancion.add(btnPagarMulta);
	}

	private class BtnPagarMultaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			IURadar radar = new IURadar();
			radar.getRadar().setVisible(true);
			dispose();
		}
	}
}
