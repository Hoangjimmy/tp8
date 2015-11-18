/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
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

		try {
			Class c = Class.forName(className);
			try {
				Field[] fi = c.getDeclaredFields();
				Method[] meth = c.getDeclaredMethods();
				FileWriter writer = new FileWriter(f, false);

				writer.write(hppFormating(className, meth, fi));
				writer.flush();
				writer.close();

			} catch (IOException ex2) {
				Logger.getLogger(Tp8.class.getName()).log(Level.SEVERE, null, ex2);
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		}

	}

	public static String hppFormating(String className, Method[] meth, Field[] fi) {

		StringBuilder sb = new StringBuilder();
		String s;

		sb = sb.append("class ").append(className).append(" { \n").append("\tprivate : \n");
		for (int i = 0; i < fi.length; ++i){
			sb.append("\t\t")
					.append(fi[i].toString()
							.replace("private", "")
							.replace("boolean", "bool")
							.replace("java.lang.String", "string")
							.replace(className + ".", ""))
					.append(";\n");
		}
		sb.append("\tpublic : \n");
		for (int i = 0; i < meth.length; ++i){
			sb.append("\t\t")
					.append(meth[i].toString()
							.replace("public", "")
							.replace("java.lang.String", "string")
							.replace("boolean", "bool")
							.replace(className + ".", ""))
					.append(";\n");
		}
		sb.append("};");
		return sb.toString();
	}

}
