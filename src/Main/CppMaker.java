/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.HppMaker.hppFormating;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jean
 */
public class CppMaker {

    public static void CppMaker(String fileName, String className, Class c) {
        File f = new File(fileName + ".cpp");
        try {
            FileWriter writer = new FileWriter(f, false);
            writer.write(cppFormating(fileName, className, c));
            writer.flush();
            writer.close();
        } catch (IOException ex2) {
            Logger.getLogger(Tp8.class.getName()).log(Level.SEVERE, null, ex2);
        }
    }

    public static String cppFormating(String fileName, String className, Class c) {
        StringBuilder sb = new StringBuilder();
        Method[] meth = c.getDeclaredMethods();
        sb.append("#include \"").append(className).append(".hpp\"\n\n");
        for (int i = 0; i < meth.length; ++i) {
            sb.append(meth[i].toString()
                    .replace(className+".",className +"::")
                    .replace("public", "")
                    .replace("java.lang.String", "string"))
            .append("\n{\n}\n")
            .append("\n");
        }

        return sb.toString();

    }

}
