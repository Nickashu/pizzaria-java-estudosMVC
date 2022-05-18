package pizzaria.controller;

import java.util.ArrayList;

import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Pizza;

public class PizzaController {
	
	public boolean cadastrarPizza(String sabor, String ingredientes, String preco) throws ExceptionDAO {
		if(sabor != null && sabor.length() > 0 && ingredientes != null && ingredientes.length() > 0 && verificaPreco(preco)) {
			
			Double precoReal = Double.parseDouble(preco.replace(",", "."));
			
			Pizza pizza = new Pizza(sabor, ingredientes, precoReal);
			pizza.cadastrarPizza(pizza);
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Pizza> consultarPizza(String nome) throws ExceptionDAO{
		return new Pizza().consultarPizza(nome);
	}
	
	public boolean alterarPizza(int idPizza, String sabor, String ingredientes, String preco) throws ExceptionDAO {
		if(sabor != null && sabor.length() > 0 && ingredientes != null && ingredientes.length() > 0 && verificaPreco(preco)) {
			
			Double precoReal = Double.parseDouble(preco.replace(",", "."));
			
			Pizza pizza = new Pizza(sabor, ingredientes, precoReal);
			pizza.setIdPizza(idPizza);
			pizza.alterarPizza(pizza);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean apagarPizza(int idPizza) throws ExceptionDAO{
		if(idPizza == 0) {
			return false;
		}
		else {
			Pizza pizza = new Pizza();
			pizza.setIdPizza(idPizza);
			pizza.apagarPizza(pizza);
			return true;
		}
	}
	
	public ArrayList<Pizza> listarPizza() throws ExceptionDAO{
		return new Pizza().listarPizza();
	}
	
	public boolean verificaSaborPizza(String saborPizzaVerificar) throws ExceptionDAO{
		return new Pizza().verificaSaborPizza(saborPizzaVerificar);
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
				if(precoReal < 25) {
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
