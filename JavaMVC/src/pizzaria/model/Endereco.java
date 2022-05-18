package pizzaria.model;

import java.util.ArrayList;

import pizzaria.dao.EnderecoDAO;
import pizzaria.dao.ExceptionDAO;

public class Endereco {
	
	private int idEndereco;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private String complemento;
	private Cliente cliente;
	
	public Endereco() {}
	
	public Endereco(int idCliente, String cidade, String bairro, String rua, int numero, String complemento) {
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		Cliente cliente = new Cliente();
		cliente.setIdCliente(idCliente);
		this.cliente = cliente;
	}

	
	public int getIdEndereco() {
		return idEndereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public String getRua() {
		return rua;
	}

	public int getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	
	
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public void cadastrarEndereco(Endereco endereco) throws ExceptionDAO {
		new EnderecoDAO().cadastrarEndereco(endereco);
	}
	
	public ArrayList<Endereco> consultarEndereco(String nomeCliente) throws ExceptionDAO{
		return new EnderecoDAO().consultarEndereco(nomeCliente);
	}
	
	public void alterarEndereco(Endereco endereco) throws ExceptionDAO {
		new EnderecoDAO().alterarEndereco(endereco);
	}
	
	public void apagarEndereco(Endereco endereco) throws ExceptionDAO {
		new EnderecoDAO().apagarEndereco(endereco);
	}
	
	public ArrayList<Endereco> listarEnderecos(int idCliente) throws ExceptionDAO{
		return new EnderecoDAO().listarEnderecos(idCliente);
	}

}
