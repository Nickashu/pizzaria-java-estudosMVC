package pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzaria.model.Pizza;

public class PizzaDAO {
	
	public void cadastrarPizza(Pizza pizza) throws ExceptionDAO{
		
		String sql = "insert into pizza (sabor, ingredientes, preco) value (?, ?, ?)";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setString(1, pizza.getSabor());
			pStatement.setString(2, pizza.getIngredientes());
			pStatement.setDouble(3, pizza.getPreco());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao cadastrar a pizza!");
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
	
	public ArrayList<Pizza> consultarPizza(String saborPizza) throws ExceptionDAO{
		
		String sql = "select * from pizza where sabor like '%" + saborPizza + "%' order by idPizza";
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					Pizza pizza = new Pizza();
					pizza.setIdPizza(rs.getInt("idPizza"));
					pizza.setSabor(rs.getString("sabor"));
					pizza.setIngredientes(rs.getString("ingredientes"));
					pizza.setPreco(rs.getDouble("preco"));
					
					pizzas.add(pizza);
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
				
		return pizzas;
	}
	
	public void alterarPizza(Pizza pizza) throws ExceptionDAO {
		String sql = "update pizza set sabor = ?, ingredientes = ?, preco = ? where idPizza = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			
			pStatement.setString(1, pizza.getSabor());
			pStatement.setString(2, pizza.getIngredientes());
			pStatement.setDouble(3, pizza.getPreco());
			pStatement.setInt(4, pizza.getIdPizza());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao alterar dados da pizza: " + erro);
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
	
	public void apagarPizza(Pizza pizza) throws ExceptionDAO{
		
		String sql = "delete from pizza where idPizza = ?";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setInt(1, pizza.getIdPizza());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao apagar pizza!");
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
	
	public ArrayList<Pizza> listarPizza() throws ExceptionDAO{
		
		String sql = "select * from pizza order by idPizza";
		
		Connection conexao = new ConnectionMVC().getConnection();
		PreparedStatement pStatement = null;
		
		ArrayList<Pizza> pizzas = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				pizzas = new ArrayList<Pizza>();
				while(rs.next()) {
					Pizza pizza = new Pizza();
					pizza.setIdPizza(rs.getInt("idPizza"));
					pizza.setSabor(rs.getString("sabor"));
					pizza.setIngredientes(rs.getString("ingredientes"));
					pizza.setPreco(rs.getDouble("preco"));
					
					pizzas.add(pizza);
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao listar pizzas: " + erro);
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
		
		return pizzas;
	}
	
	public boolean verificaSaborPizza(String saborVerificar) throws ExceptionDAO {
		String sql = "select sabor from pizza";
		
		Connection conexao = new ConnectionMVC().getConnection();
		PreparedStatement pStatement = null;
		
		ArrayList<String> saboresPizzas = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery();
			
			if(rs != null) {
				saboresPizzas = new ArrayList<String>();
				while(rs.next()) {
					saboresPizzas.add(rs.getString("sabor"));
				}
			}
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao verificar sabores de pizzas: " + erro);
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
		for(int contador = 0; contador < saboresPizzas.size(); contador++) {
			if(saboresPizzas.get(contador).strip().toLowerCase().equals(saborVerificar.strip().toLowerCase())) {
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
