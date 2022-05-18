package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pizzaria.controller.ClienteController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Cliente;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TelaConsultaCliente extends JFrame {

	

	private JPanel contentPane;
	private JTextField textFieldNomeCliente;
	private JTable tableConsultaCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaCliente frame = new TelaConsultaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	private JFrame telaCadastro;
	
	public TelaConsultaCliente(JFrame telaCadastro) {
		this();   //Sobrecarga de construtores
		this.telaCadastro = telaCadastro;
	}
	
	public TelaConsultaCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConsultaCliente.class.getResource("/imagens/icone2_pizzaMenorr.png")));
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
		
		setResizable(false);
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 799, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelTitulo = new JLabel("Consulta de Cliente");
		lblNewLabelTitulo.setForeground(Color.BLACK);
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaCadastroPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setBounds(200, 11, 381, 59);
		contentPane.add(lblNewLabelTitulo);
		
		JPanel panelCadastroCliente = new JPanel();
		panelCadastroCliente.setBackground(new Color(255, 255, 204));
		panelCadastroCliente.setBounds(10, 92, 763, 462);
		contentPane.add(panelCadastroCliente);
		panelCadastroCliente.setLayout(null);
		
		JLabel lblNewLabelNomeCliente = new JLabel("Informe o nome do cliente:");
		lblNewLabelNomeCliente.setForeground(Color.DARK_GRAY);
		lblNewLabelNomeCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelNomeCliente.setBounds(10, 11, 282, 31);
		panelCadastroCliente.add(lblNewLabelNomeCliente);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNomeCliente.setToolTipText("Digite o nome do cliente");
		textFieldNomeCliente.setBounds(295, 17, 398, 23);
		panelCadastroCliente.add(textFieldNomeCliente);
		textFieldNomeCliente.setColumns(10);
		
		JScrollPane scrollPaneConsultaCliente = new JScrollPane();
		scrollPaneConsultaCliente.setBounds(10, 64, 743, 371);
		panelCadastroCliente.add(scrollPaneConsultaCliente);
		
		tableConsultaCliente = new JTable();
		tableConsultaCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int idCliente = (Integer) tableConsultaCliente.getModel().getValueAt(tableConsultaCliente.getSelectedRow(), 0);
					String nomeCliente = (String) tableConsultaCliente.getModel().getValueAt(tableConsultaCliente.getSelectedRow(), 1);
					String telefone = (String) tableConsultaCliente.getModel().getValueAt(tableConsultaCliente.getSelectedRow(), 2);
					String email = (String) tableConsultaCliente.getModel().getValueAt(tableConsultaCliente.getSelectedRow(), 3);
					String cpf = (String) tableConsultaCliente.getModel().getValueAt(tableConsultaCliente.getSelectedRow(), 4);
					Date dtNascimento = (Date) tableConsultaCliente.getModel().getValueAt(tableConsultaCliente.getSelectedRow(), 5);
					
					String tipoTela = telaCadastro.getClass().getSimpleName();
					
					if(tipoTela.equals("TelaCadastroCliente")) {
						TelaCadastroCliente telaCadastroCliente = (TelaCadastroCliente) telaCadastro;
						telaCadastroCliente.buscarCliente(idCliente, nomeCliente, telefone, email, cpf, dtNascimento);
						telaCadastroCliente.setVisible(true);

					}
					else if(tipoTela.equals("TelaCadastroEndereco")){
						TelaCadastroEndereco telaCadastroEndereco = (TelaCadastroEndereco) telaCadastro;
						telaCadastroEndereco.buscarCliente(idCliente, nomeCliente);
						telaCadastroEndereco.setVisible(true);
					}
					else {
						TelaFazerPedido telaFazerPedido = (TelaFazerPedido) telaCadastro;
						try {
							telaFazerPedido.buscarCliente(idCliente, nomeCliente);
						} 
						catch (ExceptionDAO erro) {
							erro.printStackTrace();
						}
						telaFazerPedido.setVisible(true);
					}
					dispose();
					
				}
			}
		});
	
		tableConsultaCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id_Cliente", "Nome", "Telefone", "E-mail", "CPF", "Data de nascimento"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultaCliente.getColumnModel().getColumn(0).setPreferredWidth(49);
		tableConsultaCliente.getColumnModel().getColumn(1).setPreferredWidth(149);
		tableConsultaCliente.getColumnModel().getColumn(2).setPreferredWidth(95);
		tableConsultaCliente.getColumnModel().getColumn(3).setPreferredWidth(170);
		tableConsultaCliente.getColumnModel().getColumn(4).setPreferredWidth(86);
		tableConsultaCliente.getColumnModel().getColumn(5).setPreferredWidth(109);
		scrollPaneConsultaCliente.setViewportView(tableConsultaCliente);
		
		
		JButton btnNewButtonConsultarCliente = new JButton("");
		btnNewButtonConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNomeCliente.getText();
				DefaultTableModel tableModel = (DefaultTableModel) tableConsultaCliente.getModel();
				tableModel.setRowCount(0);  //Para limpar os resultados a cada busca que o usuário fizer
				ClienteController clienteControlador = new ClienteController();
				
				try {
					ArrayList<Cliente> clientes = clienteControlador.consultarClientes(nome);
					clientes.forEach((Cliente cliente) -> {
						tableModel.addRow(new Object[] {
								cliente.getIdCliente(),
								cliente.getNome(),
								cliente.getTelefone(),
								cliente.getEmail(),
								cliente.getCpf(),
								cliente.getDataNascimento()
						});
					});
					
					tableConsultaCliente.setModel(tableModel);
				}
				catch(ExceptionDAO erro) {
					Logger.getLogger(TelaCadastroCliente.class.getName()).log(Level.SEVERE, null, erro);
				}
			}
		});
		btnNewButtonConsultarCliente.setIcon(new ImageIcon(TelaConsultaCliente.class.getResource("/imagens/lupa.png")));
		btnNewButtonConsultarCliente.setBounds(703, 7, 50, 44);
		panelCadastroCliente.add(btnNewButtonConsultarCliente);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setVisible(false);
		btnNewButton.setBackground(new Color(255, 153, 153));
		btnNewButton.setBounds(651, 37, 89, 23);
		contentPane.add(btnNewButton);
		
		/*
		if(telaCadastro.getClass().getSimpleName().equals("TelaCadastroCliente")) {
			JLabel lblNewLabel = new JLabel("(Para modificar os dados ou apagar um cliente, clique na linha correspondente)");
			lblNewLabel.setForeground(Color.DARK_GRAY);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(155, 440, 431, 14);
			panelCadastroCliente.add(lblNewLabel);

		}
		*/
	}
	
}

