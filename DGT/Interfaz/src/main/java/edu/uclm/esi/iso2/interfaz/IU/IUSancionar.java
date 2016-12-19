package edu.uclm.esi.iso2.interfaz.IU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import edu.uclm.esi.iso2.multas.domain.Sanction;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;

public class IUSancionar extends JFrame {
	private Manager m=Manager.get();
	private JPanel contentPane;
	private final JButton btnListarExpedientes = new JButton("Listar expedientes");
	private final JButton btnSancionar = new JButton("Sancionar");
	private Manager manager = Manager.get();
	private JList lstExpediente = new JList();
	private JList lstSanciones = new JList();
	private List<Sanction> sancionesConductor;
	DefaultListModel model = new DefaultListModel<>();
	DefaultListModel model_2 = new DefaultListModel<>();
	private JTextField txtDni;
	private int idSancion;
	private int idExpediente;
	private JTextField txtDni_2;
	private final JLabel lblSelecioneUnaSancin = new JLabel("Selecione una sanción para pagar");
	private final JLabel lblIntroduzcaLaMatrcula = new JLabel("Introduzca la matrícula del coche que quiere cambiar su propietario:");
	private final JTextField txt_licencia = new JTextField();
	private final JLabel lblIntroduzcaElNuevo = new JLabel("Introduzca el nuevo propietario del vehículo:");
	private final JTextField txt_dni3 = new JTextField();
	private final JButton btnAceptar = new JButton("Aceptar");
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
		txt_dni3.setBounds(34, 138, 86, 20);
		txt_dni3.setColumns(10);
		txt_licencia.setBounds(34, 63, 86, 20);
		txt_licencia.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{32, 0, 0, 0, 0};
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
		scrollPane.setBounds(10, 46, 152, 292);
		pnl_sancionar.add(scrollPane);
		
		lstExpediente.setModel(model);
		lstExpediente.addListSelectionListener(new LstExpedienteListSelectionListener());
		scrollPane.setViewportView(lstExpediente);
		{
			btnListarExpedientes.addActionListener(new BtnListarSancionesActionListener());
			btnListarExpedientes.setBounds(10, 11, 152, 23);
			pnl_sancionar.add(btnListarExpedientes);
		}
		{
			btnSancionar.addActionListener(new BtnSancionarActionListener());
			btnSancionar.setBounds(258, 101, 113, 23);
			pnl_sancionar.add(btnSancionar);
		}
		
		JLabel lblDniDelConductor = new JLabel("DNI del conductor:");
		lblDniDelConductor.setBounds(172, 58, 107, 14);
		pnl_sancionar.add(lblDniDelConductor);
		
		txtDni = new JTextField();
		txtDni.setBounds(285, 55, 86, 20);
		pnl_sancionar.add(txtDni);
		txtDni.setColumns(10);
		
