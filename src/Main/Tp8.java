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

		if (args.length < 1 || args.length > 3)
			System.out.println("Usage tp8 <JavaClassName> <GeneratedC++ClassName> <--stdout>");
		else
			try {
				Class c = Class.forName(args[0]);
				if (args.length < 2 || (args.length < 3 && args[1] != "--stdout"))
					hppMaker(c.getName(), c);
				else
					hppMaker(args[1], c);
				/*if ("--stdout".compareTo(args[2])> 0) {
					System.out.println(hppFormating(c.getName(), c));
				}*/
			} catch (ClassNotFoundException ex) {
				System.err.println("Class not found");
			}

	}

	public static void hppMaker(String className, Class c) {

		File f = new File(className + ".hpp");

		try {

			FileWriter writer = new FileWriter(f, false);

			writer.write(hppFormating(className, c));
			writer.flush();
			writer.close();

		} catch (IOException ex2) {
			Logger.getLogger(Tp8.class.getName()).log(Level.SEVERE, null, ex2);
		}

	}

	public static String hppFormating(String className, Class c) {

		StringBuilder sb = new StringBuilder();
		String s;
		Field[] fi = c.getDeclaredFields();
		Method[] meth = c.getDeclaredMethods();

		sb = sb.append("class ").append(className).append(" { \n").append("\tprivate : \n");
		for (int i = 0; i < fi.length; ++i)
			sb.append("\t\t")
					.append(fi[i].toString()
							.replace("private", "")
							.replace("boolean", "bool")
							.replace("java.lang.String", "string")
							.replace(className + ".", ""))
					.append(";\n");
		sb.append("\tpublic : \n");
		for (int i = 0; i < meth.length; ++i)
			sb.append("\t\t")
					.append(meth[i].toString()
							.replace("public", "")
							.replace("java.lang.String", "string")
							.replace("boolean", "bool")
							.replace(className + ".", ""))
					.append(";\n");
		sb.append("};");
		return sb.toString();
	}

}
