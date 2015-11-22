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
 * @author Jean
 */
public class HppMaker {

    public static void hppMaker(String fileName, String className, Class c) {

        File f = new File(fileName + ".hpp");
        try {
            FileWriter writer = new FileWriter(f, false);
            writer.write(hppFormating(fileName, className, c));
            writer.flush();
            writer.close();
        } catch (IOException ex2) {
            Logger.getLogger(Tp8.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }

    public static String hppFormating(String fileName, String className, Class c) {

        StringBuilder sb = new StringBuilder();
        String s = "_" + fileName.toUpperCase() + "_HPP_";
        Field[] fi = c.getDeclaredFields();
        Method[] meth = c.getDeclaredMethods();
        String[] splitString;

        sb = sb.append("#ifndef ").append(s).append("\n")
                .append("#define ").append(s).append("\n")
                .append("#include <iostream>\n")
                .append("class ").append(fileName)
                .append(" { \n")
                .append("\tprivate : \n");

        for (int i = 0; i < fi.length; ++i) {
            sb.append("\t\t")
                    .append(fi[i].toString()
                            .replace("private", "")
                            .replace("boolean", "bool")
                            .replace("java.lang.String", "string")
                            .replace(className + ".", ""))
                    .append(";\n");

        }

        sb.append(
                "\tpublic : \n");
        for (int i = 0; i < meth.length; ++i) {
            splitString = meth[i].toString().split(".");
            if (splitString.length > 1) {
                sb.append(splitString[(splitString.length - 1)]);
            } else {
                sb.append("\t\t")
                        .append(meth[i].toString()
                                .replace("public", "")
                                .replace("java.lang.String", "string")
                                .replace("boolean", "bool")
                                .replace(className + ".", ""))
                        .append(";\n");
            }
        }

        sb.append(
                "}; \n#endif");
        return sb.toString();
    }

}
