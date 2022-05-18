package pizzaria.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pizzaria.controller.PizzaController;
import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Pizza;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TelaConsultaPizza extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldConsultaPizza;
	private JTable tableConsultaPizza;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaPizza frame = new TelaConsultaPizza();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private JFrame telaCadastro;
	
	public TelaConsultaPizza(JFrame telaCadastroPizza) {
		this();
		this.telaCadastro = telaCadastroPizza;
	}
	
	public TelaConsultaPizza() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConsultaPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
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
		setBounds(100, 100, 799, 602);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelConsultaPizza = new JPanel();
		panelConsultaPizza.setBackground(new Color(255, 255, 204));
		panelConsultaPizza.setBounds(10, 90, 763, 461);
		contentPane.add(panelConsultaPizza);
		panelConsultaPizza.setLayout(null);
		
		JLabel lblNewLabelSaborPizza = new JLabel("Digite o sabor da pizza procurada:");
		lblNewLabelSaborPizza.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabelSaborPizza.setForeground(Color.DARK_GRAY);
		lblNewLabelSaborPizza.setBounds(10, 11, 357, 35);
		panelConsultaPizza.add(lblNewLabelSaborPizza);
		
		textFieldConsultaPizza = new JTextField();
		textFieldConsultaPizza.setToolTipText("Informe o sabor da pizza que voc\u00EA deseja procurar");
		textFieldConsultaPizza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldConsultaPizza.setBounds(371, 19, 322, 23);
		panelConsultaPizza.add(textFieldConsultaPizza);
		textFieldConsultaPizza.setColumns(10);
		
		JButton btnNewButtonConsultarPizza = new JButton("");
		btnNewButtonConsultarPizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String saborPizza = textFieldConsultaPizza.getText();
				DefaultTableModel tableModel = (DefaultTableModel) tableConsultaPizza.getModel();
				tableModel.setRowCount(0);
				PizzaController pizzaControlador = new PizzaController();
				
				try {
					ArrayList<Pizza> pizzas = pizzaControlador.consultarPizza(saborPizza);
					pizzas.forEach((Pizza pizza) -> {
						tableModel.addRow(new Object[] {
							pizza.getIdPizza(),
							pizza.getSabor(),
							pizza.getIngredientes(),
							pizza.getPreco()
						});
					});
					
					tableConsultaPizza.setModel(tableModel);
				}
				catch(ExceptionDAO erro) {
					Logger.getLogger(TelaCadastroPizza.class.getName()).log(Level.SEVERE, null, erro);
				}
			}
		});
		btnNewButtonConsultarPizza.setIcon(new ImageIcon(TelaConsultaPizza.class.getResource("/imagens/lupa.png")));
		btnNewButtonConsultarPizza.setBounds(703, 9, 50, 44);
		panelConsultaPizza.add(btnNewButtonConsultarPizza);
		
		JScrollPane scrollPaneConsultaPizza = new JScrollPane();
		scrollPaneConsultaPizza.setBounds(10, 64, 743, 371);
		panelConsultaPizza.add(scrollPaneConsultaPizza);
		
		tableConsultaPizza = new JTable();
		tableConsultaPizza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int idPizza = (Integer) tableConsultaPizza.getModel().getValueAt(tableConsultaPizza.getSelectedRow(), 0);
					String sabor = (String) tableConsultaPizza.getModel().getValueAt(tableConsultaPizza.getSelectedRow(), 1);
					String ingredientes = (String) tableConsultaPizza.getModel().getValueAt(tableConsultaPizza.getSelectedRow(), 2);
					double preco = (Double) tableConsultaPizza.getModel().getValueAt(tableConsultaPizza.getSelectedRow(), 3);
					
					TelaCadastroPizza telaCadastroPizza = (TelaCadastroPizza) telaCadastro;
					telaCadastroPizza.buscarPizza(idPizza, sabor, ingredientes, preco);
					telaCadastroPizza.setVisible(true);
					dispose();
					
				}
			}
		});
		tableConsultaPizza.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id_Pizza", "Sabor", "Ingredientes", "Pre\u00E7o em R$ (tamanho m\u00E9dio)"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableConsultaPizza.getColumnModel().getColumn(0).setPreferredWidth(34);
		tableConsultaPizza.getColumnModel().getColumn(1).setPreferredWidth(148);
		tableConsultaPizza.getColumnModel().getColumn(2).setPreferredWidth(301);
		tableConsultaPizza.getColumnModel().getColumn(3).setPreferredWidth(155);
		scrollPaneConsultaPizza.setViewportView(tableConsultaPizza);
		
		JLabel lblNewLabel = new JLabel("(Para modificar os dados ou apagar uma pizza, clique na linha correspondente)");
		lblNewLabel.setBounds(159, 440, 426, 14);
		panelConsultaPizza.add(lblNewLabel);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewLabelTitulo = new JLabel("Consulta de Pizzas");
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabelTitulo.setIcon(new ImageIcon(TelaConsultaPizza.class.getResource("/imagens/icone2_pizzaMenorr.png")));
		lblNewLabelTitulo.setBounds(210, 24, 353, 55);
		contentPane.add(lblNewLabelTitulo);
		
		JLabel lblNewLabel_1 = new JLabel("Para voltar \u00E0 tela de cadastro sem selecionar uma pizza, basta fechar esta janela");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(324, -1, 459, 14);
		contentPane.add(lblNewLabel_1);
	}
}
