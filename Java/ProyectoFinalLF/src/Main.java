/* Proyecto CUP - Equipo 7 
 * Rodríguez Castro; Cordero Hernández
 * Parte 1 - Funcionamiento de JFLEX y CUP con calculadora */
   
import java.io.*;
   
public class Main {
  static public void main(String argv[]) {    
    try {
		  FileReader fr = new FileReader("inputRegex.txt");
		  parser p = new parser(new Lexer(fr));     
		  Object result = p.parse().value;
      } catch (Exception e) {
    	  e.printStackTrace();
      }
  }
}