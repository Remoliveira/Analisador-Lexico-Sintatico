package tp1;
import java.util.ArrayList;

public class Tokens {
	
	String nomeToken;
	String lexema;
	int numLinha;
	
	
	
	public  Tokens(String nomeToken, String lexema, int numLinha){
		
		this.nomeToken = nomeToken;
		this.lexema = lexema;
		this.numLinha = numLinha;
		
	}
	public void imprimir(int i) {
	
		System.out.println("Tabela token :  "
				+ "Nome do token: "+ nomeToken
				+ "Lexema: "+ lexema
				+ "Numero da linha: "+ numLinha);
		
	}
	
}