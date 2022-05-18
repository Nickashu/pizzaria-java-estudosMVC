package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pizzaria.controller.BebidaController;
import pizzaria.controller.ClienteController;
import pizzaria.controller.PizzaController;
import pizzaria.dao.ExceptionDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaCadastroBebida extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	
	private int idBebida = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroBebida frame = new TelaCadastroBebida();
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
	private TelaPrincipal telaPrincipal;
	
	public TelaCadastroBebida(TelaPrincipal telaPrincipal) {
		this();
		this.telaPrincipal = telaPrincipal;
	}
	
	public TelaCadastroBebida() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroBebida.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		setResizable(false);
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelCadastroBebida = new JLabel("Cadastro de Bebida");
		lblNewLabelCadastroBebida.setIcon(new ImageIcon(TelaCadastroBebida.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelCadastroBebida.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelCadastroBebida.setBounds(166, 22, 370, 50);
		contentPane.add(lblNewLabelCadastroBebida);
		
		JPanel panelCadastroBebida = new JPanel();
		panelCadastroBebida.setBackground(new Color(255, 255, 204));
		panelCadastroBebida.setBounds(60, 83, 588, 275);
		contentPane.add(panelCadastroBebida);
		panelCadastroBebida.setLayout(null);
		
		JLabel lblNewLabelNome = new JLabel("Nome da bebida:");
		lblNewLabelNome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelNome.setForeground(Color.DARK_GRAY);
		lblNewLabelNome.setBounds(26, 32, 181, 25);
		panelCadastroBebida.add(lblNewLabelNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNome.setToolTipText("Digite o nome da bebida");
		textFieldNome.setBounds(217, 34, 286, 25);
		panelCadastroBebida.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabelPreco = new JLabel("Pre\u00E7o da bebida (lata/garrafa de 500ml) em R$:");
		lblNewLabelPreco.setForeground(Color.DARK_GRAY);
		lblNewLabelPreco.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelPreco.setBounds(26, 107, 501, 25);
		panelCadastroBebida.add(lblNewLabelPreco);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setToolTipText("Digite o pre\u00E7o da bebida");
		textFieldPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldPreco.setColumns(10);
		textFieldPreco.setBounds(26, 143, 93, 25);
		panelCadastroBebida.add(textFieldPreco);
		
		JButton btnNewButtonSalvar = new JButton("Salvar");
		btnNewButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textFieldNome.getText();
				try {
					if(verificaNomeBebida(nome) || idBebida != 0) {
						String precoTxt = textFieldPreco.getText();
						boolean sucesso;
						
						try {
							BebidaController bebidaControlador = new BebidaController();
							if(idBebida == 0) {
								sucesso = bebidaControlador.cadastrarBebida(nome, precoTxt);
								if(sucesso) {
									JOptionPane.showMessageDialog(null, "Bebida cadastrada com sucesso!");
									limparTela();
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados não foram preechidos corretamente!");
								}
							}
							else {
								sucesso = bebidaControlador.alterarBebida(idBebida, nome, precoTxt);
								if(sucesso) {
									JOptionPane.showMessageDialog(null, "Bebida modificada com sucesso!");
									idBebida = 0;
									limparTela();
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados não foram preechidos corretamente!");
								}
							}
						}
						catch(Exception erro) {
							JOptionPane.showMessageDialog(null, "A bebida não foi cadastrada. Erro: " + erro);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Já existe uma bebida cadastrada com este nome");
					}
				}
				catch (Exception erro) {
					erro.printStackTrace();
				}
			}
		});
		btnNewButtonSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonSalvar.setBounds(26, 198, 93, 31);
		panelCadastroBebida.add(btnNewButtonSalvar);
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnNewButtonLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonLimpar.setBounds(129, 198, 93, 31);
		panelCadastroBebida.add(btnNewButtonLimpar);
		
		JButton btnNewButtonCancelar = new JButton("Cancelar");
		btnNewButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.setVisible(true);
				dispose();
				/*
				dispose();
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				*/
			}
		});
		btnNewButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonCancelar.setBounds(229, 198, 107, 31);
		panelCadastroBebida.add(btnNewButtonCancelar);
		
		JButton btnNewButtonConsultar = new JButton("Consultar");
		btnNewButtonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaBebida telaConsultaBebida = new TelaConsultaBebida((JFrame) TelaCadastroBebida.this);
				telaConsultaBebida.setVisible(true);
				dispose();
			}
		});
		btnNewButtonConsultar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonConsultar.setBounds(346, 198, 117, 31);
		panelCadastroBebida.add(btnNewButtonConsultar);
		
		JButton btnNewButtonApagar = new JButton("Apagar");
		btnNewButtonApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso;
				BebidaController bebidaControlador = new BebidaController();
				try {
					sucesso = bebidaControlador.apagarBebida(idBebida);
					
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Bebida apagada com sucesso!");
						idBebida = 0;
						textFieldNome.setText("");
						textFieldPreco.setText("");	
					}
					else {
						JOptionPane.showMessageDialog(null, "Erro ao apagar bebida. Por favor selecione uma bebida na consulta para apagá-la.");
					}
					
				} 
				catch (ExceptionDAO erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível apagar esta pizza pois provavelmente ela consta em algum dos pedidos já realizados.");
				}
			}
		});
		btnNewButtonApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonApagar.setBounds(471, 198, 93, 31);
		panelCadastroBebida.add(btnNewButtonApagar);
		
		JLabel lblNewLabelAvisoCadastroBebida = new JLabel("Este ser\u00E1 o pre\u00E7o do tamanho pequeno desta bebida (No m\u00EDnimo R$2,00)");
		lblNewLabelAvisoCadastroBebida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabelAvisoCadastroBebida.setBounds(129, 148, 410, 14);
		panelCadastroBebida.add(lblNewLabelAvisoCadastroBebida);
		
	}
	
	public void limparTela() {
		textFieldNome.setText("");
		textFieldPreco.setText("");	
	}
	
	public void buscarBebida(int idBebida, String nomeBebida, double precoBebida) {
		this.idBebida = idBebida;
		this.textFieldNome.setText(nomeBebida);
		this.textFieldPreco.setText(Double.toString(precoBebida));
		
	}
	
	public boolean verificaNomeBebida(String nomeBebidaVerifica) throws ExceptionDAO{
		return new BebidaController().verificaNomeBebida(nomeBebidaVerifica);
	}
}
