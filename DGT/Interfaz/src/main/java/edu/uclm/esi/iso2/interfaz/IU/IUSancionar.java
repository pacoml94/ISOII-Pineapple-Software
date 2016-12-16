package edu.uclm.esi.iso2.interfaz.IU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;

import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.domain.Inquiry;
import edu.uclm.esi.iso2.multas.domain.Manager;

import javax.swing.event.ListSelectionEvent;

public class IUSancionar extends JFrame {

	private JPanel contentPane;
	private final JButton btnListarSanciones = new JButton("Listar expedientes");
	private final JButton btnSancionar = new JButton("Sancionar");
	private Manager manager = Manager.get();
	JList lstExpediente = new JList();
	DefaultListModel model = new DefaultListModel<>();
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IUSancionar frame = new IUSancionar();
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
	public IUSancionar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblElijaQueQuiere = new JLabel("Elija que quiere hacer:");
		GridBagConstraints gbc_lblElijaQueQuiere = new GridBagConstraints();
		gbc_lblElijaQueQuiere.insets = new Insets(0, 0, 5, 5);
		gbc_lblElijaQueQuiere.gridx = 1;
		gbc_lblElijaQueQuiere.gridy = 0;
		panel.add(lblElijaQueQuiere, gbc_lblElijaQueQuiere);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 3;
		gbc_tabbedPane.gridwidth = 4;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		panel.add(tabbedPane, gbc_tabbedPane);
		
		JPanel pnl_sancionar = new JPanel();
		tabbedPane.addTab("Sancionar", null, pnl_sancionar, null);
		pnl_sancionar.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 134, 364);
		pnl_sancionar.add(scrollPane);
		
		lstExpediente.setModel(model);
		lstExpediente.addListSelectionListener(new LstExpedienteListSelectionListener());
		scrollPane.setViewportView(lstExpediente);
		{
			btnListarSanciones.addActionListener(new BtnListarSancionesActionListener());
			btnListarSanciones.setBounds(181, 285, 175, 23);
			pnl_sancionar.add(btnListarSanciones);
		}
		{
			btnSancionar.setBounds(379, 285, 113, 23);
			pnl_sancionar.add(btnSancionar);
		}
		
		JPanel pnl_pagarSancion = new JPanel();
		tabbedPane.addTab("Pagar Sancion", null, pnl_pagarSancion, null);
		
		JPanel pnl_cambiarPropietario = new JPanel();
		tabbedPane.addTab("Cambiar propietario", null, pnl_cambiarPropietario, null);
	}
	
	private class BtnListarSancionesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			cargarSanciones();
		}
		
		public void cargarSanciones() {
			List<Inquiry> l = manager.obtenerInquiry();
			
			for (int i = 0; i < l.size(); i++) {
				model.addElement(l.get(i).getId());
			}
		}
	}
	private class LstExpedienteListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			
		}
	}
}