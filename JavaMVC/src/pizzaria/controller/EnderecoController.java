package pizzaria.controller;

import java.util.ArrayList;

import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Endereco;

public class EnderecoController {
	
	public boolean cadastrarEndereco(int idCliente, String cidade, String bairro, String rua, String numeroTxt, String complemento) throws ExceptionDAO {
		if(idCliente > 0 && cidade != null && cidade.length() > 0 && bairro != null && bairro.length() > 0 && rua != null && rua.length() > 0 && verificaNumero(numeroTxt) && complemento != null) {
			int numeroReal = Integer.parseInt(numeroTxt);
			Endereco endereco = new Endereco(idCliente, cidade, bairro, rua, numeroReal, complemento);
			endereco.cadastrarEndereco(endereco);
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Endereco> consutarEndereco(String nomeCliente) throws ExceptionDAO{
		return new Endereco().consultarEndereco(nomeCliente);
	}
	
	public boolean alterarEndereco(int idEndereco, int idCliente, String cidade, String bairro, String rua, String numeroTxt, String complemento) throws ExceptionDAO {
		if(idCliente > 0 && cidade != null && cidade.length() > 0 && bairro != null && bairro.length() > 0 && rua != null && rua.length() > 0 && verificaNumero(numeroTxt) && complemento != null) {
			int numeroReal = Integer.parseInt(numeroTxt);
			Endereco endereco = new Endereco(idCliente, cidade, bairro, rua, numeroReal, complemento);
			endereco.setIdEndereco(idEndereco);
			endereco.alterarEndereco(endereco);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean apagarEndereco(int idEndereco) throws ExceptionDAO{
		if(idEndereco == 0) {
			return false;
		}
		else {
			Endereco endereco = new Endereco();
			endereco.setIdEndereco(idEndereco);
			endereco.apagarEndereco(endereco);
			return true;
		}
	}
	
	
	
	public boolean verificaNumero(String numeroTxt) {
		if(numeroTxt.length() == 0 || numeroTxt == null) {
			return false;
		}
		else {
			int numeroReal;
			try {
				numeroReal = Integer.parseInt(numeroTxt);
				if(numeroReal <= 0) {
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
	
	public ArrayList<Endereco> listarEnderecos(int idCliente) throws ExceptionDAO{
		return new Endereco().listarEnderecos(idCliente);
	}

}
