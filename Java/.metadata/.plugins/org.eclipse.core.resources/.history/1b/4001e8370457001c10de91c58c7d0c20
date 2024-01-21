import java.io.File;
import java.io.IOException;

import java_cup.internal_error;

public class GenerateParser {

	public static final String path ="ycalc2.cup";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			generarLexer(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generarLexer(String path) throws internal_error, IOException, Exception{
		File file = new File(path);
		//jflex.Main.generate(file);
		String st[] = new String[1];
		st[0] = path;
		java_cup.Main.main(st);
	}
	

}
