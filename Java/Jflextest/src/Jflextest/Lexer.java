package Jflextest;

import java.io.File;

public class Lexer {

	public static final String path ="NewLexer.flex";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		generarLexer(path);
	}
	
	public static void generarLexer(String path){
		File file = new File(path);
		jflex.Main.generate(file);
	}
	

}
