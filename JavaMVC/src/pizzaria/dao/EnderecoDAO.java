package pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzaria.model.Cliente;
import pizzaria.model.Endereco;

public class EnderecoDAO {
	
	public void cadastrarEndereco(Endereco endereco) throws ExceptionDAO{
		
		String sql = "insert into endereco (cidade, bairro, rua, numero, complemento, idCliente) value (?, ?, ?, ?, ?, ?)";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setString(1, endereco.getCidade());
			pStatement.setString(2, endereco.getBairro());
			pStatement.setString(3, endereco.getRua());
			pStatement.setInt(4, endereco.getNumero());
			pStatement.setString(5, endereco.getComplemento());
			pStatement.setInt(6, endereco.getCliente().getIdCliente());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao cadastrar endereço: " + erro);
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
	
	public ArrayList<Endereco> consultarEndereco(String nomeCliente) throws ExceptionDAO{
		String sql = "select ende.idEndereco, cli.nome, ende.cidade, ende.bairro, ende.rua, ende.numero, ende.complemento, cli.idCliente from endereco ende, cliente cli where ende.idCliente = cli.idCliente and cli.nome like '%" + nomeCliente + "%' order by ende.idEndereco";
		
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Endereco> enderecos = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			if(rs != null) {
				enderecos = new ArrayList<Endereco>();
				while(rs.next()) {
					Endereco endereco = new Endereco();
					endereco.setIdEndereco(rs.getInt("idEndereco"));
					endereco.setCidade(rs.getString("cidade"));
					endereco.setBairro(rs.getString("bairro"));
					endereco.setRua(rs.getString("rua"));
					endereco.setNumero(rs.getInt("numero"));
					endereco.setComplemento(rs.getString("complemento"));
					Cliente cliente = new Cliente();
					cliente.setIdCliente(rs.getInt("idCliente"));
					cliente.setNome(rs.getString("nome"));
					
					endereco.setCliente(cliente);
					
					enderecos.add(endereco);
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar endereços: " + erro);
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
		
		return enderecos;
	}
	
	public void alterarEndereco(Endereco endereco) throws ExceptionDAO{
		
		String sql = "update endereco set cidade = ?, bairro = ?, rua = ?, numero = ?, complemento = ?, idCliente = ? where idEndereco = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setString(1, endereco.getCidade());
			pStatement.setString(2, endereco.getBairro());
			pStatement.setString(3, endereco.getRua());
			pStatement.setInt(4, endereco.getNumero());
			pStatement.setString(5, endereco.getComplemento());
			pStatement.setInt(6, endereco.getCliente().getIdCliente());
			pStatement.setInt(7, endereco.getIdEndereco());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao alterar endereço: " + erro);
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
	
	public void apagarEndereco(Endereco endereco) throws ExceptionDAO{
		String sql = "delete from endereco where idEndereco = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setInt(1, endereco.getIdEndereco());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao apagar endereço: " + erro);
		}
		finally {
			try {
				if(pStatement != null) {
					pStatement.close();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao fechar o statement: " + erro);
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
	
	public ArrayList<Endereco> listarEnderecos(int idCliente) throws ExceptionDAO{
		String sql = "select rua, numero from endereco where idCliente = " + idCliente;
		
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Endereco> enderecos = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			if(rs != null) {
				enderecos = new ArrayList<Endereco>();
				while(rs.next()) {
					Endereco endereco = new Endereco();
					endereco.setRua(rs.getString("rua"));
					endereco.setNumero(rs.getInt("numero"));
					
					enderecos.add(endereco);
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao listar endereços: " + erro);
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
		
		return enderecos;
	}

}
