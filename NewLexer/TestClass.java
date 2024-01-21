package Jflextest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class TestClass {
		
	public static void main(String args[]) throws IOException{
		 String str = JOptionPane.showInputDialog("expresión");
		 
		 InputStream is = new ByteArrayInputStream(str.getBytes());
		 //BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		 NewLexer lexer = new NewLexer (is);
		 String resultados ="";
		 
		 while(true){
			 Token token = lexer.yylex();
			 if(token ==null){
				 resultados+= "FIN";
				 break;
			 }
			 
			 //igual, suma, variable, numero, multiplicacion, resta, division, ERROR
			 switch(token){
				 case ERROR: resultados+="ERROR"+ lexer.lexeme+" \n"; 
				 			break;
				 case ID:
				 case numero:
				 case variable: resultados+="Token:"+token+" "+ lexer.lexeme+"\n";
				 		break;
				 case IF:
				 case THEN:
				 case ELSE: 
					 resultados+="Palabra reservada de if\n";
			     break;
				 default: resultados+="Token:"+token+"\n";
				 		break;
				 }
			 }
		 
		 JOptionPane.showMessageDialog(null, resultados);
			 
		 }
		 
		 
}
