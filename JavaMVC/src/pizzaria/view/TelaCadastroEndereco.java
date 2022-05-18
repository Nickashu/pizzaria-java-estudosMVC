package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import pizzaria.controller.ClienteController;
import pizzaria.controller.EnderecoController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaCadastroEndereco extends JFrame {
	

	private JPanel contentPane;
	private JTextField textFieldRua;
	private JTextField textFieldBairro;
	private JTextField textFieldNumero;
	private JTextField txtNomeCliente;
	
	private JTextArea textAreaComplemento = new JTextArea();
	private JComboBox comboBoxCidade = new JComboBox();
	
	private int idCliente = 0;
	private int idEndereco = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEndereco frame = new TelaCadastroEndereco();
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
	
	public TelaCadastroEndereco(TelaPrincipal telaPrincipal) {
		this();
		this.telaPrincipal = telaPrincipal;
	}
	
	public TelaCadastroEndereco() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroEndereco.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		setResizable(false);
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 594);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelTitulo = new JLabel("Cadastro de Endere\u00E7o");
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaCadastroPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setBounds(190, 22, 408, 59);
		contentPane.add(lblNewLabelTitulo);
		
		JPanel panelCadastroCliente = new JPanel();
		panelCadastroCliente.setBackground(new Color(255, 255, 204));
		panelCadastroCliente.setBounds(92, 92, 600, 432);
		contentPane.add(panelCadastroCliente);
		panelCadastroCliente.setLayout(null);
		
		JLabel lblNewLabelCidade = new JLabel("Cidade:");
		lblNewLabelCidade.setForeground(Color.DARK_GRAY);
		lblNewLabelCidade.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelCidade.setBounds(44, 141, 76, 31);
		panelCadastroCliente.add(lblNewLabelCidade);
		
		JLabel lblNewLabelRua = new JLabel("Rua:");
		lblNewLabelRua.setForeground(Color.DARK_GRAY);
		lblNewLabelRua.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelRua.setBounds(44, 229, 76, 31);
		panelCadastroCliente.add(lblNewLabelRua);
		
		textFieldRua = new JTextField();
		textFieldRua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRua.setToolTipText("Informe o nome da rua");
		textFieldRua.setBounds(154, 233, 399, 27);
		panelCadastroCliente.add(textFieldRua);
		textFieldRua.setColumns(10);
		
		JLabel lblNewLabelBairro = new JLabel("Bairro:");
		lblNewLabelBairro.setForeground(Color.DARK_GRAY);
		lblNewLabelBairro.setToolTipText("Informe o bairro");
		lblNewLabelBairro.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelBairro.setBounds(44, 185, 76, 31);
		panelCadastroCliente.add(lblNewLabelBairro);
		
		textFieldBairro = new JTextField();
		textFieldBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldBairro.setBounds(154, 189, 399, 27);
		panelCadastroCliente.add(textFieldBairro);
		textFieldBairro.setColumns(10);
		
		JLabel lblNewLabelNumero = new JLabel("N\u00FAmero:");
		lblNewLabelNumero.setForeground(Color.DARK_GRAY);
		lblNewLabelNumero.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelNumero.setBounds(44, 273, 96, 31);
		panelCadastroCliente.add(lblNewLabelNumero);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setToolTipText("Informe o n\u00FAmero da resid\u00EAncia");
		textFieldNumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNumero.setBounds(154, 277, 52, 27);
		panelCadastroCliente.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		JLabel lblNewLabelComplemento = new JLabel("Complemento (opcional):");
		lblNewLabelComplemento.setForeground(Color.DARK_GRAY);
		lblNewLabelComplemento.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelComplemento.setBounds(44, 317, 263, 31);
		panelCadastroCliente.add(lblNewLabelComplemento);
		
		textAreaComplemento.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textAreaComplemento.setLineWrap(true);
		textAreaComplemento.setToolTipText("Informe o complemento do endere\u00E7o");
		textAreaComplemento.setBounds(319, 317, 234, 43);
		panelCadastroCliente.add(textAreaComplemento);
		
		comboBoxCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxCidade.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma cidade", "Rio de Janeiro", "Duque de Caxias", "Niter\u00F3i"}));
		comboBoxCidade.setSelectedIndex(0);
		comboBoxCidade.setBounds(154, 143, 224, 27);
		panelCadastroCliente.add(comboBoxCidade);
		
		JLabel lblNewLabelCliente_endereco = new JLabel("Este endere\u00E7o pertence a qual cliente?");
		lblNewLabelCliente_endereco.setForeground(Color.DARK_GRAY);
		lblNewLabelCliente_endereco.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelCliente_endereco.setBounds(44, 11, 391, 27);
		panelCadastroCliente.add(lblNewLabelCliente_endereco);
		
		JButton btnNewButtonConsultaCliente = new JButton("Consultar e selecionar um cliente");
		btnNewButtonConsultaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaCliente telaConsultaEnderecoCli = new TelaConsultaCliente((JFrame) TelaCadastroEndereco.this);
				telaConsultaEnderecoCli.setVisible(true);
				TelaCadastroEndereco.this.dispose();
			}
		});
		btnNewButtonConsultaCliente.setForeground(Color.DARK_GRAY);
		btnNewButtonConsultaCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButtonConsultaCliente.setIcon(new ImageIcon(TelaCadastroEndereco.class.getResource("/imagens/lupa.png")));
		btnNewButtonConsultaCliente.setBounds(44, 87, 391, 43);
		panelCadastroCliente.add(btnNewButtonConsultaCliente);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeCliente.setText("Consulte os clientes e selecione um deles");
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setBounds(44, 49, 399, 27);
		panelCadastroCliente.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		JButton btnNewButtonSalvar = new JButton("Salvar");
		btnNewButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCidade.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
				}
				else {
					String cidade = comboBoxCidade.getSelectedItem().toString();
					String bairro = textFieldBairro.getText();
					String rua = textFieldRua.getText();
					String numeroTxt = textFieldNumero.getText();
					String complemento = textAreaComplemento.getText();
					boolean sucesso;
					
					try {
						EnderecoController enderecoControlador = new EnderecoController();
						
						if(idEndereco == 0) {
							sucesso = enderecoControlador.cadastrarEndereco(idCliente ,cidade, bairro, rua, numeroTxt, complemento);
							if(sucesso) {
								JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso!");
								limparTela();
							}
							else {
								JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
							}
						}
						else {
							sucesso = enderecoControlador.alterarEndereco(idEndereco, idCliente ,cidade, bairro, rua, numeroTxt, complemento);
							if(sucesso) {
								JOptionPane.showMessageDialog(null, "Endereço modificado com sucesso!");
								idEndereco = 0;
								limparTela();
							}
							else {
								JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
							}
						}
					}
					catch(Exception erro) {
						JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar este endereço: " + erro);
					}
				}
				
			}
		});
		btnNewButtonSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonSalvar.setBounds(22, 387, 96, 33);
		panelCadastroCliente.add(btnNewButtonSalvar);
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnNewButtonLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonLimpar.setBounds(128, 387, 96, 33);
		panelCadastroCliente.add(btnNewButtonLimpar);
		
		JButton btnNewButtonCancelar = new JButton("Cancelar");
		btnNewButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonCancelar.setBounds(234, 387, 107, 33);
		panelCadastroCliente.add(btnNewButtonCancelar);
		
		JButton btnNewButtonConsultar = new JButton("Consultar");
		btnNewButtonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaEndereco telaConsultaEndereco = new TelaConsultaEndereco((JFrame) TelaCadastroEndereco.this);
				telaConsultaEndereco.setVisible(true);
				TelaCadastroEndereco.this.dispose();
			}
		});
		btnNewButtonConsultar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonConsultar.setBounds(351, 387, 120, 33);
		panelCadastroCliente.add(btnNewButtonConsultar);
		
		JButton btnNewButtonApagar = new JButton("Apagar");
		btnNewButtonApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnderecoController enderecoControlador = new EnderecoController();
				try {
					if(enderecoControlador.apagarEndereco(idEndereco)) {
						JOptionPane.showMessageDialog(null, "O endereço foi apagado com sucesso!");
						idEndereco = 0;
						limparTela();
					}
					else {
						JOptionPane.showMessageDialog(null, "Erro ao apagar endereço. Por favor selecione um endereço na consulta para apagá-lo.");
					}
				} 
				catch (ExceptionDAO erro) {
					erro.printStackTrace();
				}
			}
		});
		btnNewButtonApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonApagar.setBounds(481, 387, 96, 33);
		panelCadastroCliente.add(btnNewButtonApagar);
		
		
	}
	
	private void limparTela(){
		idCliente = 0;
		comboBoxCidade.setSelectedIndex(0);
		textFieldBairro.setText("");
		textFieldRua.setText("");
		textFieldNumero.setText("");
		textAreaComplemento.setText("");
		txtNomeCliente.setText("Consulte os clientes e selecione um deles");
	}
	
	public void buscarCliente(int idCliente, String nomeCliente) {
		this.idCliente = idCliente;
		this.txtNomeCliente.setText(nomeCliente);
	}
	
	public void buscarEndereco(int idEndereco, String nomeCliente, String cidade, String bairro, String rua, int numero, String complemento, int idCliente) {
		this.idEndereco = idEndereco;
		this.txtNomeCliente.setText(nomeCliente);
		for(int contador=0; contador<this.comboBoxCidade.getItemCount(); contador++) {
			if(this.comboBoxCidade.getItemAt(contador).equals(cidade)) {
				this.comboBoxCidade.setSelectedIndex(contador);
			}
		}
		this.textFieldBairro.setText(bairro);
		this.textFieldRua.setText(rua);
		this.textFieldNumero.setText(Integer.toString(numero));
		this.textAreaComplemento.setText(complemento);
		this.idCliente = idCliente;
	}
}

