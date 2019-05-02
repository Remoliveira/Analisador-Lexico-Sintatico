package tp1;

public class TableEntry {
	

	public String lexema;
	public String tipo;
	public int numeroLinha;
	
	
	public TableEntry(String lexema, String tipo, int numeroLinha) {
		
		this.lexema = lexema;
		this.tipo = tipo;
		this.numeroLinha = numeroLinha;
		
	}


	public String getLexema() {
		return lexema;
	}


	public void setLexema(String lexema) {
		this.lexema = lexema;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getNumeroLinha() {
		return numeroLinha;
	}


	public void setNumeroLinha(int numeroLinha) {
		this.numeroLinha = numeroLinha;
	}
	
	
	
	
	
	
	

}
