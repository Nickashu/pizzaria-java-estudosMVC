package pizzaria.model;

import java.util.ArrayList;

import pizzaria.dao.ExceptionDAO;
import pizzaria.dao.PizzaDAO;

public class Pizza {
	
	private int idPizza;
	private String sabor;
	private String ingredientes;
	private double preco;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	
	public int getIdPizza() {
		return idPizza;
	}
	public String getSabor() {
		return sabor;
	}
	public String getIngredientes() {
		return ingredientes;
	}
	public double getPreco() {
		return preco;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	
	
	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	public Pizza() {}
	
	public Pizza(String sabor, String ingredientes, double preco) {
		this.sabor = sabor;
		this.ingredientes = ingredientes;
		this.preco = preco;
	}
	
	
	public void cadastrarPizza(Pizza pizza) throws ExceptionDAO {
		new PizzaDAO().cadastrarPizza(pizza);
	}
	
	public ArrayList<Pizza> consultarPizza(String nome) throws ExceptionDAO{
		return new PizzaDAO().consultarPizza(nome);
	}
	
	public void alterarPizza(Pizza pizza) throws ExceptionDAO{
		new PizzaDAO().alterarPizza(pizza);
	}
	
	public void apagarPizza(Pizza pizza) throws ExceptionDAO{
		new PizzaDAO().apagarPizza(pizza);
	}
	
	public ArrayList<Pizza> listarPizza() throws ExceptionDAO{
		return new PizzaDAO().listarPizza();
	}
	
	public boolean verificaSaborPizza(String saborPizzaVerificar) throws ExceptionDAO{
		return new PizzaDAO().verificaSaborPizza(saborPizzaVerificar);
	}

}
