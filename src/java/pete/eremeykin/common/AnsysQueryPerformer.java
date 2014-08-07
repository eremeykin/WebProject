/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Pete
 */
//Добавить обработку ошибок когда например не создана директория или не считана настройка из xml
// ошибки работы с файлами
public class AnsysQueryPerformer {


    public void runQuery(String query, String userDirName, String projectName) throws IOException, FileNotFoundException, SAXException, ParserConfigurationException, InterruptedException {
        String queryFilePath = queryToFile(query, userDirName, projectName);
        ProcessBuilder pb = new ProcessBuilder("cmd");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedOutputStream out = new BufferedOutputStream(p.getOutputStream());
        String command = "\"" + ConfigLoader.ANSYS_DIR + "\"";
        String setDir = "-dir " + ConfigLoader.WORKING_DIR + "\\" + userDirName + "\\" + projectName;
        String setJobName = "-j " + projectName;
        String setInputFile = "-i " + "\"" + queryFilePath + "\"";
        String setOutputFile = "-o " + "\"" + ConfigLoader.WORKING_DIR + "\\" + userDirName + "\\" + projectName + "\\output.txt" + "\"";
        command = command + " " + setDir + " " + setInputFile + " " + setOutputFile + " " + setJobName + " -b list " + "\n";
        out.write(command.getBytes());
        out.flush();
    }

    public String queryToFile(String query, String userName, String projectName) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {
        String workingDir = ConfigLoader.WORKING_DIR;
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
