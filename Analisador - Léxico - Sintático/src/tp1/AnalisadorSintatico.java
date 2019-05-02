package tp1;
import java.io.*;
import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;
import java.util.Enumeration;

public class AnalisadorSintatico {
	
	public static ArrayList<Tokens> vetorTokens = new ArrayList<Tokens>();
	
	public AnalisadorSintatico(ArrayList<Tokens> vetorTok) {
		
		 this.vetorTokens = vetorTok;
	}
	
	public static Hashtable<String,TableEntry> symbolTable = new Hashtable<String,TableEntry>();
	public static int cont = 0;
	public static String tipo;

	
	public void sintatico() {
		
		//inicio da descida recursiva
		Programa();
		
	}
	
	
	public void imprimeErro() {
		

		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		System.out.println("Erro sintatico. Token "+ tokAtual.nomeToken + " nao esperado na entrada");
		System.exit(0);
		
	
	}
	
	public void match(String tok) {
		
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if(tokAtual.nomeToken == tok) {
			
			System.out.println("Token " + tokAtual.nomeToken + " reconhecido na entrada");
			cont++;
			
		}else {
			imprimeErro();
		}
		
		
	}
	
	public void Programa() {
		
		System.out.println("Ativacao de Programa()");
		
		
		match("INT");
		match("MAIN");
		match("LBRACKET");
		match("RBRACKET");
		match("LBRACE");
		Decl_Comando();
		match("RBRACE");
		
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		if(tokAtual.nomeToken == "EOF") {
			System.out.println("Fim na analise sintatica");
			imprimeTabela();
			System.exit(0);
		}
		imprimeErro();
	}
	
	public void Decl_Comando() {
		
		System.out.println("Ativacao de Decl_Comando()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if((tokAtual.nomeToken  == "INT") || (tokAtual.nomeToken == "FLOAT")){
			Declaracao();
			Decl_Comando();
			
		}else if((tokAtual.nomeToken  == "LBRACE") || (tokAtual.nomeToken == "ID") || (tokAtual.nomeToken == "IF") || (tokAtual.nomeToken == "WHILE") || (tokAtual.nomeToken == "READ") || (tokAtual.nomeToken == "PRINT") || (tokAtual.nomeToken == "FOR") ) {
			
			Comando();
			Decl_Comando();
			
		}
		
	}
	
	public void Declaracao() {
		
		System.out.println("Ativacao de Declaracao()");
		
		Tipo();
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		//Cria objeto e a sua respectiva entrada na tabela
		TableEntry tb = new TableEntry(tokAtual.lexema,tipo,tokAtual.numLinha);
		symbolTable.put(tb.lexema, tb);
		match("ID");
		Decl2();
			
		
		
	}
	
	public void Tipo() {
		
		System.out.println("Ativacao de Tipo()");
		
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		//verifica qual tipo da variavel para entrada na tabela
		
		if(tokAtual.nomeToken == "INT") {
			
			match("INT");
			tipo = "INT";
			
		}else if(tokAtual.nomeToken == "FLOAT") {
			match("FLOAT");
			tipo = "FLOAT";
		}
		
	}
	
	public void Decl2() {
		
		System.out.println("Ativacao de Decl2()");
		
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if(tokAtual.nomeToken == "COMMA") {
			
			match("COMMA");
			tokAtual = (Tokens) vetorTokens.get(cont);
			TableEntry tb = new TableEntry(tokAtual.lexema,tipo,tokAtual.numLinha);
			symbolTable.put(tb.lexema, tb);
			match("ID");
			Decl2();
			
		}else if(tokAtual.nomeToken == "PCOMMA") {
			
			match("PCOMMA");
			
		}else if(tokAtual.nomeToken == "ATTR") {
			
			match("ATTR");
			Expressao();
			Decl2();
			
		}
	}
	
	public void Expressao(){
		
		System.out.println("Ativacao de Expressao()");
		Adicao();
		RelacaoOpc();
		
	}
	
