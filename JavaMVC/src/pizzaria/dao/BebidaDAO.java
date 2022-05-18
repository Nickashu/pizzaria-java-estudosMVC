package pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzaria.model.Bebida;

public class BebidaDAO {
	
	public void cadastrarBebida(Bebida bebida) throws ExceptionDAO{
		String sql = "insert into bebida(nome, preco) value (?, ?)";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setString(1, bebida.getNome());
			pStatement.setDouble(2, bebida.getPreco());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao cadastrar a bebida!");
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
	
	public ArrayList<Bebida> consultarBebida(String nomeBebida) throws ExceptionDAO{
		String sql = "select * from bebida where nome like '%" + nomeBebida + "%' order by idBebida";
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					Bebida bebida = new Bebida();
					bebida.setIdBebida(rs.getInt("idBebida"));
					bebida.setNome(rs.getString("nome"));
					bebida.setPreco(rs.getDouble("preco"));
					
					bebidas.add(bebida);
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar pizzas: " + erro);
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
				
		return bebidas;
	}
	
	public void alterarBebida(Bebida bebida) throws ExceptionDAO{
		String sql = "update bebida set nome = ?, preco = ? where idBebida = ?";
		Connection conexao = null;
		PreparedStatement pStatement = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			
			pStatement.setString(1, bebida.getNome());
			pStatement.setDouble(2, bebida.getPreco());
			pStatement.setInt(3, bebida.getIdBebida());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao alterar os dados da bebida: " + erro);
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
	
	public void apagarBebida(Bebida bebida) throws ExceptionDAO{
		String sql = "delete from bebida where idBebida = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setInt(1, bebida.getIdBebida());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao apagar bebida: " + erro);
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
	
	public ArrayList<Bebida> listarBebida() throws ExceptionDAO{
		
		String sql = "select * from bebida order by idBebida";
		
		Connection conexao = new ConnectionMVC().getConnection();
		PreparedStatement pStatement = null;
		
		ArrayList<Bebida> bebidas = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				bebidas = new ArrayList<Bebida>();
				while(rs.next()) {
					Bebida bebida = new Bebida();
					bebida.setIdBebida(rs.getInt("idBebida"));
					bebida.setNome(rs.getString("nome"));
					bebida.setPreco(rs.getDouble("preco"));
					
					bebidas.add(bebida);
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao listar bebidas: " + erro);
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
		
		return bebidas;
	}
	
	public boolean verificaNomeBebida(String nomeVerificar) throws ExceptionDAO{
		String sql = "select nome from bebida";
		
		Connection conexao = new ConnectionMVC().getConnection();
		PreparedStatement pStatement = null;
		
		ArrayList<String> nomesBebidas = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				nomesBebidas = new ArrayList<String>();
				while(rs.next()) {
					nomesBebidas.add(rs.getString("nome"));
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao verificar nome de bebidas: " + erro);
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
		
		boolean existe = false;
		for(int contador = 0; contador < nomesBebidas.size(); contador++) {
			if(nomesBebidas.get(contador).strip().toLowerCase().equals(nomeVerificar.strip().toLowerCase())) {
				existe = true;
				break;
			}
		}
		
		if(existe) {
			return false;
		}
		else {
			return true;
		}
	}

}
