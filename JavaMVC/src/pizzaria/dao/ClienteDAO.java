package pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzaria.model.Cliente;

public class ClienteDAO {
	
	public void cadastrarCliente(Cliente cliente) throws ExceptionDAO{
		
		String sql = "insert into cliente (nome, telefone, email, cpf, dataNascimento) value (?, ?, ?, ?, ?)";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setString(1, cliente.getNome());
			pStatement.setString(2, cliente.getTelefone());
			pStatement.setString(3, cliente.getEmail());
			pStatement.setString(4, cliente.getCpf());
			pStatement.setDate(5, cliente.getDataNascimento());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao cadastrar o cliente: " + erro);
		}
		finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			}
			catch(SQLException erro){
				throw new ExceptionDAO("Erro ao fechar o Statement: " + erro);
			}
			
			try {
				if(conexao != null) {
					conexao.close();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao fechar a conexão com o banco de dados: " + erro);
			}
		}
		
	}
	
	public ArrayList<Cliente> consultarClientes(String nome) throws ExceptionDAO{
		String sql = "select * from cliente where nome like '%" + nome + "%' order by idCliente";
		
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Cliente> clientes = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			if(rs != null) {
				clientes = new ArrayList<Cliente>();
				while(rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setIdCliente(rs.getInt("idCliente"));
					cliente.setNome(rs.getString("nome"));
					cliente.setTelefone(rs.getString("telefone"));
					cliente.setEmail(rs.getString("email"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setDataNascimento(rs.getDate("dataNascimento"));
					
					clientes.add(cliente);
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar clientes: " + erro);
		}
		//Fechando o statement e a conexão:
		finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			}
			catch(SQLException erro){
				throw new ExceptionDAO("Erro ao fechar o Statement: " + erro);
			}
			
			try {
				if(conexao != null) {
					conexao.close();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao fechar a conexão com o banco de dados: " + erro);
			}
		}
		
		return clientes;
	}
	
	
	public boolean verificarClientes() throws ExceptionDAO{
		
		String sql = "select nome from cliente";
		
		Connection conexao = new ConnectionMVC().getConnection();
		PreparedStatement pStatement = null;
		
		ArrayList<String> nomeClientes = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				nomeClientes = new ArrayList<String>();
				while(rs.next()) {
					nomeClientes.add(rs.getString("nome"));

				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao verificar clientes: " + erro);
		}
		//Fechando o statement e a conexão:
		finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			}
			catch(SQLException erro){
				throw new ExceptionDAO("Erro ao fechar o Statement: " + erro);
			}
			
			try {
				if(conexao != null) {
					conexao.close();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao fechar a conexão com o banco de dados: " + erro);
			}
		}
		
		if(nomeClientes.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void alterarCliente(Cliente cliente) throws ExceptionDAO{
		String sql = "update cliente set nome = ?, telefone = ?, email = ?, cpf = ?, dataNascimento = ? where idCliente = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			
			pStatement.setString(1, cliente.getNome());
			pStatement.setString(2, cliente.getTelefone());
			pStatement.setString(3, cliente.getEmail());
			pStatement.setString(4, cliente.getCpf());
			pStatement.setDate(5, cliente.getDataNascimento());
			pStatement.setInt(6, cliente.getIdCliente());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao alterar dados do cliente: " + erro);
		}
		finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			}
			catch(SQLException erro){
				throw new ExceptionDAO("Erro ao fechar o Statement: " + erro);
			}
			
			try {
				if(conexao != null) {
					conexao.close();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao fechar a conexão com o banco de dados: " + erro);
			}
		}
	}
	
public void apagarCliente(Cliente cliente) throws ExceptionDAO{
		
		String sql = "delete from cliente where idCliente = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setInt(1, cliente.getIdCliente());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao apagar cliente: " + erro);
		}
		finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			}
			catch(SQLException erro){
				throw new ExceptionDAO("Erro ao fechar o Statement: " + erro);
			}
			
			try {
				if(conexao != null) {
					conexao.close();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao fechar a conexão com o banco de dados: " + erro);
			}
		}
		
	}
	
}
