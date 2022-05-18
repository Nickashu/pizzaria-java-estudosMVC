package pizzaria.model;

import java.util.ArrayList;

import pizzaria.dao.BebidaDAO;
import pizzaria.dao.ExceptionDAO;

public class Bebida {
	
	private int idBebida;
	private String nome;
	private double preco;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	
	public int getIdBebida() {
		return idBebida;
	}
	public String getNome() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void setIdBebida(int idBebida) {
		this.idBebida = idBebida;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	public Bebida() {}
	
	public Bebida(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public void cadastrarBebida(Bebida bebida) throws ExceptionDAO {
		new BebidaDAO().cadastrarBebida(bebida);
	}
	
	public ArrayList<Bebida> consultarBebida(String nomeBebida) throws ExceptionDAO{
		return new BebidaDAO().consultarBebida(nomeBebida);
	}
	
	public void alterarBebida(Bebida bebida) throws ExceptionDAO{
		new BebidaDAO().alterarBebida(bebida);
	}
	
	public void apagarBebida(Bebida bebida) throws ExceptionDAO{
		new BebidaDAO().apagarBebida(bebida);
	}
	
	public ArrayList<Bebida> listarBebida() throws ExceptionDAO{
		return new BebidaDAO().listarBebida();
	}
	
	public boolean verificarNomePizza(String nomeBebidaVerifica) throws ExceptionDAO{
		return new BebidaDAO().verificaNomeBebida(nomeBebidaVerifica);
	}
	
}
