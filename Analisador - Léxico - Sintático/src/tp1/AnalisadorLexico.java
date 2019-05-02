package tp1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class AnalisadorLexico {

	
	public static ArrayList<Tokens> analisa(String arquivo)throws java.lang.StringIndexOutOfBoundsException, IOException {
		
		ArrayList<Tokens> vetorTokens = new ArrayList<Tokens>();
		
		
		File file = new File(arquivo);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		String lexema = " ";
		int contadorLinha = 1,estado = 1;
		String aux = " ";
		char c;
		int  ch;
		String w = "";
		
		
				
		while((ch = br.read())!= -1 ) {
			
			
					
		
			
			char charAtual = (char)ch;
			
			if(charAtual =='\n') {
				contadorLinha++;
			}
			
			
			//concatena na string somente caracteres que poderao se tornar ids e palavras reservadas
			if(Pattern.matches("[a-zA-Z_0-9.]",Character.toString(charAtual))) {
				w = w.concat(Character.toString(charAtual));
			}else {
		
			
				    	
			    	if(w.equals("int")) {
			    		System.out.println("Token INT ");
			    		Tokens token = new Tokens("INT","int",contadorLinha);
						vetorTokens.add(token);
						w = "";
			    		
			    	}
			    	else if(w.equals("main")){
			    		System.out.println("Token MAIN");
			    		Tokens token = new Tokens("MAIN","main",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("float")){
			    		System.out.println("Token FLOAT");
			    		Tokens token = new Tokens("FLOAT","float",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("if")){
			    		System.out.println("Token IF");
			    		Tokens token = new Tokens("IF","if",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("else")){
			    		System.out.println("Token ELSE");
			    		Tokens token = new Tokens("ELSE","else",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("while")){
			    		System.out.println("Token WHILE");
			    		Tokens token = new Tokens("WHILE","while",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("for")){
			    		System.out.println("Token FOR");
			    		Tokens token = new Tokens("FOR","for",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("read")){
			    		System.out.println("Token READ");
			    		Tokens token = new Tokens("READ","read",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(w.equals("print")){
			    		System.out.println("Token PRINT");
			    		Tokens token = new Tokens("PRINT","print",contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			  	
			    	else if(Pattern.matches("[0-9]([0-9])*",w)){
			    		System.out.println("Token INTEGER_CONST = "+w);
			    		Tokens token = new Tokens("INTEGER_CONST",w,contadorLinha);
						vetorTokens.add(token);
						w = "";
						
			    	}
			    	else if(Pattern.matches("[0-9]([0-9])*.[0-9]([0-9])*",w)){
			    		System.out.println("Token FLOAT_CONST = "+w);
			    		Tokens token = new Tokens("FLOAT_CONST",w,contadorLinha);
						vetorTokens.add(token);
						w="";
						
			    	}
			    	else if(Pattern.matches("[A-Za-z]([A-Za-z0-9_])*",w)){			    					    		
				    	System.out.println("Token ID = "+w);
				    	Tokens token = new Tokens("ID",w,contadorLinha);
						vetorTokens.add(token);
						w = "";
			    		
						
			    	}
			}	
			    		  
			  int i=100;
				
				
				aux = Character.toString(charAtual);
				
				
				switch(estado) {
				
				
				case 1:
									
						if(charAtual == '(') {
							System.out.println("Token LBRACKET = " + charAtual );
							Tokens token = new Tokens("LBRACKET","(",contadorLinha);
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == ')') {
							System.out.println("Token RBRACKET = " + charAtual);
							Tokens token = new Tokens("RBRACKET",")",contadorLinha);
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == '{') {
							System.out.println("Token LBRACE = " + charAtual);
							Tokens token = new Tokens("LBRACE","{",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == '}') {
							System.out.println("Token RBRACE = " + charAtual);
							Tokens token = new Tokens("RBRACE","}",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == ',') {
							System.out.println("Token COMMA = " + charAtual);
							Tokens token = new Tokens("COMMA",",",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == ';') {
							System.out.println("Token PCOMMA = " + charAtual);
							Tokens token = new Tokens("PCOMMA",";",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == '+') {
							System.out.println("Token PLUS " + charAtual);
							Tokens token = new Tokens("PLUS","+",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == '-') {
							System.out.println("Token MINUS = " + charAtual);
							Tokens token = new Tokens("MINUS","-",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == '*') {
							System.out.println("Token MULT = " + charAtual);
							Tokens token = new Tokens("MULT","*",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}
						else if(charAtual == '/') {
							System.out.println("Token DIV = " + charAtual);
							Tokens token = new Tokens("DIV","/",contadorLinha); 
							vetorTokens.add(token);
							estado = 1;
						}	
						else if(charAtual == '=') {
							
							estado = 3;
						}	
						else if(charAtual == '<') {
							estado = 4;
						}
						else if(charAtual == '>') {
							estado = 5;
						}
						else if(charAtual == '!') {
							estado = 6;
						}
						else if(charAtual == '|') {
							estado = 7;
						}
						else if(charAtual == '&') {
							estado = 8;
						}
					
						
				
				//estado = 2;	
				break;	
				
				case 2: 
			
							
							
				break;
				
				case 3: 
					
					if(charAtual == '=') {
						System.out.println("Token EQ = = " + charAtual);
						Tokens token = new Tokens("EQ","==",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
					}else {
						System.out.println("Token ATTR = =" + charAtual);
						Tokens token = new Tokens("ATTR","=",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
						i = i-1;
						
					}
				break;	
				case 4:
					if(charAtual == '=') {
						System.out.println("Token LE = " + charAtual);
						Tokens token = new Tokens("LE","<=",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
					}else {
						System.out.println("Token LT = <" + charAtual);
						Tokens token = new Tokens("LT","<",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
						i = i - 1;
					}
				break;	
				case 5:
					if(charAtual == '=') {
						System.out.println("Token GE = >" + charAtual);
						Tokens token = new Tokens("GE",">=",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
					}else {
						System.out.println("Token GT = >" + charAtual);
						Tokens token = new Tokens("GT",">",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
						i = i - 1;
						
					}
				break;
				case 6:
					if(charAtual == '=') {
						System.out.println("Token GE = !" + charAtual);
						Tokens token = new Tokens("GE","!=",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
					}else {
						i = i - 1;
					}
				break;
				case 7:
					if(charAtual == '|') {
						System.out.println("Token OR = |" + charAtual);
						Tokens token = new Tokens("OR","||",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
					}else {
						i = i - 1;
					}
					
					
				break;	
				case 8 :
					if(charAtual == '&') {
						System.out.println("Token AND = &" + charAtual);
						Tokens token = new Tokens("AND","&&",contadorLinha); 
						vetorTokens.add(token);
						estado = 1;
					}else {
						i = i - 1;
					}
					
				break;	
					
					
				}
									
		}
		Tokens token = new Tokens("EOF","EOF",contadorLinha);
		vetorTokens.add(token);
		
			
				
	return vetorTokens;		
	}	
	
	
	

}

