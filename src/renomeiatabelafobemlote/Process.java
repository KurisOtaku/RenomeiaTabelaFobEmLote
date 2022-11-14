/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renomeiatabelafobemlote;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristiano.rosa
 */
public class Process {

    /*
    ===================================
                FOB TO TXT
    ===================================
     */
    
    public static String fob_to_txt(String arg) throws Exception {
        String logmessage = "";
        String pathfolder = arg;
        if(!pathfolder.endsWith("\\")){
            pathfolder += "\\";
        }
        List<String> files = getFilesList(pathfolder);
        for (String file : files) {
            if (file.contains(".xls")) {
                String renameto = file.substring(2, 21) + ".txt";
                if (renomear(pathfolder, file, renameto)) {
                    System.out.println(file + " => " + renameto);
                    logmessage += file + " => " + renameto + "\n";
                } else {
                    System.out.println("Erro => " + file);
                    logmessage += "Erro => " + file + "\n";
                }
            }
        }
        return logmessage;
    }

    private static boolean renomear(String pathfile, String oldname, String newname) throws IOException {
        // File (or directory) with old name
        File file = new File(pathfile + oldname);

        // File (or directory) with new name
        File file2 = new File(pathfile + newname);

        if (file2.exists()) {
            throw new java.io.IOException("file exists");
        }

        // Rename file (or directory)
        boolean success = file.renameTo(file2);
        return success;
    }

    public static List<String> getFilesList(String directory) throws Exception {
        List<String> results = new ArrayList<String>();
        File[] files = new File(directory).listFiles();
//If this pathname does not denote a directory, then listFiles() returns null. 
        if (files == null) {
            throw new Exception("Erro ao Buscar lista de arquivos: " + directory);
        }
        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

}
