/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Pete
 */
public class AnsysQueryPerformer {

    private static String ansysDir;

    public AnsysQueryPerformer() throws ParserConfigurationException, SAXException, IOException {
        ansysDir = Utils.getSetting("Ansys_dir", "path");

    }

    public String runQuery(StringBuffer query) throws IOException, ParserConfigurationException, SAXException {
        dirictoryPrep("test");
        ProcessBuilder pb = new ProcessBuilder("cmd");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(), "cp866"));
        BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream(), "cp866"));
        BufferedOutputStream out = new BufferedOutputStream(p.getOutputStream());
//        String line = "c:\n" +"\"c:\\Program Files\\ANSYS Inc\\v150\\ansys\\bin\\winx64\\ansys150.exe\"";
        String command = "\""+ansysDir+"\"";
        command += "\n";
        out.write(command.getBytes());
        out.flush();
        String result = "ok!";
        return result;

    }

    private String dirictoryPrep(String name) throws SAXException, IOException, ParserConfigurationException {
        String dirName = Utils.getSetting("Working_dir", "path") + "\\" + name;
        File dir = new File(dirName);
        dir.mkdirs();
        return dirName;
    }

}
