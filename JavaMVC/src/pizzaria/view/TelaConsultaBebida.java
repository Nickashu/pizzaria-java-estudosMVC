package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pizzaria.controller.BebidaController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Bebida;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TelaConsultaBebida extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldConsultaBebida;
	private JTable tableConsultaBebida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaBebida frame = new TelaConsultaBebida();
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
	
	private JFrame telaCadastro;
	
	public TelaConsultaBebida(JFrame telaCadastroBebida) {
		this();
		this.telaCadastro = telaCadastroBebida;
	}
	
	public TelaConsultaBebida() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConsultaBebida.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					dispose();
					telaCadastro.setVisible(true);
				} 
				catch (Exception erro) {
					erro.printStackTrace();
				}
			}
		});
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 611);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelConsultaBebida = new JPanel();
		panelConsultaBebida.setBackground(new Color(255, 255, 204));
		panelConsultaBebida.setBounds(10, 90, 763, 461);
		contentPane.add(panelConsultaBebida);
		panelConsultaBebida.setLayout(null);
		
		JLabel lblNewLabelNomeBebida = new JLabel("Digite o nome da bebida procurada:");
		lblNewLabelNomeBebida.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelNomeBebida.setForeground(Color.DARK_GRAY);
		lblNewLabelNomeBebida.setBounds(10, 11, 369, 35);
		panelConsultaBebida.add(lblNewLabelNomeBebida);
		
		textFieldConsultaBebida = new JTextField();
		textFieldConsultaBebida.setToolTipText("Informe o nome da bebida que voc\u00EA deseja procurar");
		textFieldConsultaBebida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldConsultaBebida.setBounds(378, 19, 315, 23);
		panelConsultaBebida.add(textFieldConsultaBebida);
		textFieldConsultaBebida.setColumns(10);
		
		JButton btnNewButtonConsultarBebida = new JButton("");
		btnNewButtonConsultarBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeBebida = textFieldConsultaBebida.getText();
				DefaultTableModel tableModel = (DefaultTableModel) tableConsultaBebida.getModel();
				tableModel.setRowCount(0);
				BebidaController bebidaControlador = new BebidaController();
		
				try {
					ArrayList<Bebida> bebidas = bebidaControlador.consultarBebida(nomeBebida);
					bebidas.forEach((Bebida bebida) -> {
						tableModel.addRow(new Object[] {
							bebida.getIdBebida(),
							bebida.getNome(),
							bebida.getPreco()
						});
					});
					
					tableConsultaBebida.setModel(tableModel);
				}
				catch(ExceptionDAO erro) {
					Logger.getLogger(TelaCadastroBebida.class.getName()).log(Level.SEVERE, null, erro);
				}
			}
		});
		btnNewButtonConsultarBebida.setIcon(new ImageIcon(TelaConsultaPizza.class.getResource("/imagens/lupa.png")));
		btnNewButtonConsultarBebida.setBounds(703, 9, 50, 44);
		panelConsultaBebida.add(btnNewButtonConsultarBebida);
		
		JScrollPane scrollPaneConsultaBebida = new JScrollPane();
		scrollPaneConsultaBebida.setBounds(10, 64, 743, 371);
		panelConsultaBebida.add(scrollPaneConsultaBebida);
		
		tableConsultaBebida = new JTable();
		tableConsultaBebida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int idBebida = (Integer) tableConsultaBebida.getModel().getValueAt(tableConsultaBebida.getSelectedRow(), 0);
					String nomeBebida = (String) tableConsultaBebida.getModel().getValueAt(tableConsultaBebida.getSelectedRow(), 1);
					double precoBebida = (Double) tableConsultaBebida.getModel().getValueAt(tableConsultaBebida.getSelectedRow(), 2);
					
					TelaCadastroBebida telaCadastroBebida = (TelaCadastroBebida) telaCadastro;
					telaCadastroBebida.buscarBebida(idBebida, nomeBebida, precoBebida);
					telaCadastroBebida.setVisible(true);
					dispose();
					
				}
			}
		});
		tableConsultaBebida.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id_Bebida", "Nome", "Pre\u00E7o em R$ (lata/garrafa de 500ml)"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultaBebida.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableConsultaBebida.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableConsultaBebida.getColumnModel().getColumn(2).setPreferredWidth(152);
		scrollPaneConsultaBebida.setViewportView(tableConsultaBebida);
		
		JLabel lblNewLabel = new JLabel("(Para modificar os dados ou apagar uma bebida, clique na linha correspondente)");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(160, 441, 437, 14);
		panelConsultaBebida.add(lblNewLabel);
		
		JLabel lblNewLabelTitulo = new JLabel("Consulta de Bebidas");
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaConsultaPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setBounds(212, 23, 383, 55);
		contentPane.add(lblNewLabelTitulo);
		
		JLabel lblNewLabelAvisoVoltarConsultaBebida = new JLabel("Para voltar \u00E0 tela de cadastro sem selecionar uma bebida, basta fechar esta janela");
		lblNewLabelAvisoVoltarConsultaBebida.setForeground(Color.WHITE);
		lblNewLabelAvisoVoltarConsultaBebida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabelAvisoVoltarConsultaBebida.setBounds(313, 1, 468, 14);
		contentPane.add(lblNewLabelAvisoVoltarConsultaBebida);
	}
	
}


