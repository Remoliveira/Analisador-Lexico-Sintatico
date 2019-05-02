package tp1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compilador {
	
	
	
	public static void compila(String arquivo) throws StringIndexOutOfBoundsException, IOException {
	
		ArrayList<Tokens> vTo = new ArrayList<Tokens>();

		vTo = AnalisadorLexico.analisa(arquivo);
					
		AnalisadorSintatico as = new AnalisadorSintatico(vTo);
		as.sintatico();
		
	}
	

}