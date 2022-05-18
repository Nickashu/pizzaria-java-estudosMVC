package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pizzaria.controller.PizzaController;
import pizzaria.dao.ExceptionDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TelaCadastroPizza extends JFrame {
	

	private JPanel contentPane;
	private JTextField textFieldSabor;
	private JTextField textFieldPreco;
	private JTextArea textAreaIngredientes = new JTextArea();
	
	private int idPizza = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroPizza frame = new TelaCadastroPizza();
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
	
	public TelaCadastroPizza(TelaPrincipal telaPrincipal) {
		this();
		this.telaPrincipal = telaPrincipal;
	}
	  
	public TelaCadastroPizza() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastroPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		setResizable(false);
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabelTitulo = new JLabel("Cadastro de Pizza");
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaCadastroPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setBounds(213, 21, 345, 59);
		contentPane.add(lblNewLabelTitulo);
		
		JPanel panelCadastroPizza = new JPanel();
		panelCadastroPizza.setBackground(new Color(255, 255, 204));
		panelCadastroPizza.setBounds(92, 92, 600, 352);
		contentPane.add(panelCadastroPizza);
		panelCadastroPizza.setLayout(null);
		
		JLabel lblNewLabelSabor = new JLabel("Sabor:");
		lblNewLabelSabor.setForeground(Color.DARK_GRAY);
		lblNewLabelSabor.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelSabor.setBounds(98, 60, 66, 31);
		panelCadastroPizza.add(lblNewLabelSabor);
		
		textFieldSabor = new JTextField();
		textFieldSabor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldSabor.setToolTipText("Digite o sabor da pizza");
		textFieldSabor.setBounds(178, 61, 399, 33);
		panelCadastroPizza.add(textFieldSabor);
		textFieldSabor.setColumns(10);
		
		JLabel lblNewLabelIngredientes = new JLabel("Ingredientes:");
		lblNewLabelIngredientes.setForeground(Color.DARK_GRAY);
		lblNewLabelIngredientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelIngredientes.setBounds(26, 112, 138, 25);
		panelCadastroPizza.add(lblNewLabelIngredientes);
		
		textAreaIngredientes.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textAreaIngredientes.setLineWrap(true);
		textAreaIngredientes.setToolTipText("Digite os ingredientes desta pizza");
		textAreaIngredientes.setBounds(178, 116, 399, 69);
		panelCadastroPizza.add(textAreaIngredientes);
		
		JLabel lblNewLabelPreco = new JLabel("Pre\u00E7o em R$ (Tamanho m\u00E9dio):");
		lblNewLabelPreco.setForeground(Color.DARK_GRAY);
		lblNewLabelPreco.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelPreco.setBounds(26, 196, 320, 31);
		panelCadastroPizza.add(lblNewLabelPreco);
		
		textFieldPreco = new JTextField();
		textFieldPreco.setBounds(353, 198, 120, 31);
		panelCadastroPizza.add(textFieldPreco);
		textFieldPreco.setColumns(10);
		
		JButton btnNewButtonSalvar = new JButton("Salvar");
		btnNewButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sabor = textFieldSabor.getText();
				try {
					if(verificaSaborPizza(sabor) || idPizza != 0) {
						String ingredientes = textAreaIngredientes.getText();
						String precoValor = textFieldPreco.getText();
						boolean sucesso;
						
						try {
							PizzaController pizzaControlador = new PizzaController();
							if(idPizza == 0) {
								sucesso = pizzaControlador.cadastrarPizza(sabor, ingredientes, precoValor);
								if(sucesso) {
									JOptionPane.showMessageDialog(null, "Pizza cadastrada com sucesso!");
									limparTela();
									
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados não foram preechidos corretamente!");
								}
							}
							else {
								sucesso = pizzaControlador.alterarPizza(idPizza, sabor, ingredientes, precoValor);
								if(sucesso) {
									JOptionPane.showMessageDialog(null, "Pizza modificada com sucesso!");
									idPizza = 0;
									limparTela();
									
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados não foram preechidos corretamente!");
								}
							}
							
						}
						catch(Exception erro) {
							JOptionPane.showMessageDialog(null, "A pizza não foi cadastrada. Erro: " + erro);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Já existe uma pizza cadastrada com este nome");
					}
				}
				catch (Exception erro) {
					erro.printStackTrace();
				}

			}
		});
		btnNewButtonSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonSalvar.setBounds(26, 291, 94, 33);
		panelCadastroPizza.add(btnNewButtonSalvar);
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnNewButtonLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonLimpar.setBounds(130, 291, 94, 33);
		panelCadastroPizza.add(btnNewButtonLimpar);
		
		JButton btnNewButtonCancelar = new JButton("Cancelar");
		btnNewButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonCancelar.setBounds(234, 291, 108, 33);
		panelCadastroPizza.add(btnNewButtonCancelar);
		
		JButton btnNewButtonConsultar = new JButton("Consultar");
		btnNewButtonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaPizza telaConsultaPizza = new TelaConsultaPizza((JFrame) TelaCadastroPizza.this);
				telaConsultaPizza.setVisible(true);
				dispose();

			}
		});
		btnNewButtonConsultar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonConsultar.setBounds(352, 291, 121, 33);
		panelCadastroPizza.add(btnNewButtonConsultar);
		
		JButton btnNewButtonApagar = new JButton("Apagar");
		btnNewButtonApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean sucesso;
				PizzaController pizzaControlador = new PizzaController();
				try {
					sucesso = pizzaControlador.apagarPizza(idPizza);
					
					if(sucesso) {
						JOptionPane.showMessageDialog(null, "Pizza apagada com sucesso!");
						idPizza = 0;
						limparTela();
					}
					else {
						JOptionPane.showMessageDialog(null, "Erro ao apagar pizza. Por favor selecione uma pizza na consulta para apagá-la.");
					}
					
				} 
				catch (ExceptionDAO erro) {
					JOptionPane.showMessageDialog(null, "Não foi possível apagar esta bebida pois provavelmente ela consta em algum dos pedidos já realizados.");
				}
			}
		});
		btnNewButtonApagar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonApagar.setBounds(483, 291, 94, 32);
		panelCadastroPizza.add(btnNewButtonApagar);
		
		JLabel lblNewLabelAvisoPrecoCadastroPizza = new JLabel("Este ser\u00E1 o pre\u00E7o do tamanho m\u00E9dio da pizza (No m\u00EDnimo R$25,00)");
		lblNewLabelAvisoPrecoCadastroPizza.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelAvisoPrecoCadastroPizza.setBounds(164, 238, 413, 14);
		panelCadastroPizza.add(lblNewLabelAvisoPrecoCadastroPizza);
	}
	
	public void limparTela() {
		textFieldSabor.setText("");
		textAreaIngredientes.setText("");
		textFieldPreco.setText("");
	}
	
	public void buscarPizza(int idPizza, String sabor, String ingredientes, double preco){
		this.idPizza = idPizza;
		this.textFieldSabor.setText(sabor);
		this.textAreaIngredientes.setText(ingredientes);
		this.textFieldPreco.setText(Double.toString(preco));
	}
	
	public boolean verificaSaborPizza(String saborPizzaVerifica) throws ExceptionDAO {
		return new PizzaController().verificaSaborPizza(saborPizzaVerifica);
	}
}