	public void Comando(){
		
		System.out.println("Ativacao de Comando()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if(tokAtual.nomeToken == "LBRACE") {
			
			// bloco
			match("LBRACE");
			Decl_Comando();
			match("RBRACE");
			
		}else if(tokAtual.nomeToken == "ID") {
			
			//atribuicao
			match("ID");
			match("ATTR");
			Expressao();
			match("PCOMMA");
			
		}else if(tokAtual.nomeToken == "IF") {
			
			//comando se
			match("IF");
			match("LBRACKET");
			Expressao();
			match("RBRACKET");
			Comando();
			ComandoSenao();
			
		}else if(tokAtual.nomeToken == "WHILE") {
			
			//comando enquanto
			match("WHILE");
			match("LBRACKET");
			Expressao();
			match("RBRACKET");
			Comando();
			
		}else if(tokAtual.nomeToken == "READ") {
			
			//comando read
			match("READ");
			match("ID");
			match("PCOMMA");
			
		}else if(tokAtual.nomeToken == "PRINT") {
			
			// comando print
			match("PRINT");
			match("LBRACKET");
			Expressao();
			match("RBRACKET");
			match("PCOMMA");
			
		}else if(tokAtual.nomeToken == "FOR") {
			
			//comando for
			match("FOR");
			match("LBRACKET");
			AtribuicaoFor();
			match("PCOMMA");
			Expressao();
			match("PCOMMA");
			AtribuicaoFor();
			match("RBRACKET");
			Comando();
				
		}
		
		
		
	}
	
	public void ComandoSenao() {
		
		System.out.println("Ativacao de ComandoSenao()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
			
		if(tokAtual.nomeToken == "ELSE") {
			match("ELSE");
			Comando();
		}	
	}
	
	public void AtribuicaoFor() {
		
		System.out.println("Ativacao de AtribuicaoFor()");
		match("ID");
		match("ATTR");
		Expressao();
		
	}
	public void Adicao() {
		
		System.out.println("Ativacao de Adicao()");
		Termo();
		AdicaoOpc();
		
	}
	
	public void RelacaoOpc() {
		
		System.out.println("Ativacao de RelacaoOpc()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		if((tokAtual.nomeToken == "LT") || (tokAtual.nomeToken == "LE") || (tokAtual.nomeToken == "GT") || (tokAtual.nomeToken == "GE")) {
		
			OpRel();
			Adicao();
			RelacaoOpc();
		}else {
			//nada
		}
			
	}
	
	public void OpRel() {
		
		System.out.println("Ativacao de OpRel()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if(tokAtual.nomeToken == "LT") {
			match("LT");
			
		}else if(tokAtual.nomeToken == "LE") {
			match("LE");
			
		}else if(tokAtual.nomeToken == "GT") {
			match("GT");
			
		}else if(tokAtual.nomeToken == "GE") {
			match("GE");
		}
		
	}
	public void Termo() {
		
		System.out.println("Ativacao de Termo()");
		
		Fator();
		TermoOpc();
		
	}
	
	public void AdicaoOpc() {
		
		System.out.println("Ativacao de AdicaoOpc()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if((tokAtual.nomeToken == "PLUS") || (tokAtual.nomeToken == "MINUS")) {
		
			OpAdicao();
			Termo();
			AdicaoOpc();
		}	else {
			
		}
		
	}
	
	public void OpAdicao() {
		
		System.out.println("Ativacao de OpAdicao()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if(tokAtual.nomeToken == "PLUS") {
			match("PLUS");
			
		}else if(tokAtual.nomeToken == "MINUS") {
			match("MINUS");
			
		}
	}
	
	public void Fator() {
		
		System.out.println("Ativacao de Fator()");
		
		
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		if(tokAtual.nomeToken == "ID") {
			match("ID");
			
		}else if(tokAtual.nomeToken == "INTEGER_CONST") {
			match("INTEGER_CONST");
			
		}else if(tokAtual.nomeToken == "FLOAT_CONST") {
			match("FLOAT_CONST");
			
		}else if(tokAtual.nomeToken == "LBRACKET") {
			match("LBRACKET");
			Expressao();
			match("RBRACKET");
		}else {
			imprimeErro();
		}
		
		
	}
	
	public void TermoOpc() {
		
		System.out.println("Ativacao de TermoOpc()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if((tokAtual.nomeToken == "MULT") || (tokAtual.nomeToken == "DIV")){
			OpMult();
			Fator();
			TermoOpc();
		}else {
			
		}
	
	}
	
	public void OpMult() {
		
		System.out.println("Ativacao de OpMult()");
		Tokens tokAtual = (Tokens) vetorTokens.get(cont);
		
		if(tokAtual.nomeToken == "MULT") {
			match("MULT");
			
		}else if(tokAtual.nomeToken == "DIV") {
			match("DIV");
			
		}
	}
	
	
	public void imprimeTabela() {
		
		System.out.println("Tabela de simbolos: ");
		Set<String> keys = symbolTable.keySet();
		
		for(String key : keys) {
			
			if(key != null) {
				System.out.println("Lexema:" + symbolTable.get(key).lexema + " " +"-"+ " "+ 
						"Tipo:" +  symbolTable.get(key).tipo + " "+ "-"+ " "+
						 "Numero da linha: " + symbolTable.get(key).numeroLinha);
			}
			
		}
		
		
	}
	
}
