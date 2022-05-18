package pizzaria.controller;

import java.util.ArrayList;

import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Bebida;

public class BebidaController {
	
	public boolean cadastrarBebida(String nome, String precoTxt) throws ExceptionDAO {
		if(nome != null && nome.length() > 0 && verificaPreco(precoTxt)) {

			double precoReal = Double.parseDouble(precoTxt.replace(",", "."));
			
			Bebida bebida = new Bebida(nome, precoReal);
			bebida.cadastrarBebida(bebida);
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Bebida> consultarBebida(String nomeBebida) throws ExceptionDAO{
		return new Bebida().consultarBebida(nomeBebida);
	}
	
	public boolean alterarBebida(int idBebida, String nome, String precoTxt) throws ExceptionDAO {
		if(nome != null && nome.length() > 0 && verificaPreco(precoTxt)) {

			double precoReal = Double.parseDouble(precoTxt.replace(",", "."));
			
			Bebida bebida = new Bebida(nome, precoReal);
			bebida.setIdBebida(idBebida);
			bebida.alterarBebida(bebida);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean apagarBebida(int idBebida) throws ExceptionDAO{
		if(idBebida == 0) {
			return false;
		}
		else {
			Bebida bebida = new Bebida();
			bebida.setIdBebida(idBebida);
			bebida.apagarBebida(bebida);
			return true;
		}
	}
	
	public ArrayList<Bebida> listarBebida() throws ExceptionDAO{
		return new Bebida().listarBebida();
	}
	
	public boolean verificaNomeBebida(String nomeBebidaVerifica) throws ExceptionDAO{
		return new Bebida().verificarNomePizza(nomeBebidaVerifica);
	}
	
	
	
	
	public boolean verificaPreco(String precoTxt) {
		if(precoTxt.length() == 0 || precoTxt == null) {
			return false;
		}
		else {
			String precoFormatado = precoTxt.replace(",", ".");
			try {
				double precoReal = Double.parseDouble(precoFormatado);
				if(precoReal < 2) {
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
