package tp1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws StringIndexOutOfBoundsException, IOException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o arquivo a ser analisado");	
		
		int i = 0;
		while(i == 0) {
			try {
				String arquivo = scan.nextLine();
				Compilador.compila(arquivo);	
				i = 1;
				
			}catch(FileNotFoundException ex) {
				 
				System.out.println("Arquivo nao encontrado, digite novamente.");
			}
		}		
	}
	
}