		JPanel pnl_pagarSancion = new JPanel();
		tabbedPane.addTab("Pagar Sancion", null, pnl_pagarSancion, null);
		pnl_pagarSancion.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 48, 166, 290);
		pnl_pagarSancion.add(scrollPane_1);
		
		lstSanciones.setModel(model_2);
		lstSanciones.addListSelectionListener(new List_1ListSelectionListener());
		scrollPane_1.setViewportView(lstSanciones);
		
		JLabel label_1 = new JLabel("Inserte DNI:");
		label_1.setBounds(10, 20, 70, 14);
		pnl_pagarSancion.add(label_1);
		
		txtDni_2 = new JTextField();
		txtDni_2.setBounds(90, 17, 86, 20);
		pnl_pagarSancion.add(txtDni_2);
		txtDni_2.setColumns(10);
		
		JButton btn_lsanciones = new JButton("Listar sanciones");
		btn_lsanciones.addActionListener(new ButtonActionListener());
		btn_lsanciones.setBounds(205, 16, 164, 23);
		pnl_pagarSancion.add(btn_lsanciones);
		
		JButton btn_pagar = new JButton("Pagar Multa");
		btn_pagar.addActionListener(new Btn_pagarActionListener());
		btn_pagar.setBounds(267, 114, 102, 23);
		pnl_pagarSancion.add(btn_pagar);
		{
			lblSelecioneUnaSancin.setBounds(205, 71, 171, 14);
			pnl_pagarSancion.add(lblSelecioneUnaSancin);
		}
		
		JPanel pnl_cambiarPropietario = new JPanel();
		tabbedPane.addTab("Cambiar propietario", null, pnl_cambiarPropietario, null);
		pnl_cambiarPropietario.setLayout(null);
		{
			lblIntroduzcaLaMatrcula.setBounds(10, 36, 398, 14);
			pnl_cambiarPropietario.add(lblIntroduzcaLaMatrcula);
		}
		{
			pnl_cambiarPropietario.add(txt_licencia);
		}
		{
			lblIntroduzcaElNuevo.setBounds(10, 113, 320, 14);
			pnl_cambiarPropietario.add(lblIntroduzcaElNuevo);
		}
		{
			pnl_cambiarPropietario.add(txt_dni3);
		}
		{
			btnAceptar.addActionListener(new BtnCambiarPropietarioActionListener());
			btnAceptar.setBounds(76, 191, 254, 127);
			pnl_cambiarPropietario.add(btnAceptar);
		}
	}
	
	private class BtnListarSancionesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			model.clear();
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
			idExpediente = Integer.parseInt(lstExpediente.getSelectedValue().toString());
		}
	}
	private class BtnSancionarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String dni=txtDni.getText();
			if(dni.matches("[0-9]*") && dni.length()==7){
				try{
				Sanction multa = manager.identifyDriver(idExpediente, dni);
				JOptionPane.showMessageDialog(contentPane, "Sanción hecha con éxito", "Radar", JOptionPane.INFORMATION_MESSAGE);
				//System.out.println(multa.getId()+" "+multa.getAmount()+" "+multa.getPoints()+" "+multa.getDateOfPayment()+" "+multa.getDateOfReception());
				}catch(Exception ex){
					JOptionPane.showMessageDialog(contentPane, "Este DNI no pertenece a ningún conductor", "Radar", JOptionPane.ERROR_MESSAGE);
					txtDni.setText("");
				}
				
			}else{
				JOptionPane.showMessageDialog(contentPane, "Dni incorrecto", "Radar", JOptionPane.ERROR_MESSAGE);
				txtDni.setText("");
			}
			
		}
	}
	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			sancionesConductor=new ArrayList<>();
			model_2.clear();
			int id=0;
			int cont=0;
			List<Sanction> sanciones=m.obtenerSanction();
			String dni = txtDni_2.getText();
			txtDni_2.setText("");
			if(dni.matches("[0-9]*") && dni.length()==7){
				id=m.obtenerId(dni);
				for(int i=0;i<sanciones.size();i++){
					if(sanciones.get(i).getSanctionHolder().getId()==id){
						sancionesConductor.add(sanciones.get(i));
						cont++;
					}
				}
				if(cont!=0){
					for(int i=0;i<sancionesConductor.size();i++){
						model_2.addElement(sancionesConductor.get(i).getId());
					}
				}else{
					JOptionPane.showMessageDialog(contentPane, "Este DNI no está multado", "Radar", JOptionPane.ERROR_MESSAGE);
					txtDni_2.setText("");
				}		
			}else{
				JOptionPane.showMessageDialog(contentPane, "Dni incorrecto", "Radar", JOptionPane.ERROR_MESSAGE);
				txtDni_2.setText("");
			}
		}
	}
	private class List_1ListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent arg0) {
			idSancion=lstSanciones.getSelectedIndex();
		}
	}
	private class Btn_pagarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			m.pay(sancionesConductor.get(idSancion));
			JOptionPane.showMessageDialog(contentPane, "Sanción pagada correctamente", "Radar", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private class BtnCambiarPropietarioActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String dni = txt_dni3.getText();
			String license = txt_licencia.getText();
			if(dni.matches("[0-9]*") && dni.length()==7 && license.matches("[0-9]*") && license.length()==4){
				try{
					m.changeOwner(license, dni);
					JOptionPane.showMessageDialog(contentPane, "Cambio hecho correctamente", "Radar", JOptionPane.INFORMATION_MESSAGE);	
				}catch(Exception ex){
					JOptionPane.showMessageDialog(contentPane, "Este Dni o licencia no existen", "Radar", JOptionPane.ERROR_MESSAGE);
					txt_dni3.setText("");
					txt_licencia.setText("");
				}
			}else{
				JOptionPane.showMessageDialog(contentPane, "Dni o licencia incorrecta", "Radar", JOptionPane.ERROR_MESSAGE);
				txt_dni3.setText("");
				txt_licencia.setText("");
			}
		}
	}
}