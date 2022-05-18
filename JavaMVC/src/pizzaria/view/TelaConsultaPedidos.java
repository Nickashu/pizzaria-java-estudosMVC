package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pizzaria.controller.EnderecoController;
import pizzaria.controller.PedidoController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Endereco;
import pizzaria.model.Pedido;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

public class TelaConsultaPedidos extends JFrame {

	private JPanel contentPane;
	private JTable tableConsultaPedidoPizza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaPedidos frame = new TelaConsultaPedidos();
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
	private JTable tableConsultaPedidoBebida;
	
	public TelaConsultaPedidos(TelaPrincipal telaPrincipal) throws ExceptionDAO {
		this();
		this.telaPrincipal = telaPrincipal;
	}
	
	public TelaConsultaPedidos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConsultaPedidos.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		setTitle("Pizzaria Muito Massa");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 984, 878);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelConsultaPedido = new JPanel();
		panelConsultaPedido.setBackground(new Color(255, 255, 204));
		panelConsultaPedido.setBounds(10, 80, 948, 725);
		contentPane.add(panelConsultaPedido);
		panelConsultaPedido.setLayout(null);
		
		JScrollPane scrollPaneConsultaPedidoPizza = new JScrollPane();
		scrollPaneConsultaPedidoPizza.setBounds(10, 52, 928, 300);
		panelConsultaPedido.add(scrollPaneConsultaPedidoPizza);
		
		tableConsultaPedidoPizza = new JTable();
		tableConsultaPedidoPizza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableConsultaPedidoPizza.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id do Pedido", "Sabor da Pizza", "Tamanho da Pizza", "Pre\u00E7o Total do Pedido", "Data do Pedido", "Nome do Cliente", "Endere\u00E7o do Cliente"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultaPedidoPizza.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableConsultaPedidoPizza.getColumnModel().getColumn(6).setPreferredWidth(120);
		scrollPaneConsultaPedidoPizza.setViewportView(tableConsultaPedidoPizza);
		
		JScrollPane scrollPaneConsultaPedidoBebida = new JScrollPane();
		scrollPaneConsultaPedidoBebida.setBounds(10, 414, 928, 300);
		panelConsultaPedido.add(scrollPaneConsultaPedidoBebida);
		
		tableConsultaPedidoBebida = new JTable();
		tableConsultaPedidoBebida.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableConsultaPedidoBebida.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id do Pedido", "Nome da Bebida", "Tamanho da Bebida", "Pre\u00E7o Total do Pedido", "Data do Pedido", "Nome do Cliente", "Endere\u00E7o do Cliente"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultaPedidoBebida.getColumnModel().getColumn(0).setPreferredWidth(15);
		tableConsultaPedidoBebida.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableConsultaPedidoBebida.getColumnModel().getColumn(3).setPreferredWidth(55);
		tableConsultaPedidoBebida.getColumnModel().getColumn(6).setPreferredWidth(120);
		scrollPaneConsultaPedidoBebida.setViewportView(tableConsultaPedidoBebida);
		
		JLabel lblNewLabelTituloPedidoPizza = new JLabel("Pedidos Realizados (Informa\u00E7\u00F5es sobre Pizzas)");
		lblNewLabelTituloPedidoPizza.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelTituloPedidoPizza.setBounds(262, 11, 477, 30);
		panelConsultaPedido.add(lblNewLabelTituloPedidoPizza);
		
		JLabel lblNewLabelTituloPedidoBebida = new JLabel("Pedidos Realizados (Informa\u00E7\u00F5es sobre Bebidas)");
		lblNewLabelTituloPedidoBebida.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelTituloPedidoBebida.setBounds(247, 373, 494, 30);
		panelConsultaPedido.add(lblNewLabelTituloPedidoBebida);
		
		DefaultTableModel tableModel1 = (DefaultTableModel) tableConsultaPedidoPizza.getModel();
		DefaultTableModel tableModel2 = (DefaultTableModel) tableConsultaPedidoBebida.getModel();
		
		tableModel1.setRowCount(0);
		tableModel2.setRowCount(0);
		
		
		PedidoController pedidoControlador = new PedidoController();
		
		try {
			ArrayList<Pedido> pedidosPizza = pedidoControlador.consultarPedidoPizza();
			ArrayList<Pedido> pedidosBebida = pedidoControlador.consultarPedidoBebida();
			ArrayList<String> tamanhosPizza = pedidoControlador.retornoTamanhoPizzas();
			ArrayList<String> tamanhosBebida = pedidoControlador.retornoTamanhoBebidas();
			
			
			for(int contador=0; contador<pedidosPizza.size(); contador++) {
				tableModel1.addRow(new Object[] {
						pedidosPizza.get(contador).getIdPedido(),
						pedidosPizza.get(contador).getPizzas().get(0).getSabor(),
						tamanhosPizza.get(contador),
						"R$" + Double.toString(pedidosPizza.get(contador).getPrecoPedido()),
						pedidosPizza.get(contador).getDataPedido(),
						pedidosPizza.get(contador).getCliente().getNome(),
						pedidosPizza.get(contador).getEnderecoCliente(),
				});
			}
			
			tableConsultaPedidoPizza.setModel(tableModel1);
			
			for(int contador=0; contador<pedidosBebida.size(); contador++) {
				tableModel2.addRow(new Object[] {
						pedidosBebida.get(contador).getIdPedido(),
						pedidosBebida.get(contador).getBebidas().get(0).getNome(),
						tamanhosBebida.get(contador),
						"R$" + Double.toString(pedidosBebida.get(contador).getPrecoPedido()),
						pedidosBebida.get(contador).getDataPedido(),
						pedidosBebida.get(contador).getCliente().getNome(),
						pedidosBebida.get(contador).getEnderecoCliente(),
				});
			}
			
			
			tableConsultaPedidoBebida.setModel(tableModel2);
		} 
		catch (ExceptionDAO erro) {
			erro.printStackTrace();
		}
		
		JLabel lblNewLabelTituloConsultaPedido = new JLabel("Consulta de Pedidos");
		lblNewLabelTituloConsultaPedido.setIcon(new ImageIcon(TelaConsultaPedidos.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTituloConsultaPedido.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTituloConsultaPedido.setBounds(325, 22, 369, 47);
		contentPane.add(lblNewLabelTituloConsultaPedido);
		
		JLabel lblNewLabelAvisoConsultaPedido = new JLabel("IDs de pedidos iguais representam o mesmo pedido");
		lblNewLabelAvisoConsultaPedido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabelAvisoConsultaPedido.setBounds(320, 812, 381, 23);
		contentPane.add(lblNewLabelAvisoConsultaPedido);
	}
}
