package pizzaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pizzaria.model.Bebida;
import pizzaria.model.Cliente;
import pizzaria.model.Pedido;
import pizzaria.model.Pizza;

public class PedidoDAO {
	
	public void cadastrarPedido(Pedido pedido) throws ExceptionDAO{
		String sql = "insert into pedido (precoPedido, dataPedido, enderecoCliente, idCliente) values (?, ?, ?, ?)";
		PreparedStatement pStatement = null;
		Connection conexao = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			pStatement.setDouble(1, pedido.getPrecoPedido());
			pStatement.setDate(2, pedido.getDataPedido());
			pStatement.setString(3, pedido.getEnderecoCliente());
			pStatement.setInt(4, pedido.getCliente().getIdCliente());
			pStatement.execute();
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao cadastrar pedido: " + erro);
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
	
	
	public void cadastrarPedidoPizza(Pedido pedido, ArrayList<String> tamanhosPizzas) throws ExceptionDAO{
		if(pedido.getPizzas().size() > 0) {
			
			String sql1 = "select idPedido from pedido";
			String sql2 = "insert into pedido_pizza (idPedido, idPizza, tam_pizza) values (?, ?, ?)";
			PreparedStatement pStatement1 = null;
			PreparedStatement pStatement2 = null;
			Connection conexao = null;
			
			ArrayList<Integer> idsPedidos = null;
			
			try {
				conexao = new ConnectionMVC().getConnection();
				pStatement1 = conexao.prepareStatement(sql1);
				ResultSet rs = pStatement1.executeQuery(sql1);
				
				if(rs != null) {
					idsPedidos = new ArrayList<Integer>();
					while(rs.next()) {
						idsPedidos.add(rs.getInt("idPedido"));
					}
				}
				int maiorId = 0;
				for(int contador = 0; contador < idsPedidos.size(); contador++) {
					if(contador == 0) {
						maiorId = idsPedidos.get(contador);
					}
					else {
						if(idsPedidos.get(contador) > maiorId) {
							maiorId = idsPedidos.get(contador);
						}
					}
				}
				
				for(int cont = 0; cont < pedido.getPizzas().size(); cont++) {
					pStatement2 = conexao.prepareStatement(sql2);
					pStatement2.setInt(1, maiorId);
					pStatement2.setInt(2, pedido.getPizzas().get(cont).getIdPizza());
					pStatement2.setString(3, tamanhosPizzas.get(cont));
					pStatement2.execute();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao cadastrar pizzas do pedido: " + erro);
			}
			//Fechando o statement e a conexão:
			finally {
				try {
					if(pStatement1 != null && pStatement2 != null) {
						pStatement1.close();
						pStatement2.close();
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
	
	
	public void cadastrarPedidoBebida(Pedido pedido, ArrayList<String> tamanhosBebidas) throws ExceptionDAO{
		if(pedido.getBebidas().size() > 0) {
			String sql1 = "select idPedido from pedido";
			String sql2 = "insert into pedido_bebida (idPedido, idBebida, tam_bebida) values (?, ?, ?)";
			PreparedStatement pStatement1 = null;
			PreparedStatement pStatement2 = null;
			Connection conexao = null;
			
			ArrayList<Integer> idsPedidos = null;
			
			try {
				conexao = new ConnectionMVC().getConnection();
				pStatement1 = conexao.prepareStatement(sql1);
				ResultSet rs = pStatement1.executeQuery(sql1);
				
				if(rs != null) {
					idsPedidos = new ArrayList<Integer>();
					while(rs.next()) {
						idsPedidos.add(rs.getInt("idPedido"));
					}
				}
				int maiorId = 0;
				for(int contador = 0; contador < idsPedidos.size(); contador++) {
					if(contador == 0) {
						maiorId = idsPedidos.get(contador);
					}
					else {
						if(idsPedidos.get(contador) > maiorId) {
							maiorId = idsPedidos.get(contador);
						}
					}
				}
				
				for(int cont = 0; cont < pedido.getBebidas().size(); cont++) {
					pStatement2 = conexao.prepareStatement(sql2);
					pStatement2.setInt(1, maiorId);
					pStatement2.setInt(2, pedido.getBebidas().get(cont).getIdBebida());
					pStatement2.setString(3, tamanhosBebidas.get(cont));
					pStatement2.execute();
				}
			}
			catch(SQLException erro) {
				throw new ExceptionDAO("Erro ao cadastrar bebidas do pedido: " + erro);
			}
			//Fechando o statement e a conexão:
			finally {
				try {
					if(pStatement1 != null && pStatement2 != null) {
						pStatement1.close();
						pStatement2.close();
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
	
	
	public ArrayList<Pedido> consultarPedidoPizza() throws ExceptionDAO{
		String sql = "select ped.idPedido, piz.sabor as \"sabor_pizza\", ped.precoPedido as \"precoTotalPedido\", ped.dataPedido, cli.nome as \"nome_cliente\", ped.enderecoCliente\r\n"
					+ "from pedido ped\r\n"
					+ "left join pedido_pizza ped_pi\r\n"
					+ "on ped_pi.idPedido = ped.idPedido,\r\n"
					+ "cliente cli, pizza piz\r\n"
					+ "where ped.idCliente = cli.idCliente and ped_pi.idPizza = piz.idPizza\r\n"
					+ "order by ped.idPedido";
		
		
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Pedido> pedidos = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			if(rs != null) {
				pedidos = new ArrayList<Pedido>();
				while(rs.next()) {
					Pedido pedido = new Pedido();
					pedido.setIdPedido(rs.getInt("idPedido"));
					ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
					Pizza pizza = new Pizza();
					pizza.setSabor(rs.getString("sabor_pizza"));
					
					pedido.setPrecoPedido(rs.getDouble("precoTotalPedido"));
					pedido.setDataPedido(rs.getDate("dataPedido"));
					Cliente cliente = new Cliente();
					cliente.setNome(rs.getString("nome_cliente"));
					pedido.setEnderecoCliente(rs.getString("enderecoCliente"));
					
					pizzas.add(pizza);
					pedido.setPizzas(pizzas);
					pedido.setCliente(cliente);
					
					pedidos.add(pedido);

				}
			}

		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar pedidos de pizzas: " + erro);
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
		
		return pedidos;
	}
	
	public ArrayList<Pedido> consultarPedidoBebida() throws ExceptionDAO{
		String sql = "select ped.idPedido, beb.nome as \"nome_bebida\", ped.precoPedido as \"precoTotalPedido\", ped.dataPedido, cli.nome as \"nome_cliente\", ped.enderecoCliente\r\n"
					+ "from pedido ped\r\n"
					+ "left join pedido_bebida ped_be\r\n"
					+ "on ped_be.idPedido = ped.idPedido,\r\n"
					+ "cliente cli, bebida beb\r\n"
					+ "where ped.idCliente = cli.idCliente and ped_be.idBebida = beb.idBebida\r\n"
					+ "order by ped.idPedido";
		
		
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<Pedido> pedidos = null;
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			
			if(rs != null) {
				pedidos = new ArrayList<Pedido>();
				while(rs.next()) {
					Pedido pedido = new Pedido();
					pedido.setIdPedido(rs.getInt("idPedido"));
					ArrayList<Bebida> bebidas = new ArrayList<Bebida>();
					Bebida bebida = new Bebida();
					bebida.setNome(rs.getString("nome_bebida"));
					
					pedido.setPrecoPedido(rs.getDouble("precoTotalPedido"));
					pedido.setDataPedido(rs.getDate("dataPedido"));
					Cliente cliente = new Cliente();
					cliente.setNome(rs.getString("nome_cliente"));
					pedido.setEnderecoCliente(rs.getString("enderecoCliente"));
					
					bebidas.add(bebida);
					pedido.setBebidas(bebidas);
					pedido.setCliente(cliente);
					
					pedidos.add(pedido);

				}
			}

		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar pedidos de pizzas: " + erro);
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
		
		return pedidos;
	}
	
	public ArrayList<String> retornoTamanhoPizzas() throws ExceptionDAO {
		String sql = "select * from pedido_pizza";
	
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<String> tam_pizzas = new ArrayList<String>();
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			if(rs != null) {
				while(rs.next()) {
					tam_pizzas.add(rs.getString("tam_pizza"));
	
				}
			}
	
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar tamanhos de pizzas: " + erro);
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
		
		return tam_pizzas;
	}
	
	public ArrayList<String> retornoTamanhoBebidas() throws ExceptionDAO{
		String sql = "select * from pedido_bebida";
		
		Connection conexao = null;
		PreparedStatement pStatement = null;
		ArrayList<String> tam_bebidas = new ArrayList<String>();
		
		try {
			conexao = new ConnectionMVC().getConnection();
			pStatement = conexao.prepareStatement(sql);
			ResultSet rs = pStatement.executeQuery(sql);
			
			if(rs != null) {
				while(rs.next()) {
					tam_bebidas.add(rs.getString("tam_bebida"));
	
				}
			}
	
		}
		catch(SQLException erro) {
			throw new ExceptionDAO("Erro ao consultar tamanhos de bebidas: " + erro);
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
		
		return tam_bebidas;
	}

}
