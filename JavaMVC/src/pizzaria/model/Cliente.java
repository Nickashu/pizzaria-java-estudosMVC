package pizzaria.model;

import java.sql.Date;
import java.util.ArrayList;

import pizzaria.dao.ClienteDAO;
import pizzaria.dao.ExceptionDAO;

public class Cliente {
	
	private int idCliente;
	private String nome;
	private String telefone;
	private String email;
	private String cpf;
	private Date dataNascimento;
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	private ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
	
	public Cliente() {}
	
	public Cliente(String nome, String telefone, String email, String cpf, Date dataNascimento) {
		
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		
	}

	
	
	public int getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public ArrayList<Endereco> getEnderecos() {
		return enderecos;
	}

	
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void setEnderecos(ArrayList<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	
	public void cadastrarCliente(Cliente cliente) throws ExceptionDAO {
		new ClienteDAO().cadastrarCliente(cliente);
	}
	
	
	public ArrayList<Cliente> consultarClientes(String nome) throws ExceptionDAO{
		return new ClienteDAO().consultarClientes(nome);
	}
	
	public boolean verificarClientes() throws ExceptionDAO{
		return new ClienteDAO().verificarClientes();
	}
	
	public void alterarCliente(Cliente cliente) throws ExceptionDAO{
		new ClienteDAO().alterarCliente(cliente);
	}
	
	public void apagarCliente(Cliente cliente) throws ExceptionDAO{
		new ClienteDAO().apagarCliente(cliente);
	}
}
