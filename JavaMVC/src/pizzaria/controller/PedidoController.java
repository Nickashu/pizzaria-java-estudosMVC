package pizzaria.controller;

import java.sql.Date;
import java.util.ArrayList;

import pizzaria.dao.ExceptionDAO;
import pizzaria.dao.PedidoDAO;
import pizzaria.model.Bebida;
import pizzaria.model.Pedido;
import pizzaria.model.Pizza;

public class PedidoController {
	
	
	public boolean cadastrarPedido(String precoPedido, Date dataPedido, String enderecoCliente, ArrayList<Pizza> pizzas, ArrayList<Bebida> bebidas, int idCliente, ArrayList<String> tamanhoPizzas, ArrayList<String> tamanhoBebidas) throws ExceptionDAO {
		if(verificaPreco(precoPedido) && enderecoCliente != null && enderecoCliente.length() > 0 && pizzas != null && bebidas != null && tamanhoPizzas != null && tamanhoBebidas != null) {
			double precoReal = Double.parseDouble(precoPedido.replace(",", "."));

			Pedido pedido = new Pedido(precoReal, dataPedido, enderecoCliente, pizzas, bebidas, idCliente);
			pedido.cadastrarPedido(pedido);
			
			if(tamanhoBebidas.size() > 0) {
				pedido.cadastrarPedidoBebida(pedido, tamanhoBebidas);
			}
			if(tamanhoPizzas.size() > 0) {
				pedido.cadastrarPedidoPizza(pedido, tamanhoPizzas);
			}

			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Pedido> consultarPedidoPizza() throws ExceptionDAO{
		return new Pedido().consultarPedidoPizza();
	}
	
	public ArrayList<Pedido> consultarPedidoBebida() throws ExceptionDAO{
		return new Pedido().consultarPedidoBebida();
	}
	
	public ArrayList<String> retornoTamanhoPizzas() throws ExceptionDAO {
		return new Pedido().retornoTamanhoPizzas();
	}
	
	public ArrayList<String> retornoTamanhoBebidas() throws ExceptionDAO {
		return new Pedido().retornoTamanhoBebidas();
	}

	
	
	public boolean verificaPreco(String precoValor) {
		if(precoValor.length() == 0 || precoValor == null) {
			return false;
		}
		else {
			double precoReal;
			String precoFormatado = precoValor.replace(",", ".");
			try {
				precoReal = Double.parseDouble(precoFormatado);
				if(precoReal <= 0) {
					return false;
				}
				else {
					return true;
				}
			}
			catch(Exception erro) {
				return false;
			}

		}
	}

}
