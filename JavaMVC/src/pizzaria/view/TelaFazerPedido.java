package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pizzaria.controller.BebidaController;
import pizzaria.controller.EnderecoController;
import pizzaria.controller.PedidoController;
import pizzaria.controller.PizzaController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Bebida;
import pizzaria.model.Cliente;
import pizzaria.model.Endereco;
import pizzaria.model.Pizza;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TelaFazerPedido extends JFrame {
	
	private int idCliente = 0;
	private boolean isAbertoSegundaPizza = false;
	private boolean isAbertoSegundaBebida = false;
	//private static int idPedidoAtualmente = 1;
	
	private JLabel lblNewLabelTituloPizzaPedido = new JLabel("Pizza");
	private JPanel panelFazerPedido = new JPanel();
	
	private JComboBox comboBoxSaborPizza1 = new JComboBox();
	private JComboBox comboBoxNomeBebida1 = new JComboBox();
	
	private JComboBox comboBoxSaborPizza2 = new JComboBox();
	private JComboBox comboBoxNomeBebida2 = new JComboBox();
	
	private JComboBox comboBoxTamanhoPizza1 = new JComboBox();
	private JComboBox comboBoxTamanhoBebida1 = new JComboBox();
	
	private JComboBox comboBoxTamanhoPizza2 = new JComboBox();
	private JComboBox comboBoxTamanhoBebida2 = new JComboBox();
	
	private JComboBox comboBoxEndereco = new JComboBox();
	
	private JLabel lblNewLabelPrecoTotal = new JLabel("0,00");
	private JLabel lblNewLabelSelecioneEnd = new JLabel("Selecione o endere\u00E7o para a entrega:");
	private JLabel lblNewLabelPizza2Pedido = new JLabel("Escolha a pizza:");
	private JLabel lblNewLabelBebida2Pedido = new JLabel("Escolha a bebida:");
	
	private JButton btnNewButtonApagarBebida2 = new JButton("");
	private JButton btnNewButtonApagarPizza2 = new JButton("");
	private JButton btnNewButtonOutraBebidaPedido = new JButton("");
	private JButton btnNewButtonOutraPizzaPedido = new JButton("");
	
	private JPanel contentPane;
	
	private double precoTotal = Double.parseDouble(lblNewLabelPrecoTotal.getText().replace(",", "."));
	
	private int validacaoPizza1 = 0;
	private int validacaoBebida1 = 0;
	private int validacaoPizza2 = 0;
	private int validacaoBebida2 = 0;
	
	private double precoAjudaPizza1 = 0;
	private int idPizzaAjuda1 = 0;
	private double precoAjudaPizza2 = 0;
	private int idPizzaAjuda2 = 0;
	
	private double precoAjudaBebida1 = 0;
	private int idBebidaAjuda1 = 0;
	private double precoAjudaBebida2 = 0;
	private int idBebidaAjuda2 = 0;
	
	private PizzaController pizzaControlador = new PizzaController();
	private BebidaController bebidaControlador = new BebidaController();
	private EnderecoController enderecoControlador = new EnderecoController();
	private ArrayList<Endereco> enderecos;
	
	private ArrayList<Pizza> pizzas = pizzaControlador.listarPizza();
	private ArrayList<Bebida> bebidas = bebidaControlador.listarBebida();
	
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Calendar calTemp = Calendar.getInstance();
	int anoAtual = calTemp.get(Calendar.YEAR);
	int mesAtual = calTemp.get(Calendar.MONTH) + 1;
	int diaAtual = calTemp.get(Calendar.DAY_OF_MONTH);
	String dataAtualTxt = diaAtual + "/" + mesAtual + "/" + anoAtual;
	
	DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFazerPedido frame = new TelaFazerPedido();
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
	
	public TelaFazerPedido(TelaPrincipal telaPrincipal) throws ExceptionDAO {
		this();
		this.telaPrincipal = telaPrincipal;
	}
	
	public TelaFazerPedido() throws ExceptionDAO {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFazerPedido.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 810);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panelFazerPedido.setBackground(new Color(255, 255, 204));
		panelFazerPedido.setBounds(10, 86, 964, 670);
		contentPane.add(panelFazerPedido);
		panelFazerPedido.setLayout(null);
		
		JLabel lblNewLabelClientePedido = new JLabel("Quem est\u00E1 fazendo o pedido?");
		lblNewLabelClientePedido.setForeground(Color.DARK_GRAY);
		lblNewLabelClientePedido.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabelClientePedido.setBounds(10, 17, 343, 25);
		panelFazerPedido.add(lblNewLabelClientePedido);
		
		JButton btnNewButtonSelecionarCliente = new JButton("Selecionar Cliente");
		btnNewButtonSelecionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaCliente telaConsultaFazerPedidoCli = new TelaConsultaCliente((JFrame) TelaFazerPedido.this);
				telaConsultaFazerPedidoCli.setVisible(true);
				TelaFazerPedido.this.dispose();
			}
		});
		btnNewButtonSelecionarCliente.setIcon(new ImageIcon(TelaFazerPedido.class.getResource("/imagens/lupa.png")));
		btnNewButtonSelecionarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonSelecionarCliente.setBounds(370, 11, 252, 40);
		panelFazerPedido.add(btnNewButtonSelecionarCliente);
		
		JSeparator separatorTituloPizza = new JSeparator();
		separatorTituloPizza.setBounds(0, 117, 964, 2);
		panelFazerPedido.add(separatorTituloPizza);
		
		lblNewLabelTituloPizzaPedido.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTituloPizzaPedido.setBounds(10, 130, 86, 25);
		panelFazerPedido.add(lblNewLabelTituloPizzaPedido);
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setBounds(10, 73, 234, 27);
		panelFazerPedido.add(textFieldNomeCliente);
		textFieldNomeCliente.setColumns(10);
		
		
		comboBoxSaborPizza1.setModel(new DefaultComboBoxModel(new String[] {"Selecione um dos sabores cadastrados"}));
		comboBoxSaborPizza1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSaborPizza1.setBounds(180, 182, 388, 25);
		panelFazerPedido.add(comboBoxSaborPizza1);
		
		listarPizzaComboBox();
		
		JLabel lblNewLabelPizza1Pedido = new JLabel("Escolha a pizza:");
		lblNewLabelPizza1Pedido.setForeground(Color.DARK_GRAY);
		lblNewLabelPizza1Pedido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelPizza1Pedido.setBounds(10, 180, 160, 25);
		panelFazerPedido.add(lblNewLabelPizza1Pedido);
		
		
		comboBoxTamanhoPizza1.setModel(new DefaultComboBoxModel(new String[] {"Selecione um tamanho", "Pequena", "M\u00E9dia", "Grande"}));
		comboBoxTamanhoPizza1.setBounds(589, 182, 169, 25);
		panelFazerPedido.add(comboBoxTamanhoPizza1);
		
		JLabel lblNewLabelTituloBebidaPedido = new JLabel("Bebida");
		lblNewLabelTituloBebidaPedido.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTituloBebidaPedido.setBounds(10, 346, 110, 25);
		panelFazerPedido.add(lblNewLabelTituloBebidaPedido);
		
		comboBoxNomeBebida1.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma das bebidas cadastradas"}));
		comboBoxNomeBebida1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxNomeBebida1.setBounds(194, 403, 311, 25);
		panelFazerPedido.add(comboBoxNomeBebida1);
		
		listarBebidaComboBox();
		
		JLabel lblNewLabelBebida1Pedido = new JLabel("Escolha a bebida:");
		lblNewLabelBebida1Pedido.setForeground(Color.DARK_GRAY);
		lblNewLabelBebida1Pedido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelBebida1Pedido.setBounds(10, 402, 176, 25);
		panelFazerPedido.add(lblNewLabelBebida1Pedido);
		
		comboBoxTamanhoBebida1.setModel(new DefaultComboBoxModel(new String[] {"Selecione um tamanho", "Lata pequena/Garrafa de 500ml", "Garrafa de 1L", "Garrafa de 2L"}));
		comboBoxTamanhoBebida1.setBounds(527, 403, 196, 25);
		panelFazerPedido.add(comboBoxTamanhoBebida1);
		
		
		JLabel lblNewLabelPrecoPedido = new JLabel("Pre\u00E7o total: R$");
		lblNewLabelPrecoPedido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelPrecoPedido.setBounds(715, 636, 150, 25);
		panelFazerPedido.add(lblNewLabelPrecoPedido);
		
		btnNewButtonOutraPizzaPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButtonOutraPizzaPedido.setVisible(false);
				lblNewLabelPizza2Pedido.setVisible(true);
				comboBoxSaborPizza2.setVisible(true);
				comboBoxTamanhoPizza2.setVisible(true);
				btnNewButtonApagarPizza2.setVisible(true);
				isAbertoSegundaPizza = true;
			}
		});
		btnNewButtonOutraPizzaPedido.setIcon(new ImageIcon(TelaFazerPedido.class.getResource("/imagens/icone_maiss.png")));
		btnNewButtonOutraPizzaPedido.setBounds(10, 257, 35, 35);
		panelFazerPedido.add(btnNewButtonOutraPizzaPedido);
		
		btnNewButtonOutraBebidaPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButtonOutraBebidaPedido.setVisible(false);
				lblNewLabelBebida2Pedido.setVisible(true);
				comboBoxNomeBebida2.setVisible(true);
				comboBoxTamanhoBebida2.setVisible(true);
				btnNewButtonApagarBebida2.setVisible(true);
				isAbertoSegundaBebida = true;
			}
		});
		btnNewButtonOutraBebidaPedido.setIcon(new ImageIcon(TelaFazerPedido.class.getResource("/imagens/icone_maiss.png")));
		btnNewButtonOutraBebidaPedido.setBounds(10, 468, 35, 35);
		panelFazerPedido.add(btnNewButtonOutraBebidaPedido);
		
		JSeparator separatorPizzaBebida = new JSeparator();
		separatorPizzaBebida.setBounds(0, 333, 964, 2);
		panelFazerPedido.add(separatorPizzaBebida);
		
		lblNewLabelPrecoTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelPrecoTotal.setBounds(869, 636, 95, 25);
		panelFazerPedido.add(lblNewLabelPrecoTotal);
		
		JButton btnNewButtonFazerPedido = new JButton("Fazer Pedido");
		btnNewButtonFazerPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idCliente > 0) {
					if(comboBoxEndereco.getSelectedIndex() != 0) {
						ArrayList<Pizza> pizzasPedido = new ArrayList<Pizza>();
						ArrayList<Bebida> bebidasPedido = new ArrayList<Bebida>();
						ArrayList<String> tamanhoBebidas = new ArrayList<String>();
						ArrayList<String> tamanhoPizzas = new ArrayList<String>();
						if(isAbertoSegundaPizza && !isAbertoSegundaBebida) {
							if(comboBoxSaborPizza1.getSelectedIndex() != 0 && comboBoxTamanhoPizza1.getSelectedIndex() != 0) {
								if(comboBoxSaborPizza2.getSelectedIndex() != 0 && comboBoxTamanhoPizza2.getSelectedIndex() != 0) {
									String precoTxt = lblNewLabelPrecoTotal.getText();
									Date dataAtual = null;
									for(int cont = 0; cont<pizzas.size(); cont++) {
										if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza1.getSelectedItem().toString())) {
											pizzasPedido.add(pizzas.get(cont));
										}
									}
									for(int cont = 0; cont<pizzas.size(); cont++) {
										if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza2.getSelectedItem().toString())) {
											pizzasPedido.add(pizzas.get(cont));
										}
									}
									tamanhoPizzas.add(comboBoxTamanhoPizza1.getSelectedItem().toString());
									tamanhoPizzas.add(comboBoxTamanhoPizza2.getSelectedItem().toString());
									if(comboBoxNomeBebida1.getSelectedIndex() != 0 && comboBoxTamanhoBebida1.getSelectedIndex() != 0) {
										tamanhoBebidas.add(comboBoxTamanhoBebida1.getSelectedItem().toString());
										for(int cont = 0; cont<bebidas.size(); cont++) {
											if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida1.getSelectedItem().toString())) {
												bebidasPedido.add(bebidas.get(cont));
											}
										}
									}
									try {
										dataAtual = new Date(format.parse(dataAtualTxt).getTime());
									} 
									catch (ParseException erro) {
										erro.printStackTrace();
									}
									String enderecoCliente = comboBoxEndereco.getSelectedItem().toString();
									boolean sucesso;
									
									try {
										PedidoController pedidoControlador = new PedidoController();
										sucesso = pedidoControlador.cadastrarPedido(precoTxt, dataAtual, enderecoCliente, pizzasPedido, bebidasPedido, idCliente, tamanhoPizzas, tamanhoBebidas);
										if(sucesso) {
											JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
											limparTela();
										}
										else {
											JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
										}
									}
									catch(Exception erro) {
										JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o pedido: " + erro);
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
							}
						}
						else if(!isAbertoSegundaPizza && isAbertoSegundaBebida) {
							if(comboBoxNomeBebida1.getSelectedIndex() != 0 && comboBoxTamanhoBebida1.getSelectedIndex() != 0) {
								if(comboBoxNomeBebida2.getSelectedIndex() != 0 && comboBoxTamanhoBebida2.getSelectedIndex() != 0) {
									String precoTxt = lblNewLabelPrecoTotal.getText();
									Date dataAtual = null;
									for(int cont = 0; cont<bebidas.size(); cont++) {
										if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida1.getSelectedItem().toString())) {
											bebidasPedido.add(bebidas.get(cont));
										}
									}
									for(int cont = 0; cont<bebidas.size(); cont++) {
										if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida2.getSelectedItem().toString())) {
											bebidasPedido.add(bebidas.get(cont));
										}
									}
									tamanhoBebidas.add(comboBoxTamanhoBebida1.getSelectedItem().toString());
									tamanhoBebidas.add(comboBoxTamanhoBebida2.getSelectedItem().toString());
									if(comboBoxSaborPizza1.getSelectedIndex() != 0 && comboBoxTamanhoPizza1.getSelectedIndex() != 0) {
										tamanhoPizzas.add(comboBoxTamanhoPizza1.getSelectedItem().toString());
										for(int cont = 0; cont<pizzas.size(); cont++) {
											if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza1.getSelectedItem().toString())) {
												pizzasPedido.add(pizzas.get(cont));
											}
										}
									}
									try {
										dataAtual = new Date(format.parse(dataAtualTxt).getTime());
									} 
									catch (ParseException erro) {
										erro.printStackTrace();
									}
									String enderecoCliente = comboBoxEndereco.getSelectedItem().toString();
									boolean sucesso;
									
									try {
										PedidoController pedidoControlador = new PedidoController();
										sucesso = pedidoControlador.cadastrarPedido(precoTxt, dataAtual, enderecoCliente, pizzasPedido, bebidasPedido, idCliente, tamanhoPizzas, tamanhoBebidas);
										if(sucesso) {
											JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
											limparTela();
										}
										else {
											JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
										}
									}
									catch(Exception erro) {
										JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o pedido: " + erro);
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
							}
						}
						else if(isAbertoSegundaPizza && isAbertoSegundaBebida) {
							if((comboBoxNomeBebida1.getSelectedIndex() != 0 && comboBoxTamanhoBebida1.getSelectedIndex() != 0) && (comboBoxSaborPizza1.getSelectedIndex() != 0 && comboBoxTamanhoPizza1.getSelectedIndex() != 0)) {
								if((comboBoxNomeBebida2.getSelectedIndex() != 0 && comboBoxTamanhoBebida2.getSelectedIndex() != 0) && (comboBoxSaborPizza2.getSelectedIndex() != 0 && comboBoxTamanhoPizza2.getSelectedIndex() != 0)) {
									String precoTxt = lblNewLabelPrecoTotal.getText();
									Date dataAtual = null;
									
									for(int cont = 0; cont<bebidas.size(); cont++) {
										if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida1.getSelectedItem().toString())) {
											bebidasPedido.add(bebidas.get(cont));
										}
									}
									for(int cont = 0; cont<bebidas.size(); cont++) {
										if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida2.getSelectedItem().toString())) {
											bebidasPedido.add(bebidas.get(cont));
										}
									}
									for(int cont = 0; cont<pizzas.size(); cont++) {
										if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza1.getSelectedItem().toString())) {
											pizzasPedido.add(pizzas.get(cont));
										}
									}
									for(int cont = 0; cont<pizzas.size(); cont++) {
										if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza2.getSelectedItem().toString())) {
											pizzasPedido.add(pizzas.get(cont));
										}
									}
									tamanhoBebidas.add(comboBoxTamanhoBebida1.getSelectedItem().toString());
									tamanhoBebidas.add(comboBoxTamanhoBebida2.getSelectedItem().toString());
									tamanhoPizzas.add(comboBoxTamanhoPizza1.getSelectedItem().toString());
									tamanhoPizzas.add(comboBoxTamanhoPizza2.getSelectedItem().toString());

									try {
										dataAtual = new Date(format.parse(dataAtualTxt).getTime());
									} 
									catch (ParseException erro) {
										erro.printStackTrace();
									}
									String enderecoCliente = comboBoxEndereco.getSelectedItem().toString();
									boolean sucesso;
									
									try {
										PedidoController pedidoControlador = new PedidoController();
										sucesso = pedidoControlador.cadastrarPedido(precoTxt, dataAtual, enderecoCliente, pizzasPedido, bebidasPedido, idCliente, tamanhoPizzas, tamanhoBebidas);
										if(sucesso) {
											JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
											limparTela();
										}
										else {
											JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
										}
									}
									catch(Exception erro) {
										JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o pedido: " + erro);
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
							}
						}
						else {
							String precoTxt = lblNewLabelPrecoTotal.getText();
							Date dataAtual = null;
							if((comboBoxSaborPizza1.getSelectedIndex() != 0 && comboBoxTamanhoPizza1.getSelectedIndex() != 0) && (comboBoxNomeBebida1.getSelectedIndex() == 0 && comboBoxTamanhoBebida1.getSelectedIndex() == 0)) {
								for(int cont = 0; cont<pizzas.size(); cont++) {
									if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza1.getSelectedItem().toString())) {
										pizzasPedido.add(pizzas.get(cont));
									}
								}
								tamanhoPizzas.add(comboBoxTamanhoPizza1.getSelectedItem().toString());
								
								try {
									dataAtual = new Date(format.parse(dataAtualTxt).getTime());
								} 
								catch (ParseException erro) {
									erro.printStackTrace();
								}
								String enderecoCliente = comboBoxEndereco.getSelectedItem().toString();
								boolean sucesso;
								
								try {
									PedidoController pedidoControlador = new PedidoController();
									sucesso = pedidoControlador.cadastrarPedido(precoTxt, dataAtual, enderecoCliente, pizzasPedido, bebidasPedido, idCliente, tamanhoPizzas, tamanhoBebidas);
									if(sucesso) {
										JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
										limparTela();
									}
									else {
										JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
									}
								}
								catch(Exception erro) {
									JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o pedido: " + erro);
								}
								
							}
							else if((comboBoxSaborPizza1.getSelectedIndex() == 0 && comboBoxTamanhoPizza1.getSelectedIndex() == 0) && (comboBoxNomeBebida1.getSelectedIndex() != 0 && comboBoxTamanhoBebida1.getSelectedIndex() != 0)) {
								for(int cont = 0; cont<bebidas.size(); cont++) {
									if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida1.getSelectedItem().toString())) {
										bebidasPedido.add(bebidas.get(cont));
									}
								}
								tamanhoBebidas.add(comboBoxTamanhoBebida1.getSelectedItem().toString());
								
								try {
									dataAtual = new Date(format.parse(dataAtualTxt).getTime());
								} 
								catch (ParseException erro) {
									erro.printStackTrace();
								}
								String enderecoCliente = comboBoxEndereco.getSelectedItem().toString();
								boolean sucesso;
								
								try {
									PedidoController pedidoControlador = new PedidoController();
									sucesso = pedidoControlador.cadastrarPedido(precoTxt, dataAtual, enderecoCliente, pizzasPedido, bebidasPedido, idCliente, tamanhoPizzas, tamanhoBebidas);
									if(sucesso) {
										JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
										limparTela();
									}
									else {
										JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
									}
								}
								catch(Exception erro) {
									JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o pedido: " + erro);
								}
								

							}
							else if((comboBoxSaborPizza1.getSelectedIndex() != 0 && comboBoxTamanhoPizza1.getSelectedIndex() != 0) && (comboBoxNomeBebida1.getSelectedIndex() != 0 && comboBoxTamanhoBebida1.getSelectedIndex() != 0)) {
								for(int cont = 0; cont<pizzas.size(); cont++) {
									if(pizzas.get(cont).getSabor().equals(comboBoxSaborPizza1.getSelectedItem().toString())) {
										pizzasPedido.add(pizzas.get(cont));
									}
								}
								for(int cont = 0; cont<bebidas.size(); cont++) {
									if(bebidas.get(cont).getNome().equals(comboBoxNomeBebida1.getSelectedItem().toString())) {
										bebidasPedido.add(bebidas.get(cont));
									}
								}
								tamanhoPizzas.add(comboBoxTamanhoPizza1.getSelectedItem().toString());
								tamanhoBebidas.add(comboBoxTamanhoBebida1.getSelectedItem().toString());
								
								try {
									dataAtual = new Date(format.parse(dataAtualTxt).getTime());
								} 
								catch (ParseException erro) {
									erro.printStackTrace();
								}
								String enderecoCliente = comboBoxEndereco.getSelectedItem().toString();
								boolean sucesso;
								
								try {
									PedidoController pedidoControlador = new PedidoController();
									sucesso = pedidoControlador.cadastrarPedido(precoTxt, dataAtual, enderecoCliente, pizzasPedido, bebidasPedido, idCliente, tamanhoPizzas, tamanhoBebidas);
									if(sucesso) {
										JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
										limparTela();
									}
									else {
										JOptionPane.showMessageDialog(null, "Os dados não foram preenchidos corretamente!");
									}
								}
								catch(Exception erro) {
									JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o pedido: " + erro);
								}
								
							}
							else {
								JOptionPane.showMessageDialog(null, "Os dados do pedido não foram preenchidos corretamente!");
							}
								
							

						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Você não selecionou um endereço! Se o cliente não tiver um endereço cadastrado, cadastre-o antes de fazer o pedido.");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Você não selecionou um cliente.");
				}
				
			}
		});
		btnNewButtonFazerPedido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonFazerPedido.setBounds(231, 584, 160, 33);
		panelFazerPedido.add(btnNewButtonFazerPedido);
		
		JButton btnNewButtonCancelar = new JButton("Cancelar");
		btnNewButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonCancelar.setBounds(552, 584, 110, 33);
		panelFazerPedido.add(btnNewButtonCancelar);
		
		JButton btnNewButtonLimpar = new JButton("Limpar");
		btnNewButtonLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		btnNewButtonLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButtonLimpar.setBounds(425, 584, 94, 33);
		panelFazerPedido.add(btnNewButtonLimpar);
		
		lblNewLabelSelecioneEnd.setVisible(false);
		lblNewLabelSelecioneEnd.setForeground(Color.DARK_GRAY);
		lblNewLabelSelecioneEnd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelSelecioneEnd.setBounds(254, 71, 377, 29);
		panelFazerPedido.add(lblNewLabelSelecioneEnd);
		
		comboBoxEndereco.setVisible(false);
		comboBoxEndereco.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxEndereco.setModel(new DefaultComboBoxModel(new String[] {"Selecione um endere\u00E7o deste cliente"}));
		comboBoxEndereco.setBounds(641, 75, 313, 26);
		panelFazerPedido.add(comboBoxEndereco);
		
		
		lblNewLabelPizza2Pedido.setVisible(false);
		lblNewLabelPizza2Pedido.setForeground(Color.DARK_GRAY);
		lblNewLabelPizza2Pedido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelPizza2Pedido.setBounds(10, 253, 160, 25);
		panelFazerPedido.add(lblNewLabelPizza2Pedido);
		
		comboBoxSaborPizza2.setVisible(false);
		comboBoxSaborPizza2.setModel(new DefaultComboBoxModel(new String[] {"Selecione um dos sabores cadastrados"}));
		comboBoxSaborPizza2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSaborPizza2.setBounds(180, 255, 388, 25);
		panelFazerPedido.add(comboBoxSaborPizza2);
		
		listarPizza2ComboBox();
		
		comboBoxTamanhoPizza2.setVisible(false);
		comboBoxTamanhoPizza2.setModel(new DefaultComboBoxModel(new String[] {"Selecione um tamanho", "Pequena", "M\u00E9dia", "Grande"}));
		comboBoxTamanhoPizza2.setBounds(589, 255, 169, 25);
		panelFazerPedido.add(comboBoxTamanhoPizza2);
		
		
		lblNewLabelBebida2Pedido.setVisible(false);
		lblNewLabelBebida2Pedido.setForeground(Color.DARK_GRAY);
		lblNewLabelBebida2Pedido.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelBebida2Pedido.setBounds(10, 478, 176, 25);
		panelFazerPedido.add(lblNewLabelBebida2Pedido);
		
		comboBoxNomeBebida2.setVisible(false);
		comboBoxNomeBebida2.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma das bebidas cadastradas"}));
		comboBoxNomeBebida2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxNomeBebida2.setBounds(194, 480, 311, 25);
		panelFazerPedido.add(comboBoxNomeBebida2);
		
		listarBebida2ComboBox();
		
		comboBoxTamanhoBebida2.setVisible(false);
		comboBoxTamanhoBebida2.setModel(new DefaultComboBoxModel(new String[] {"Selecione um tamanho", "Lata pequena/Garrafa de 500ml", "Garrafa de 1L", "Garrafa de 2L"}));
		comboBoxTamanhoBebida2.setBounds(527, 480, 196, 25);
		panelFazerPedido.add(comboBoxTamanhoBebida2);
		btnNewButtonApagarBebida2.setIcon(new ImageIcon(TelaFazerPedido.class.getResource("/imagens/icone_lixeira.png")));
		
		btnNewButtonApagarBebida2.setVisible(false);
		btnNewButtonApagarBebida2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTelaBebida2();
			}
		});
		btnNewButtonApagarBebida2.setBounds(760, 475, 40, 35);
		panelFazerPedido.add(btnNewButtonApagarBebida2);
		btnNewButtonApagarPizza2.setIcon(new ImageIcon(TelaFazerPedido.class.getResource("/imagens/icone_lixeira.png")));
		
		btnNewButtonApagarPizza2.setVisible(false);
		btnNewButtonApagarPizza2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTelaPizza2();
			}
		});
		btnNewButtonApagarPizza2.setBounds(781, 248, 40, 35);
		panelFazerPedido.add(btnNewButtonApagarPizza2);
		
		JLabel lblNewLabelTitulo = new JLabel("Fazer Pedido");
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaFazerPedido.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setBounds(320, 17, 322, 55);
		contentPane.add(lblNewLabelTitulo);
		
	}
	
	public void buscarCliente(int idCliente, String nomeCliente) throws ExceptionDAO {
		comboBoxEndereco.removeAllItems();
		comboBoxEndereco.setModel(new DefaultComboBoxModel(new String[] {"Selecione um endere\u00E7o deste cliente"}));
		
		this.idCliente = idCliente;
		this.textFieldNomeCliente.setText(nomeCliente);
		this.comboBoxEndereco.setVisible(true);
		this.lblNewLabelSelecioneEnd.setVisible(true);
		
		enderecos = enderecoControlador.listarEnderecos(this.idCliente);
		
		for(int contador=0; contador<enderecos.size(); contador++) {
			comboBoxEndereco.addItem(enderecos.get(contador).getRua() + ",  " + enderecos.get(contador).getNumero());
		}
	}
	
	
	private boolean isPequenaPizza1;
	private boolean isMediaPizza1;
	private boolean isGrandePizza1;
	
	private boolean isPequenaPizza2;
	private boolean isMediaPizza2;
	private boolean isGrandePizza2;
	
	private boolean isPequenaBebida1;
	private boolean isMediaBebida1;
	private boolean isGrandeBebida1;
	
	private boolean isPequenaBebida2;
	private boolean isMediaBebida2;
	private boolean isGrandeBebida2;
	private JTextField textFieldNomeCliente;
	
	public void listarPizzaComboBox() throws ExceptionDAO {
		
		for(int contador=0; contador<pizzas.size(); contador++) {
			comboBoxSaborPizza1.addItem(pizzas.get(contador).getSabor());
		}
		
		comboBoxTamanhoPizza1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(comboBoxSaborPizza1.getSelectedIndex() != 0) {
						//Se escolheu a pizza e depois o tamanho:
						if(comboBoxTamanhoPizza1.getSelectedIndex() != 0) {
							String valorSelecionadoPizza = comboBoxSaborPizza1.getSelectedItem().toString();
							for(int cont=0; cont<pizzas.size(); cont++) {
								if(valorSelecionadoPizza.equals(pizzas.get(cont).getSabor())) {
									if(validacaoPizza1 == 0) {
										if(precoTotal == 0) {
											precoTotal = pizzas.get(cont).getPreco();
										}
										else {
											precoTotal+=pizzas.get(cont).getPreco();
										}
										precoAjudaPizza1 = pizzas.get(cont).getPreco();
										idPizzaAjuda1 = pizzas.get(cont).getIdPizza();
										validacaoPizza1 = 99;
									}
									else {
										if(pizzas.get(cont).getIdPizza() != idPizzaAjuda1){
											if((precoTotal - precoAjudaPizza1) == 0) {
												precoTotal = pizzas.get(cont).getPreco();
											}
											else {
												precoTotal = precoTotal + pizzas.get(cont).getPreco() - precoAjudaPizza1;
											}
											idPizzaAjuda1 = pizzas.get(cont).getIdPizza();
											precoAjudaPizza1 = pizzas.get(cont).getPreco();
										}
									}
									break;
								}
								
							}
							
						}
						
						//Se escolheu uma pizza mas não escolheu o tamanho:
						else {
							validacaoPizza1 = 0;
							precoTotal = precoTotal - precoAjudaPizza1;
							
						}
					}
					
					//Se não escolheu a pizza:
					else {
						validacaoPizza1 = 0;
						precoTotal = precoTotal - precoAjudaPizza1;
						precoAjudaPizza1 = 0;
					}
					
					precoTotal = Double.parseDouble(df.format(precoTotal).replace(",", "."));
					
					if(comboBoxSaborPizza1.getSelectedIndex() != 0 && comboBoxTamanhoPizza1.getSelectedIndex() != 0) {
						
						if(comboBoxTamanhoPizza1.getSelectedItem().toString().equals("Pequena")) {
							isPequenaPizza1 = true;
							//Se foi da media pra pequena:
							if(isMediaPizza1) {
								precoTotal = precoTotal - 20;
								precoAjudaPizza1 = precoAjudaPizza1 - 20;
								isMediaPizza1 = false;
							}
							//Se foi da grande pra pequena
							else if(isGrandePizza1) {
								precoTotal = precoTotal - 40;
								precoAjudaPizza1 = precoAjudaPizza1 - 40;
								isGrandePizza1 = false;
							}
							//Se a primeira foi a pequena:
							else {
								precoTotal -= 20;
								precoAjudaPizza1 -= 20;
							}
						}
						else if(comboBoxTamanhoPizza1.getSelectedItem().toString().equals("Grande")) {
							isGrandePizza1 = true;
							//Se foi da media pra grande:
							if(isMediaPizza1) {
								precoTotal = precoTotal + 20;
								precoAjudaPizza1 = precoAjudaPizza1 + 20;
								isMediaPizza1 = false;
							}
							//Se foi da pequena pra grande:
							else if(isPequenaPizza1) {
								precoTotal = precoTotal + 40;
								precoAjudaPizza1 = precoAjudaPizza1 + 40;
								isPequenaPizza1 = false;
							}
							//Se a primeira foi a grande:
							else {
								precoTotal += 20;
								precoAjudaPizza1 += 20;
							}
						}
						else if(comboBoxTamanhoPizza1.getSelectedItem().toString().equals("Média")) {
							isMediaPizza1 = true;
							//Se foi da pequena pra média:
							if(isPequenaPizza1) {
								precoTotal = precoTotal + 20;
								precoAjudaPizza1 = precoAjudaPizza1 + 20;
								isPequenaPizza1 = false;
							}
							//Se foi da grande pra média:
							else if(isGrandePizza1) {
								precoTotal = precoTotal - 20;
								precoAjudaPizza1 = precoAjudaPizza1 - 20;
								isGrandePizza1 = false;
							}
						}
					}
					
					if(precoTotal != 0) {
						
						lblNewLabelPrecoTotal.setText(df.format(precoTotal));
					}
					else {
						lblNewLabelPrecoTotal.setText("0,00");
					}
					
				}
			}
		});
		
		
		
		comboBoxSaborPizza1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if((e.getStateChange() == ItemEvent.SELECTED)) {
					comboBoxTamanhoPizza1.setSelectedIndex(0);
					isPequenaPizza1= false;
					isMediaPizza1= false;
					isGrandePizza1= false;
				}
			}
		});
		
	}
	
	public void listarPizza2ComboBox() throws ExceptionDAO {
		
		for(int contador=0; contador<pizzas.size(); contador++) {
			comboBoxSaborPizza2.addItem(pizzas.get(contador).getSabor());
		}
		
		comboBoxTamanhoPizza2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					if(comboBoxSaborPizza2.getSelectedIndex() != 0) {
						//Se escolheu a pizza e depois o tamanho:
						if(comboBoxTamanhoPizza2.getSelectedIndex() != 0) {
							String valorSelecionadoPizza = comboBoxSaborPizza2.getSelectedItem().toString();
							for(int cont=0; cont<pizzas.size(); cont++) {
								if(valorSelecionadoPizza.equals(pizzas.get(cont).getSabor())) {
									if(validacaoPizza2 == 0) {
										if(precoTotal == 0) {
											precoTotal = pizzas.get(cont).getPreco();
										}
										else {
											precoTotal+=pizzas.get(cont).getPreco();
										}
										precoAjudaPizza2 = pizzas.get(cont).getPreco();
										idPizzaAjuda2 = pizzas.get(cont).getIdPizza();
										validacaoPizza2 = 99;
									}
									else {
										if(pizzas.get(cont).getIdPizza() != idPizzaAjuda2){
											if(precoTotal - precoAjudaPizza2 == 0) {
												precoTotal = pizzas.get(cont).getPreco();
											}
											else {
												precoTotal = precoTotal + pizzas.get(cont).getPreco() - precoAjudaPizza2;
											}
											idPizzaAjuda2 = pizzas.get(cont).getIdPizza();
											precoAjudaPizza2 = pizzas.get(cont).getPreco();
										}
									}
									break;
								}
								
							}
							
						}
						
						//Se escolheu uma pizza mas não escolheu o tamanho:
						else {
							validacaoPizza2 = 0;
							precoTotal = precoTotal - precoAjudaPizza2;
							
						}
					}
					
					//Se não escolheu a pizza:
					else {
						validacaoPizza2 = 0;
						precoTotal = precoTotal - precoAjudaPizza2;
						precoAjudaPizza2 = 0;
					}
					
					precoTotal = Double.parseDouble(df.format(precoTotal).replace(",", "."));
					
					if(comboBoxSaborPizza2.getSelectedIndex() != 0 && comboBoxTamanhoPizza2.getSelectedIndex() != 0) {
						
						if(comboBoxTamanhoPizza2.getSelectedItem().toString().equals("Pequena")) {
							isPequenaPizza2 = true;
							//Se foi da media pra pequena:
							if(isMediaPizza2) {
								precoTotal -= 20;
								precoAjudaPizza2 -= 20;
								isMediaPizza2 = false;
							}
							//Se foi da grande pra pequena
							else if(isGrandePizza2) {
								precoTotal -= 40;
								precoAjudaPizza2 -= 40;
								isGrandePizza2 = false;
							}
							//Se a primeira foi a pequena:
							else {
								precoTotal -= 20;
								precoAjudaPizza2 -= 20;
							}
						}
						else if(comboBoxTamanhoPizza2.getSelectedItem().toString().equals("Grande")) {
							isGrandePizza2 = true;
							//Se foi da media pra grande:
							if(isMediaPizza2) {
								precoTotal += 20;
								precoAjudaPizza2 += 20;
								isMediaPizza2 = false;
							}
							//Se foi da pequena pra grande:
							else if(isPequenaPizza2) {
								precoTotal += 40;
								precoAjudaPizza2 += 40;
								isPequenaPizza2 = false;
							}
							//Se a primeira foi a grande:
							else {
								precoTotal += 20;
								precoAjudaPizza2 += 20;
							}
						}
						else if(comboBoxTamanhoPizza2.getSelectedItem().toString().equals("Média")) {
							isMediaPizza2 = true;
							//Se foi da pequena pra média:
							if(isPequenaPizza2) {
								precoTotal += 20;
								precoAjudaPizza2 += 20;
								isPequenaPizza2 = false;
							}
							//Se foi da grande pra média:
							else if(isGrandePizza2) {
								precoTotal -= 20;
								precoAjudaPizza2 -= 20;
								isGrandePizza2 = false;
							}
						}
					}
					
					if(precoTotal != 0) {
						
						lblNewLabelPrecoTotal.setText(df.format(precoTotal));
					}
					else {
						lblNewLabelPrecoTotal.setText("0,00");
					}
					
				}
			}
		});
		
		
		comboBoxSaborPizza2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if((e.getStateChange() == ItemEvent.SELECTED)) {
					comboBoxTamanhoPizza2.setSelectedIndex(0);
					isPequenaPizza2= false;
					isMediaPizza2= false;
					isGrandePizza2= false;
				}
			}
		});
		
	}
	
	
	public void listarBebidaComboBox() throws ExceptionDAO {
		
		for(int contador=0; contador<bebidas.size(); contador++) {
			comboBoxNomeBebida1.addItem(bebidas.get(contador).getNome());
		}
		
		comboBoxTamanhoBebida1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
				
					if(comboBoxNomeBebida1.getSelectedIndex() != 0) {
						//Se escolheu a bebida e depois o tamanho:
						if(comboBoxTamanhoBebida1.getSelectedIndex() != 0) {
							String valorSelecionadoBebida = comboBoxNomeBebida1.getSelectedItem().toString();
							for(int cont=0; cont<bebidas.size(); cont++) {
								if(valorSelecionadoBebida.equals(bebidas.get(cont).getNome())) {
									if(validacaoBebida1 == 0) {
										if(precoTotal == 0) {
											precoTotal = bebidas.get(cont).getPreco();
										}
										else {
											precoTotal+=bebidas.get(cont).getPreco();
										}
										precoAjudaBebida1 = bebidas.get(cont).getPreco();
										idBebidaAjuda1 = bebidas.get(cont).getIdBebida();
										validacaoBebida1 = 99;
									}
									else {
										if(bebidas.get(cont).getIdBebida() != idBebidaAjuda1){
											if((precoTotal - precoAjudaBebida1) == 0) {
												precoTotal = bebidas.get(cont).getPreco();
											}
											else {
												precoTotal = precoTotal + bebidas.get(cont).getPreco() - precoAjudaBebida1;
											}
											idBebidaAjuda1 = bebidas.get(cont).getIdBebida();
											precoAjudaBebida1 = bebidas.get(cont).getPreco();
										}
									}
									break;
								}
								
							}
							
						}
						
						//Se escolheu uma bebida mas não escolheu o tamanho:
						else {
							validacaoBebida1 = 0;
	
							precoTotal = precoTotal - precoAjudaBebida1;
							
						}
					}
					
					//Se não escolheu a bebida:
					else{
						validacaoBebida1 = 0;
						precoTotal = precoTotal - precoAjudaBebida1;
						precoAjudaBebida1 = 0;

					}
					
					precoTotal = Double.parseDouble(df.format(precoTotal).replace(",", "."));
					
					if(comboBoxNomeBebida1.getSelectedIndex() != 0 && comboBoxTamanhoBebida1.getSelectedIndex() != 0) {
						
						if(comboBoxTamanhoBebida1.getSelectedItem().toString().equals("Lata pequena/Garrafa de 500ml")) {
							isPequenaBebida1 = true;
							//Se foi da media pra pequena:
							if(isMediaBebida1) {
								precoTotal -= 2;
								precoAjudaBebida1 -= 2;
								isMediaBebida1 = false;
							}
							//Se foi da grande pra pequena
							else if(isGrandeBebida1) {
								precoTotal -= 4;
								precoAjudaBebida1 -= 4;
								isGrandeBebida1 = false;
							}
						}
						else if(comboBoxTamanhoBebida1.getSelectedItem().toString().equals("Garrafa de 2L")) {
							isGrandeBebida1 = true;
							//Se foi da media pra grande:
							if(isMediaBebida1) {
								precoTotal += 2;
								precoAjudaBebida1 += 2;
								isMediaBebida1 = false;
							}
							//Se foi da pequena pra grande:
							else if(isPequenaBebida1) {
								precoTotal += 4;
								precoAjudaBebida1 += 4;
								isPequenaBebida1 = false;
							}
							//Se a primeira foi a grande:
							else {
								precoTotal += 4;
								precoAjudaBebida1 += 4;
							}
						}
						else if(comboBoxTamanhoBebida1.getSelectedItem().toString().equals("Garrafa de 1L")) {
							isMediaBebida1 = true;
							//Se foi da pequena pra média:
							if(isPequenaBebida1) {
								precoTotal += 2;
								precoAjudaBebida1 += 2;
								isPequenaBebida1 = false;
							}
							//Se foi da grande pra média:
							else if(isGrandeBebida1) {
								precoTotal -= 2;
								precoAjudaBebida1 -= 2;
								isGrandeBebida1 = false;
							}
							//Se a primeira foi a média:
							else{
								precoTotal += 2;
								precoAjudaBebida1 += 2;
								isGrandeBebida1 = false;
							}
						}
					}
					
					if(precoTotal != 0) {

						lblNewLabelPrecoTotal.setText(df.format(precoTotal));
					}
					else {
						lblNewLabelPrecoTotal.setText("0,00");
					}
					
				}
			}
		});
		
		comboBoxNomeBebida1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if((e.getStateChange() == ItemEvent.SELECTED)) {
					comboBoxTamanhoBebida1.setSelectedIndex(0);
					isPequenaBebida1= false;
					isMediaBebida1= false;
					isGrandeBebida1= false;
				}
			}
		});
	}
	
	public void listarBebida2ComboBox() throws ExceptionDAO {
		
		for(int contador=0; contador<bebidas.size(); contador++) {
			comboBoxNomeBebida2.addItem(bebidas.get(contador).getNome());
		}
		
		comboBoxTamanhoBebida2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
				
					if(comboBoxNomeBebida2.getSelectedIndex() != 0) {
						//Se escolheu a bebida e depois o tamanho:
						if(comboBoxTamanhoBebida2.getSelectedIndex() != 0) {
							String valorSelecionadoBebida = comboBoxNomeBebida2.getSelectedItem().toString();
							for(int cont=0; cont<bebidas.size(); cont++) {
								if(valorSelecionadoBebida.equals(bebidas.get(cont).getNome())) {
									if(validacaoBebida2 == 0) {
										if(precoTotal == 0) {
											precoTotal = bebidas.get(cont).getPreco();
										}
										else {
											precoTotal += bebidas.get(cont).getPreco();
										}
										precoAjudaBebida2 = bebidas.get(cont).getPreco();
										idBebidaAjuda2 = bebidas.get(cont).getIdBebida();
										validacaoBebida2 = 99;
									}
									else {
										if(bebidas.get(cont).getIdBebida() != idBebidaAjuda2){
											if((precoTotal - precoAjudaBebida2) == 0) {
												precoTotal = bebidas.get(cont).getPreco();
											}
											else {
												precoTotal = precoTotal + bebidas.get(cont).getPreco() - precoAjudaBebida2;
											}
											idBebidaAjuda2 = bebidas.get(cont).getIdBebida();
											precoAjudaBebida2 = bebidas.get(cont).getPreco();
										}
									}
									break;
								}
								
							}
							
						}
						
						//Se escolheu uma bebida mas não escolheu o tamanho:
						else {
							validacaoBebida2 = 0;
	
							precoTotal = precoTotal - precoAjudaBebida2;
							
						}
					}
					
					//Se não escolheu a bebida:
					else{
						validacaoBebida2 = 0;
						precoTotal = precoTotal - precoAjudaBebida2;
						precoAjudaBebida2 = 0;

					}
					
					precoTotal = Double.parseDouble(df.format(precoTotal).replace(",", "."));
					
					if(comboBoxNomeBebida2.getSelectedIndex() != 0 && comboBoxTamanhoBebida2.getSelectedIndex() != 0) {
						
						if(comboBoxTamanhoBebida2.getSelectedItem().toString().equals("Lata pequena/Garrafa de 500ml")) {
							isPequenaBebida2 = true;
							//Se foi da media pra pequena:
							if(isMediaBebida2) {
								precoTotal -= 2;
								precoAjudaBebida2 -= 2;
								isMediaBebida2 = false;
							}
							//Se foi da grande pra pequena
							else if(isGrandeBebida2) {
								precoTotal -= 4;
								precoAjudaBebida2 -= 4;
								isGrandeBebida2 = false;
							}
						}
						else if(comboBoxTamanhoBebida2.getSelectedItem().toString().equals("Garrafa de 2L")) {
							isGrandeBebida2 = true;
							//Se foi da media pra grande:
							if(isMediaBebida2) {
								precoTotal += 2;
								precoAjudaBebida2 += 2;
								isMediaBebida2 = false;
							}
							//Se foi da pequena pra grande:
							else if(isPequenaBebida2) {
								precoTotal += 4;
								precoAjudaBebida2 += 4;
								isPequenaBebida2 = false;
							}
							//Se a primeira foi a grande:
							else {
								precoTotal += 4;
								precoAjudaBebida2 += 4;
							}
						}
						else if(comboBoxTamanhoBebida2.getSelectedItem().toString().equals("Garrafa de 1L")) {
							isMediaBebida2 = true;
							//Se foi da pequena pra média:
							if(isPequenaBebida2) {
								precoTotal += 2;
								precoAjudaBebida2 += 2;
								isPequenaBebida2 = false;
							}
							//Se foi da grande pra média:
							else if(isGrandeBebida2) {
								precoTotal -= 2;
								precoAjudaBebida2 -= 2;
								isGrandeBebida2 = false;
							}
							//Se a primeira foi a média:
							else{
								precoTotal += 2;
								precoAjudaBebida2 += 2;
								isGrandeBebida2 = false;
							}
						}
					}
					
					if(precoTotal != 0) {

						lblNewLabelPrecoTotal.setText(df.format(precoTotal));
					}
					else {
						lblNewLabelPrecoTotal.setText("0,00");
					}
					
				}
			}
		});
		
		comboBoxNomeBebida2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if((e.getStateChange() == ItemEvent.SELECTED)) {
					comboBoxTamanhoBebida2.setSelectedIndex(0);
					isPequenaBebida2= false;
					isMediaBebida2= false;
					isGrandeBebida2= false;
				}
			}
		});
	}
	
	public void limparTela() {
		validacaoPizza1 = 0;
		validacaoBebida1 = 0;
		validacaoPizza2 = 0;
		validacaoBebida2 = 0;
		
		precoAjudaPizza1 = 0;
		idPizzaAjuda1 = 0;
		precoAjudaBebida1 = 0;
		idBebidaAjuda1 = 0;
		precoAjudaPizza2 = 0;
		idPizzaAjuda2 = 0;
		precoAjudaBebida2 = 0;
		idBebidaAjuda2 = 0;
		
		lblNewLabelPrecoTotal.setText("0,00");
		precoTotal = Double.parseDouble(lblNewLabelPrecoTotal.getText().replace(",", "."));
		
		comboBoxNomeBebida1.setSelectedIndex(0);
		comboBoxSaborPizza1.setSelectedIndex(0);
		comboBoxTamanhoBebida1.setSelectedIndex(0);
		comboBoxTamanhoPizza1.setSelectedIndex(0);
		comboBoxNomeBebida2.setSelectedIndex(0);
		comboBoxSaborPizza2.setSelectedIndex(0);
		comboBoxTamanhoBebida2.setSelectedIndex(0);
		comboBoxTamanhoPizza2.setSelectedIndex(0);
		
		isPequenaPizza1 = false;
		isMediaPizza1 = false;
		isGrandePizza1 = false;
		isPequenaPizza2 = false;
		isMediaPizza2 = false;
		isGrandePizza2 = false;
		
		isPequenaBebida1 = false;
		isMediaBebida1 = false;
		isGrandeBebida1 = false;
		isPequenaBebida2 = false;
		isMediaBebida2 = false;
		isGrandeBebida2 = false;
		
		this.idCliente = 0;
		this.textFieldNomeCliente.setText("");
		
		comboBoxEndereco.setVisible(false);
		lblNewLabelSelecioneEnd.setVisible(false);
		
		comboBoxTamanhoPizza2.setSelectedIndex(0);
		comboBoxSaborPizza2.setSelectedIndex(0);
		btnNewButtonOutraPizzaPedido.setVisible(true);
		lblNewLabelPizza2Pedido.setVisible(false);
		comboBoxSaborPizza2.setVisible(false);
		comboBoxTamanhoPizza2.setVisible(false);
		btnNewButtonApagarPizza2.setVisible(false);
		isAbertoSegundaPizza = false;
		
		comboBoxTamanhoBebida2.setSelectedIndex(0);
		comboBoxNomeBebida2.setSelectedIndex(0);
		btnNewButtonOutraBebidaPedido.setVisible(true);
		lblNewLabelBebida2Pedido.setVisible(false);
		comboBoxNomeBebida2.setVisible(false);
		comboBoxTamanhoBebida2.setVisible(false);
		btnNewButtonApagarBebida2.setVisible(false);
		isAbertoSegundaBebida = false;
	}
	
	public void limparTelaPizza2() {
		validacaoPizza2 = 0;
		idPizzaAjuda2 = 0;
		
		precoTotal -= precoAjudaPizza2;
		precoAjudaPizza2 = 0;
		if(precoTotal != 0) {
			lblNewLabelPrecoTotal.setText(df.format(precoTotal));
		}
		else {
			lblNewLabelPrecoTotal.setText("0,00");
		}
		
		comboBoxTamanhoPizza2.setSelectedIndex(0); //Ao apertar o botao de apagar
		comboBoxSaborPizza2.setSelectedIndex(0);
		
		
		btnNewButtonOutraPizzaPedido.setVisible(true);
		lblNewLabelPizza2Pedido.setVisible(false);
		comboBoxSaborPizza2.setVisible(false);
		comboBoxTamanhoPizza2.setVisible(false);
		btnNewButtonApagarPizza2.setVisible(false);
		isAbertoSegundaPizza = false;
	}
	
	public void limparTelaBebida2() {
		validacaoBebida2 = 0;
		idBebidaAjuda2 = 0;
		
		precoTotal -= precoAjudaBebida2;
		precoAjudaBebida2 = 0;
		if(precoTotal != 0) {
			lblNewLabelPrecoTotal.setText(df.format(precoTotal));
		}
		else {
			lblNewLabelPrecoTotal.setText("0,00");
		}
		
		comboBoxTamanhoBebida2.setSelectedIndex(0); //Ao apertar o botao de apagar
		comboBoxNomeBebida2.setSelectedIndex(0);
		
		
		btnNewButtonOutraBebidaPedido.setVisible(true);
		lblNewLabelBebida2Pedido.setVisible(false);
		comboBoxNomeBebida2.setVisible(false);
		comboBoxTamanhoBebida2.setVisible(false);
		btnNewButtonApagarBebida2.setVisible(false);
		isAbertoSegundaBebida = false;
	}
	
}
