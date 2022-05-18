package pizzaria.model;

import java.sql.Date;
import java.util.ArrayList;

import pizzaria.dao.ExceptionDAO;
import pizzaria.dao.PedidoDAO;

public class Pedido {
	
	private int idPedido;
	private double precoPedido;
	private Date dataPedido;
	private String enderecoCliente;
	private Cliente cliente;
	private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	private ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
	
	public Pedido() {}
	
	public Pedido(double precoPedido, Date dataPedido, String enderecoCliente, ArrayList<Pizza> pizzas, ArrayList<Bebida> bebidas, int idCliente) {
		this.precoPedido = precoPedido;
		this.dataPedido = dataPedido;
		this.enderecoCliente = enderecoCliente;
		this.pizzas = pizzas;
		this.bebidas = bebidas;
		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente);
		this.cliente = cliente;
	}
	
	
	public int getIdPedido() {
		return idPedido;
	}
	public double getPrecoPedido() {
		return precoPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}
	public ArrayList<Bebida> getBebidas() {
		return bebidas;
	}
	public String getEnderecoCliente() {
		return enderecoCliente;
	}
	

	
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public void setPrecoPedido(double precoPedido) {
		this.precoPedido = precoPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setPizzas(ArrayList<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	public void setBebidas(ArrayList<Bebida> bebidas) {
		this.bebidas = bebidas;
	}
	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}
	
	
	public void cadastrarPedido(Pedido pedido) throws ExceptionDAO{
		new PedidoDAO().cadastrarPedido(pedido);
	}
	
	public void cadastrarPedidoPizza(Pedido pedido, ArrayList<String> tamanhoPizzas) throws ExceptionDAO{
		new PedidoDAO().cadastrarPedidoPizza(pedido, tamanhoPizzas);
	}
	
	public void cadastrarPedidoBebida(Pedido pedido, ArrayList<String> tamanhoBebidas) throws ExceptionDAO{
		new PedidoDAO().cadastrarPedidoBebida(pedido, tamanhoBebidas);
	}
	
	public ArrayList<Pedido> consultarPedidoPizza() throws ExceptionDAO{
		return new PedidoDAO().consultarPedidoPizza();
	}
	
	public ArrayList<Pedido> consultarPedidoBebida() throws ExceptionDAO{
		return new PedidoDAO().consultarPedidoBebida();
	}
	
	public ArrayList<String> retornoTamanhoPizzas() throws ExceptionDAO {
		return new PedidoDAO().retornoTamanhoPizzas();
	}
	
	public ArrayList<String> retornoTamanhoBebidas() throws ExceptionDAO {
		return new PedidoDAO().retornoTamanhoBebidas();
	}
}
