package Jflex;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class TestClass {
		
	public static void main(String args[]) throws IOException{
		String top = "<!DOCTYPE html><html lang=\"es\"><head><title>ARCHIVO GENERADO</title><style>* {text-align: center;}</style></head><body>";
		String nombres = "<h2>NOMBRES ENCONTRADOS</h2>";
		String locales = "</hr><h2>TELÉFONOS LOCALES ENCONTRADOS</h2>";
		String celulares = "</hr><h2>CELULARES ENCONTRADOS</h2>";
		String urls = "</hr><h2>URLS ENCONTRADAS</h2>";
		String correos = "</hr><h2>CORREOS ENCONTRADOS</h2>";
		String videos = "</hr><h2>VIDEOS ENCONTRADOS</h2>";
		String bottom = "</body></html>";
		int names = 0, locals = 0, cels = 0, urlS = 0, mails = 0, vids = 0, errors = 0;
		
		try {
			String str = JOptionPane.showInputDialog("Introduce la ruta de tu archivo");
			File myObj = new File(str);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				InputStream is = new ByteArrayInputStream(data.getBytes());
				NewLexer lexer = new NewLexer(is);
				 
				while(true){
					Token token = lexer.yylex();
					if(token == null) break;
					 
					switch(token){
					case NOMBRE:
						nombres += String.format("<p><strong>%s</strong></p>", lexer.lexeme);
						names++;
						break;
					case LOCAL:
						locales += String.format("<p style=\"color:green;\">%s</p>", lexer.lexeme);
						locals++;
						break;
					case CELULAR:
						celulares += String.format("<p style=\"color:red;\">%s</p>", lexer.lexeme);
						cels++;
						break;
					case URL:
						urls += String.format("<a href=\"%s\">%s</a></br>", lexer.lexeme, lexer.lexeme);
						urlS++;
						break;
					case EMAIL:
						correos += String.format("<a href=\"mailto:%s\">%s</a></br>", lexer.lexeme, lexer.lexeme);
						mails++;
						break;
					case VIDEO:
						videos += String.format("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/%s\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></br></br>", embeded(lexer.lexeme));
						vids++;
						break;
					case ERROR:
					default:
						errors++;
						break;
					}
				}
			}
			
			try {
			      File html = new File(String.format("output%s.html", System.currentTimeMillis()));
			      if (html.createNewFile()) {
			    	  System.out.println("Archivo generado: " + html.getName() + "\n");
			    	  FileWriter toWrite = new FileWriter(html.getName());
			    	  toWrite.write(top + nombres + locales + celulares + urls + correos + videos + bottom);
			    	  toWrite.close();
			      } else {
			    	  System.out.println("El archivo ya existe.");
			      }
			    } catch (IOException e) {
			      System.out.println("Ocurrió un error inesperado.");
			      e.printStackTrace();
			    }
			myReader.close();
			
			System.out.printf("REPORTE GENERADO\nNombres: %d\nTeléfonos locales: %d\nCelulares: %d\nURL's: %d\nCorreos: %d\nVideos: %d\nErrores: %d\n",
					names, locals, cels, urlS, mails, vids, errors);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrió un error inesperado.");
			e.printStackTrace();
		}	 
	}
	
	static String embeded(String previous) {
		String id = "";
		boolean set = false;
		for(char c : previous.toCharArray()) {
			if(set) id += c;
			if(c == '=') set = true;
		}
		return id;
	}
}