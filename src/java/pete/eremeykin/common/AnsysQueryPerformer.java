/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Pete
 */
//Добавить обработку ошибок когда например не создана директория или не считана настройка из xml
// ошибки работы с файлами
public class AnsysQueryPerformer {

    private static String ansysDir;

    public AnsysQueryPerformer() throws ParserConfigurationException, SAXException, IOException {
            ansysDir = Utils.getSetting("Ansys_dir", "path");
    }

    public String runQuery(String query, String userDirName, String projectName) throws IOException, FileNotFoundException, SAXException, ParserConfigurationException, InterruptedException {
        String queryFilePath = queryToFile(query, userDirName, projectName);
        ProcessBuilder pb = new ProcessBuilder("cmd");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedOutputStream out = new BufferedOutputStream(p.getOutputStream());
        String command = "\"" + ansysDir + "\"";
        String setDir = "-dir " + Utils.getSetting("Working_dir", "path") + "\\" + userDirName + "\\" + projectName;
        String setJobName = "-j " + projectName;
        String setInputFile = "-i " + "\"" + queryFilePath + "\"";
        String setOutputFile = "-o " + "\"" + Utils.getSetting("Working_dir", "path") + "\\" + userDirName + "\\" + projectName + "\\output.txt" + "\"";
        command = command + " " + setDir + " " + setInputFile + " " + setOutputFile + " " + setJobName + " -b list " + "\n";
        out.write(command.getBytes());
        out.flush();
        String result = "ok!";
        return result;
    }

    public String queryToFile(String query, String userName, String projectName) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        String workingDir = Utils.getSetting("Working_dir", "path");
        String dirName = workingDir + "\\" + userName + "\\" + projectName;
        File dir = new File(dirName);
        dir.mkdirs();
        FileOutputStream fout = null;
        File queryFile = new File(dirName + "\\query.mac");

        try { // Попытка открыть файлы. 
            fout = new FileOutputStream(queryFile);
            fout.write(query.toString().getBytes());
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
        return queryFile.getAbsolutePath();
    }

}
