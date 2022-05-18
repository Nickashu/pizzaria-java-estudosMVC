package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pizzaria.controller.EnderecoController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Endereco;

import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TelaConsultaEndereco extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldConsultaEndereco;
	private JTable tableConsultaEndereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaEndereco frame = new TelaConsultaEndereco();
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
	
	public TelaConsultaEndereco(JFrame telaCadastroEndereco) {
		this();
		this.telaCadastro = telaCadastroEndereco;
	}
	
	public TelaConsultaEndereco() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConsultaEndereco.class.getResource("/imagens/icone2_pizzaMenorr.png")));
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
		setBounds(100, 100, 1000, 639);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelTitulo = new JLabel("Consulta de Endere\u00E7o");
		lblNewLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaConsultaEndereco.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setBounds(300, 21, 400, 55);
		contentPane.add(lblNewLabelTitulo);
		
		JPanel panelConsultaEndereco = new JPanel();
		panelConsultaEndereco.setBackground(new Color(255, 255, 204));
		panelConsultaEndereco.setBounds(10, 87, 964, 485);
		contentPane.add(panelConsultaEndereco);
		panelConsultaEndereco.setLayout(null);
		
		JScrollPane scrollPaneConsultaEndereco = new JScrollPane();
		scrollPaneConsultaEndereco.setBounds(10, 77, 944, 376);
		panelConsultaEndereco.add(scrollPaneConsultaEndereco);
		
		tableConsultaEndereco = new JTable();
		tableConsultaEndereco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int idEndereco = (Integer) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 0);
					String nomeCliente = (String) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 1);
					String cidade = (String) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 2);
					String bairro = (String) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 3);
					String rua = (String) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 4);
					int numero = (Integer) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 5);
					String complemento = (String) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 6);
					int idCliente = (Integer) tableConsultaEndereco.getModel().getValueAt(tableConsultaEndereco.getSelectedRow(), 7);
					
					TelaCadastroEndereco telaCadastroEndereco = (TelaCadastroEndereco) telaCadastro;
					telaCadastroEndereco.buscarEndereco(idEndereco, nomeCliente, cidade, bairro, rua, numero, complemento, idCliente);
					telaCadastroEndereco.setVisible(true);
					dispose();
				}
			}
		});
		tableConsultaEndereco.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id do Endere\u00E7o", "Nome do Cliente", "Cidade", "Bairro", "Rua", "N\u00FAmero", "Complemento", "Id do Cliente"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultaEndereco.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableConsultaEndereco.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableConsultaEndereco.getColumnModel().getColumn(2).setPreferredWidth(95);
		tableConsultaEndereco.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableConsultaEndereco.getColumnModel().getColumn(4).setPreferredWidth(105);
		tableConsultaEndereco.getColumnModel().getColumn(5).setPreferredWidth(40);
		tableConsultaEndereco.getColumnModel().getColumn(6).setPreferredWidth(160);
		tableConsultaEndereco.getColumnModel().getColumn(7).setPreferredWidth(60);
		scrollPaneConsultaEndereco.setViewportView(tableConsultaEndereco);
		
		JLabel lblNewLabelConsultaEndereco = new JLabel("Informe o nome do cliente:");
		lblNewLabelConsultaEndereco.setForeground(Color.DARK_GRAY);
		lblNewLabelConsultaEndereco.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelConsultaEndereco.setBounds(10, 18, 275, 25);
		panelConsultaEndereco.add(lblNewLabelConsultaEndereco);
		
		textFieldConsultaEndereco = new JTextField();
		textFieldConsultaEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldConsultaEndereco.setBounds(295, 20, 379, 23);
		panelConsultaEndereco.add(textFieldConsultaEndereco);
		textFieldConsultaEndereco.setColumns(10);
		
		JButton btnNewButtonConsultaEndereco = new JButton("");
		btnNewButtonConsultaEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeCliente = textFieldConsultaEndereco.getText();
				DefaultTableModel tableModel = (DefaultTableModel) tableConsultaEndereco.getModel();
				
				tableModel.setRowCount(0);
				EnderecoController enderecoControlador = new EnderecoController();
				
				try {
					ArrayList<Endereco> enderecos = enderecoControlador.consutarEndereco(nomeCliente);
					enderecos.forEach((Endereco endereco) -> {
						tableModel.addRow(new Object[] {
								endereco.getIdEndereco(),
								endereco.getCliente().getNome(),
								endereco.getCidade(),
								endereco.getBairro(),
								endereco.getRua(),
								endereco.getNumero(),
								endereco.getComplemento(),
								endereco.getCliente().getIdCliente()
						});
					});
					
					tableConsultaEndereco.setModel(tableModel);
				} 
				catch (ExceptionDAO erro) {
					erro.printStackTrace();
				}
			}
		});
		btnNewButtonConsultaEndereco.setIcon(new ImageIcon(TelaConsultaEndereco.class.getResource("/imagens/lupa.png")));
		btnNewButtonConsultaEndereco.setBounds(690, 8, 50, 46);
		panelConsultaEndereco.add(btnNewButtonConsultaEndereco);
		
		JLabel lblNewLabelAvisoConsultaEndereco = new JLabel("(Para modificar os dados ou apagar um endere\u00E7o, clique na linha correspondente)");
		lblNewLabelAvisoConsultaEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabelAvisoConsultaEndereco.setBounds(259, 461, 447, 14);
		panelConsultaEndereco.add(lblNewLabelAvisoConsultaEndereco);
		
		JLabel lblNewLabelAvisoVoltarConsultaEndereco = new JLabel("Para voltar \u00E0 tela de cadastro sem selecionar um endere\u00E7o, basta fechar esta janela");
		lblNewLabelAvisoVoltarConsultaEndereco.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabelAvisoVoltarConsultaEndereco.setForeground(Color.WHITE);
		lblNewLabelAvisoVoltarConsultaEndereco.setBounds(506, 1, 476, 14);
		contentPane.add(lblNewLabelAvisoVoltarConsultaEndereco);
	}
}
