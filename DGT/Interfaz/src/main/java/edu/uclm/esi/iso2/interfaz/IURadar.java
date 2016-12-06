package edu.uclm.esi.iso2.interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IURadar {

	private JFrame frmRadar;
	private final JPanel panel = new JPanel();
	private final JButton btnArrancarRadar = new JButton("Arrancar radar");
	private final JButton btnDetenerRadar = new JButton("Detener radar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IURadar window = new IURadar();
					window.frmRadar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getRadar() {
		return frmRadar;
	}
	
	/**
	 * Create the application.
	 */
	public IURadar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRadar = new JFrame();
		frmRadar.setTitle("Radar");
		frmRadar.setBounds(100, 100, 450, 300);
		frmRadar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		{
			frmRadar.getContentPane().add(panel, BorderLayout.CENTER);
		}
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		{
			btnArrancarRadar.addActionListener(new BtnArrancarRadarActionListener());
			panel.add(btnArrancarRadar);
		}
		{
			btnDetenerRadar.addActionListener(new BtnDetenerRadarActionListener());
			panel.add(btnDetenerRadar);
		}
	}

	private class BtnDetenerRadarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			frmRadar.dispose();
		}
	}
	private class BtnArrancarRadarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//Radar r = new Radar(true);
			frmRadar.dispose();
			IUSancion iuSancion = new IUSancion();
			iuSancion.setVisible(true);
		}
	}
}
