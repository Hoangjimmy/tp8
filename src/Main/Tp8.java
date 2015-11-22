/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author hoangjim
 */
public class Tp8 {

    public static void main(String[] args) {

        if (args.length < 1 || args.length > 3) {
            System.out.println("Usage : tp8 <--stdout> <JavaClassName> <GeneratedC++ClassName>");
        } else {
            try {
                HppMaker h = new HppMaker();
                CppMaker cpp = new CppMaker();
                Class c;
                if (args.length < 2) {

                    c = Class.forName(args[0]);
                    h.hppMaker(args[0], c.getName(), c);
                    cpp.CppMaker(args[0],c.getName(),c);
                    System.out.println(h.hppFormating(c.getName(), c.getName(), c));

                } else if (args.length < 3) {

                    c = Class.forName(args[0]);
                    h.hppMaker(args[1], c.getName(), c);
                    cpp.CppMaker(args[1], c.getName(), c);
                } else if (args.length < 4 && args[0].compareTo("--stdout") == 0) {

                    c = Class.forName(args[1]);
                    System.out.println(args[1]);
                    h.hppMaker(args[2], c.getName(), c);
                    System.out.println(h.hppFormating(args[2], c.getName(), c));

                }
            } catch (ClassNotFoundException ex) {
                System.err.println("Class not found");
            }
        }

    }

}
