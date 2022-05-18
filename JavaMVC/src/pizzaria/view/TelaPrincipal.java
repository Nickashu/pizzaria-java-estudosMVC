package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import musica.TocarSom;
import pizzaria.controller.ClienteController;
import pizzaria.dao.ExceptionDAO;

import java.awt.TextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Panel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private TocarSom musica = new TocarSom();
	private URL url = musica.getUrl();
	
	private ClienteController clienteControlador = new ClienteController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		//Classe para tocar a música:
		musica.tocar(url);
		setResizable(false);
		setTitle("Pizzaria Muito Massa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 689);
		
		JMenuBar menuBarTelaPrincipal = new JMenuBar();
		setJMenuBar(menuBarTelaPrincipal);
		
		JMenu mnNewMenuPedido = new JMenu("Pedido");
		mnNewMenuPedido.setForeground(Color.BLACK);
		mnNewMenuPedido.setBackground(Color.WHITE);
		mnNewMenuPedido.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mnNewMenuPedido.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/pizza_iconeMenorr.png")));
		menuBarTelaPrincipal.add(mnNewMenuPedido);
		
		JMenuItem mntmNewMenuItemFazerPedido = new JMenuItem("Fazer Pedido");
		mntmNewMenuItemFazerPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(clienteControlador.verificarClientes()) {
						TelaFazerPedido telaFazerPedido;
						try {
							telaFazerPedido = new TelaFazerPedido(TelaPrincipal.this);
							telaFazerPedido.setVisible(true);
							dispose();
						} 
						catch (ExceptionDAO erro) {
							erro.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Ainda não há clientes cadastrados!");
					}
				} 
				catch (ExceptionDAO erro) {
					erro.printStackTrace();
				}
				
			}
		});
		mntmNewMenuItemFazerPedido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItemFazerPedido.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/seta.png")));
		mnNewMenuPedido.add(mntmNewMenuItemFazerPedido);
		
		JMenuItem mntmNewMenuItemConsultarPedido = new JMenuItem("Consultar");
		mntmNewMenuItemConsultarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultaPedidos telaConsultaPedido;
				try {
					telaConsultaPedido = new TelaConsultaPedidos(TelaPrincipal.this);
					telaConsultaPedido.setVisible(true);
					dispose();
				} 
				catch (ExceptionDAO erro) {
					erro.printStackTrace();
				}
			}
		});
		mntmNewMenuItemConsultarPedido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItemConsultarPedido.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/seta.png")));
		mnNewMenuPedido.add(mntmNewMenuItemConsultarPedido);
		
		JMenu mnNewMenuCadastro = new JMenu("Cadastrar");
		mnNewMenuCadastro.setForeground(Color.BLACK);
		mnNewMenuCadastro.setBackground(new Color(240, 240, 240));
		mnNewMenuCadastro.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/icone_cadastroMenorr.png")));
		mnNewMenuCadastro.setFont(new Font("Segoe UI", Font.BOLD, 19));
		menuBarTelaPrincipal.add(mnNewMenuCadastro);
		
		JMenuItem mntmNewMenuItemCadastroPizza = new JMenuItem("Cadastrar Pizza");
		mntmNewMenuItemCadastroPizza.setForeground(Color.BLACK);
		mntmNewMenuItemCadastroPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroPizza telaCadPizza = new TelaCadastroPizza(TelaPrincipal.this);
				telaCadPizza.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItemCadastroPizza.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItemCadastroPizza.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/seta.png")));
		mnNewMenuCadastro.add(mntmNewMenuItemCadastroPizza);
		
		JMenuItem mntmNewMenuItemCadastroCliente = new JMenuItem("Cadastrar Cliente");
		mntmNewMenuItemCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroCliente telaCadCliente = null;
				try {
					telaCadCliente = new TelaCadastroCliente(TelaPrincipal.this);
					telaCadCliente.setVisible(true);
					dispose();
				} 
				catch (ParseException erro) {
					erro.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmNewMenuItemCadastroBebida = new JMenuItem("Cadastrar Bebida");
		mntmNewMenuItemCadastroBebida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroBebida telaCadBebida = null;
				telaCadBebida = new TelaCadastroBebida(TelaPrincipal.this);
				telaCadBebida.setVisible(true);
				dispose();
			}
		});
		mntmNewMenuItemCadastroBebida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItemCadastroBebida.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/seta.png")));
		mnNewMenuCadastro.add(mntmNewMenuItemCadastroBebida);
		mntmNewMenuItemCadastroCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItemCadastroCliente.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/seta.png")));
		mnNewMenuCadastro.add(mntmNewMenuItemCadastroCliente);
		
		JMenuItem mntmNewMenuItemCadastroEndereco = new JMenuItem("Cadastrar Endere\u00E7o");
		mntmNewMenuItemCadastroEndereco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(clienteControlador.verificarClientes()) {
						TelaCadastroEndereco telaCadEndereco = new TelaCadastroEndereco(TelaPrincipal.this);
						telaCadEndereco.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Ainda não há clientes cadastrados!");
					}
				} 
				catch (ExceptionDAO erro) {
					erro.printStackTrace();
				}
				
			}
		});
		mntmNewMenuItemCadastroEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItemCadastroEndereco.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/seta.png")));
		mnNewMenuCadastro.add(mntmNewMenuItemCadastroEndereco);
		
		JMenuItem mntmNewMenuItemSair = new JMenuItem("Sair");
		mntmNewMenuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		Component horizontalStrut = Box.createHorizontalStrut(350);
		menuBarTelaPrincipal.add(horizontalStrut);
		mntmNewMenuItemSair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/icone_sairMenorr.png")));
		mntmNewMenuItemSair.setForeground(Color.BLACK);
		mntmNewMenuItemSair.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBarTelaPrincipal.add(mntmNewMenuItemSair);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTelaPrincipal = new JPanel();
		panelTelaPrincipal.setBackground(new Color(255, 153, 102));
		panelTelaPrincipal.setBounds(88, 30, 608, 526);
		contentPane.add(panelTelaPrincipal);
		panelTelaPrincipal.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/Pizzaria_Muito_Massaa.png")));
		lblNewLabel.setBounds(0, 0, 608, 526);
		panelTelaPrincipal.add(lblNewLabel);
	}
}
