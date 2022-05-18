package pizzaria.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.text.ParseException;

import pizzaria.dao.ExceptionDAO;
import pizzaria.model.Cliente;

public class ClienteController {
	
	public int cadastrarCliente(String nome, String telefone, String email, String cpf, String dataNascimento) throws ExceptionDAO {
		if(nome != null && nome.length() > 0 && telefone != null && telefone.length() > 0 && validarEmail(email) && cpf != null && cpf.length() > 0 && validarCPF(cpf) && dataNascimento != null && dataNascimento.length() > 0 && formatoDataValida(dataNascimento)) {
			int dia = Integer.parseInt(dataNascimento.substring(0, 2));
			int mes = Integer.parseInt(dataNascimento.substring(3, 5));
			int ano = Integer.parseInt(dataNascimento.substring(6, 10));
			if(dataValida(dia, mes, ano)) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date dataDeNascimento = null;
				try {
					dataDeNascimento = new Date(format.parse(dataNascimento).getTime());
					Cliente cliente = new Cliente(nome, telefone, email, cpf, dataDeNascimento);
					cliente.cadastrarCliente(cliente);
					return 0;
				} catch (ParseException e) {
					return -1;
				}
			}
			else {
				return 1;
			}
		}
		else {
			return -1;
		}
		
	}
	
	
	public ArrayList<Cliente> consultarClientes(String nome) throws ExceptionDAO{
		return new Cliente().consultarClientes(nome);
	}
	
	
	public int alterarCliente(int idCliente, String nome, String telefone, String email, String cpf, String dataNascimento) throws ExceptionDAO {
		if(nome != null && nome.length() > 0 && telefone != null && telefone.length() > 0 && validarEmail(email) && cpf != null && cpf.length() > 0 && validarCPF(cpf) && dataNascimento != null && dataNascimento.length() > 0 && formatoDataValida(dataNascimento)) {
			int dia = Integer.parseInt(dataNascimento.substring(0, 2));
			int mes = Integer.parseInt(dataNascimento.substring(3, 5));
			int ano = Integer.parseInt(dataNascimento.substring(6, 10));
			if(dataValida(dia, mes, ano)) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date dataDeNascimento = null;
				try {
					dataDeNascimento = new Date(format.parse(dataNascimento).getTime());
					Cliente cliente = new Cliente(nome, telefone, email, cpf, dataDeNascimento);
					cliente.setIdCliente(idCliente);
					cliente.alterarCliente(cliente);
					return 0;
				} catch (ParseException e) {
					return -1;
				}
			}
			else {
				return 1;
			}
		}
		else {
			return -1;
		}
		
	}
	
	public boolean apagarCliente(int idCliente) throws ExceptionDAO{
		if(idCliente == 0) {
			return false;
		}
		else {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(idCliente);
			cliente.apagarCliente(cliente);
			return true;
		}
	}
	
	public boolean verificarClientes() throws ExceptionDAO{
		return new Cliente().verificarClientes();
	}
	
	
	
	public boolean validarEmail(String email) {
		if(email == null || email.length() <= 9) {
			return false;
		}
		else {
			if(email.contains("@") && email.contains(".com") && (email.indexOf("@") < email.indexOf(".com"))){
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public boolean validarCPF(String cpf) {
		int i;
		String cpfNum = "";
		boolean invalido = false;
		
		for(i = 0; i<cpf.length(); i++) {
			if(!Character.isDigit(cpf.charAt(i))) {   //Se não for um dígito
				if(i != 3 && i != 7 && i != 11) {
					invalido = true;
					break;
				}
			}
		}
		
		if(invalido) {
			return false;
		}
		else {
			for(i=0; i<cpf.length(); i++) {
				if(i != 3 && i != 7 && i != 11) {
					cpfNum+=cpf.charAt(i);
				}
			}
			
			if (cpfNum.equals("00000000000") || cpfNum.equals("11111111111") || cpfNum.equals("22222222222") || cpfNum.equals("33333333333") || cpfNum.equals("44444444444") || cpfNum.equals("55555555555") || cpfNum.equals("66666666666") || cpfNum.equals("77777777777") || cpfNum.equals("88888888888") || cpfNum.equals("99999999999") || (cpfNum.length() != 11)) {
				return false;
			}
			
			char dig10, dig11;
	        int sm, x, r, num, peso;
	        
	        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
	        try {
	        	// Calculo do 1o. Digito Verificador
	            sm = 0;
	            peso = 10;
	            for (x=0; x<9; x++) {
	            	// converte o i-esimo caractere do CPF em um numero:
	            	// por exemplo, transforma o caractere '0' no inteiro 0
	            	// (48 eh a posicao de '0' na tabela ASCII)
		            num = (int)(cpfNum.charAt(x) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11)) {
	            	dig10 = '0';
	            }
	            else {
	            	dig10 = (char)(r + 48); // converte no respectivo caractere numerico
	            }

	            // Calculo do 2o. Digito Verificador
	            sm = 0;
	            peso = 11;
	            for(x=0; x<10; x++) {
		            num = (int)(cpfNum.charAt(x) - 48);
		            sm = sm + (num * peso);
		            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11)) {
	            	dig11 = '0';
	            }
	            else {
	            	dig11 = (char)(r + 48);
	            }

	            // Verifica se os digitos calculados conferem com os digitos informados.
	            if ((dig10 == cpfNum.charAt(9)) && (dig11 == cpfNum.charAt(10))) {
	            	return true;
	            }
	            else{
	            	return false;
	            }
	        }
	        catch (InputMismatchException erro) {
	            return false;
	        }
		}

	}
	
	
	public boolean formatoDataValida(String dataModelo) {
		String num1txt, num2txt, num3txt;
		int num1, num2, num3;
		
		if(dataModelo.length() != 10){
			return false;
		}
		else {
			if(dataModelo.charAt(2) == '/' && dataModelo.charAt(5) == '/') {
				num1txt = dataModelo.substring(0, 2);
				num2txt = dataModelo.substring(3, 5);
				num3txt = dataModelo.substring(6, 10);
				
				try {
					num1 = Integer.parseInt(num1txt);
					num2 = Integer.parseInt(num2txt);
					num3 = Integer.parseInt(num3txt);
					
					return true;
				}
				catch(NumberFormatException erro) {
					return false;
				}
			}
			else {
				return false;
			}
		}
	}
	
	
	//Fazendo a validação da data:
	public boolean dataValida(int dia, int mes, int ano) {
		
		Calendar calTemp = Calendar.getInstance();
		
		int anoAtual = calTemp.get(Calendar.YEAR);
		int mesAtual = calTemp.get(Calendar.MONTH) + 1;
		int diaAtual = calTemp.get(Calendar.DAY_OF_MONTH);
		
		//Verificando se a/o cliente tem mais de 18 anos:
		if(ano >= anoAtual-17){ 
			return false;
		}
					
		else if(ano == anoAtual-18){    
			if(mes > mesAtual) {
				return false;
			}
			else if(mes == mesAtual) {
				if(mes == 2) {
					if((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {   //Verificando se o ano é bissexto
						if(dia > 29) {
							return false;
						}
						else {
							if(dia > diaAtual) {
								return false;
							}
							else{
								return true;
							}
						}
					}
					else {
						if(dia > 28) {
							return false;
						}
						else {
							if(dia > diaAtual) {
								return false;
							}
							else{
								return true;
							}
						}
					}
				}
				else if(mes == 1 || mes== 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
					if(dia > 31) {
						return false;
					}
					else {
						if(dia > diaAtual) {
							return false;
						}
						else{
							return true;
						}
					}
				}
				else {
					if(dia > 30) {
						return false;
					}
					else {
						if(dia > diaAtual) {
							return false;
						}
						else{
							return true;
						}
					}
				}
			}
			
			else {
				return true;
			}
		}
		
		else{
			if(mes == 2) {
				if((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {   //Verificando se o ano é bissexto
					if(dia > 29) {
						return false;
					}
					else {
						return true;
					}
				}
				else {
					if(dia > 28) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			else if(mes == 1 || mes== 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
				if(dia > 31) {
					return false;
				}
				else {
					return true;
				}
			}
			else {
				if(dia > 30) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		
	}
	

}
