package com.sanjay.cu;

import com.sanjay.util.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zsj_09@hotmail.com on 2014/12/30.
 */
public class CUSrc {

    /**
     * The file is generated by UCDector
     *
     * @return
     */
    public static String clear0Reference(File f) {
        List<String> list = null;
        try {
            list = FileTools.readLines(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        String line;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            line = (String) it.next();

            if (line.contains("Class") && line.contains("has 0 reference") && !line.contains("Method")) {
                int end = line.indexOf(".<init>");
                if (end != -1) {
                    sb.append(line.substring(0, end));
                    sb.append("\r\n");
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("none args!");
            System.exit(2);
        }

        File f = new File(args[0]);
        String s = clear0Reference(f);
        if (s != null && !s.equals("")) {
            String out = new StringBuilder().append(f.getParent()).append(File.separator).append("out.txt").toString();
            FileTools.saveToFile(out, s);
            System.out.println("Success! File path is" + out);
        }
    }
}
