/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoangjim
 */
public class Tp8 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		if (args.length < 1)
			System.out.println("Usage tp8 <JavaClassName> <GeneratedC++ClassName>");
		else
			hppMaker(args[0]);

	}

	public static void hppMaker(String className) {

		File f = new File(className + ".hpp");
		StringBuilder sb = new StringBuilder();

		try {
			Class c = Class.forName(className);
			try {
				FileWriter writer = new FileWriter(f, true);
				sb = sb.append("class ").append(className).append(" { \n").append("private : \n");
				Method[] meth = c.getMethods();
				for (int i = 0; i < 6; ++i)
					sb.append(meth[i].toString().replace("public","")).append(";\n");
				sb.append("}");
				writer.write(sb.toString());
				writer.flush();
				writer.close();

			} catch (IOException ex2) {
				Logger.getLogger(Tp8.class.getName()).log(Level.SEVERE, null, ex2);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		}

	}

}
