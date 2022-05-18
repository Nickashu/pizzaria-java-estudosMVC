package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import pizzaria.controller.ClienteController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.awt.Toolkit;

public class TelaCadastroCliente extends JFrame {
	

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JFormattedTextField formattedTextFieldTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
	private JFormattedTextField formattedTextFieldCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
	private JFormattedTextField formattedTextFieldDtNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
	
	private int idCliente = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente frame = new TelaCadastroCliente();
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
	private TelaPrincipal telaPrincipal;
	
	public TelaCadastroCliente(TelaPrincipal telaPrincipal) throws ParseException {
		this();
		this.telaPrincipal = telaPrincipal;
	}
	
	public TelaCadastroCliente() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroCliente.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		setResizable(false);
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelTitulo = new JLabel("Cadastro de Cliente");
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaCadastroPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setBounds(193, 22, 371, 59);
		contentPane.add(lblNewLabelTitulo);
		
		JPanel panelCadastroCliente = new JPanel();
		panelCadastroCliente.setBackground(new Color(255, 255, 204));
		panelCadastroCliente.setBounds(92, 92, 600, 352);
		contentPane.add(panelCadastroCliente);
		panelCadastroCliente.setLayout(null);
		
		JLabel lblNewLabelNome = new JLabel("Nome completo:");
		lblNewLabelNome.setForeground(Color.DARK_GRAY);
		lblNewLabelNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelNome.setBounds(44, 26, 170, 31);
		panelCadastroCliente.add(lblNewLabelNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNome.setToolTipText("Digite o nome do cliente");
		textFieldNome.setBounds(220, 30, 333, 27);
		panelCadastroCliente.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabelTelefone = new JLabel("Telefone celular:");
		lblNewLabelTelefone.setForeground(Color.DARK_GRAY);
		lblNewLabelTelefone.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelTelefone.setBounds(44, 76, 179, 31);
		panelCadastroCliente.add(lblNewLabelTelefone);
		
		formattedTextFieldTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldTelefone.setToolTipText("Informe o n\u00FAmero de celular do cliente");
		formattedTextFieldTelefone.setBounds(233, 80, 320, 27);
		panelCadastroCliente.add(formattedTextFieldTelefone);
		
		JLabel lblNewLabelEmail = new JLabel("E-mail:");
		lblNewLabelEmail.setForeground(Color.DARK_GRAY);
		lblNewLabelEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelEmail.setBounds(44, 126, 76, 31);
		panelCadastroCliente.add(lblNewLabelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEmail.setToolTipText("Informe o E-mail do cliente");
		textFieldEmail.setBounds(154, 130, 399, 27);
		panelCadastroCliente.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabelCPF = new JLabel("CPF:");
		lblNewLabelCPF.setForeground(Color.DARK_GRAY);
		lblNewLabelCPF.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelCPF.setToolTipText("");
		lblNewLabelCPF.setBounds(44, 176, 58, 27);
		panelCadastroCliente.add(lblNewLabelCPF);
		
		formattedTextFieldCPF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldCPF.setToolTipText("Informe o CPF do cliente");
		formattedTextFieldCPF.setBounds(154, 176, 399, 27);
		panelCadastroCliente.add(formattedTextFieldCPF);
		
		JLabel lblNewLabelDtNascimento = new JLabel("Data de Nascimento:");
		lblNewLabelDtNascimento.setForeground(Color.DARK_GRAY);
		lblNewLabelDtNascimento.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelDtNascimento.setBounds(44, 226, 215, 31);
		panelCadastroCliente.add(lblNewLabelDtNascimento);
		
		formattedTextFieldDtNascimento.setToolTipText("Informe a data de nascimento");
		formattedTextFieldDtNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldDtNascimento.setBounds(269, 230, 107, 27);
		panelCadastroCliente.add(formattedTextFieldDtNascimento);
		
		
		JButton btnNewButtonSalvar = new JButton("Salvar");
		btnNewButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int validacao;
				String nome = textFieldNome.getText();
				String telefone = formattedTextFieldTelefone.getText();
				String email = textFieldEmail.getText();
				String cpf = formattedTextFieldCPF.getText();
				String dataNascimento = formattedTextFieldDtNascimento.getText();
				
				try {
					ClienteController clienteControlador = new ClienteController();
					if(idCliente == 0) {
						validacao = clienteControlador.cadastrarCliente(nome, telefone, email, cpf, dataNascimento);
						if(validacao == 0) {
							JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
							limparTela();
						}
						else if(validacao == 1){
							JOptionPane.showMessageDialog(null, "A data informada é inválida ou o cliente não é maior de idade!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
						}
					}
					else {
						validacao = clienteControlador.alterarCliente(idCliente, nome, telefone, email, cpf, dataNascimento);
						if(validacao == 0) {
							JOptionPane.showMessageDialog(null, "Cliente modificado com sucesso!");
							idCliente = 0;
							limparTela();
						}
						else if(validacao == 1){
							JOptionPane.showMessageDialog(null, "A data informada é inválida ou o cliente não é maior de idade!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
						}
					}
					
				}
				catch(Exception erro) {
					JOptionPane.showMessageDialog(null, "O cliente não pôde ser cadastrado. Erro: " + erro);
				}
			}
		});
		btnNewButtonSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonSalvar.setBounds(30, 289, 94, 33);
		panelCadastroCliente.add(btnNewButtonSalvar);
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();	
			}
		});
		btnNewButtonLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonLimpar.setBounds(134, 289, 94, 33);
		panelCadastroCliente.add(btnNewButtonLimpar);
		
		JButton btnNewButtonCancelar = new JButton("Cancelar");
		btnNewButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.setVisible(true);
				dispose();
				//TelaPrincipal telaPrincipal = new TelaPrincipal();
				//telaPrincipal.setVisible(true);
			}
		});
		btnNewButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonCancelar.setBounds(236, 289, 107, 33);
		panelCadastroCliente.add(btnNewButtonCancelar);
		
		
		JButton btnNewButtonConsultar = new JButton("Consultar");
		btnNewButtonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaCliente telaConsultaCliente = new TelaConsultaCliente((JFrame) TelaCadastroCliente.this);
				telaConsultaCliente.setVisible(true);
				TelaCadastroCliente.this.dispose();
			}
		});
		btnNewButtonConsultar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonConsultar.setBounds(353, 289, 115, 33);
		panelCadastroCliente.add(btnNewButtonConsultar);
		
		JButton btnNewButtonApagar = new JButton("Apagar");
		btnNewButtonApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso;
				ClienteController clienteControlador = new ClienteController();
				try {
					sucesso = clienteControlador.apagarCliente(idCliente);
					
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso!");
						idCliente = 0;
						limparTela();
					}
					else {
						JOptionPane.showMessageDialog(null, "Erro ao apagar cliente. Por favor selecione um cliente na consulta para apagá-lo.");
					}
					
				} 
				catch (ExceptionDAO erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível apagar este cliente pois provavelmente ele consta em algum dos pedidos já realizados.");
				}
			}
		});
		btnNewButtonApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonApagar.setBounds(478, 289, 94, 33);
		panelCadastroCliente.add(btnNewButtonApagar);
		
		JLabel lblNewLabelAvisoCadastroCliente = new JLabel("Para alterar os dados de algum cliente ou apag\u00E1-lo, consulte os clientes e clique na linha correspondente");
		lblNewLabelAvisoCadastroCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabelAvisoCadastroCliente.setBounds(36, 455, 714, 23);
		contentPane.add(lblNewLabelAvisoCadastroCliente);
	}
	
	private void limparTela() {
		textFieldNome.setText("");
		formattedTextFieldTelefone.setText("");
		textFieldEmail.setText("");
		formattedTextFieldCPF.setText("");
		formattedTextFieldDtNascimento.setText("");
	}
	
	public void buscarCliente(int idCliente, String nomeCliente, String telefone, String email, String cpf, Date dtNascimento){
		this.idCliente = idCliente;
		this.textFieldNome.setText(nomeCliente);
		this.formattedTextFieldTelefone.setText(telefone);
		this.textFieldEmail.setText(email);
		this.formattedTextFieldCPF.setText(cpf);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.formattedTextFieldDtNascimento.setText(format.format(dtNascimento));
	}
	
	
}