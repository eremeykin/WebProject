/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pete.eremeykin.common;

/**
 *
 * @author Pete
 */
public class HTMLHelper {

    public static String printRow(Object... data) {
        return printRow(false, data);
    }

    public static String printHeader(Object... data) {
        return printRow(true, data);
    }
    
    public static String printError(Object ... data){
        return printRow(null,data);
    }

    protected static String printRow(Boolean isHeader, Object... data) {
        StringBuffer buffer = new StringBuffer("<tr bgcolor=\"" + ((isHeader == null) ? "FF4444" : (isHeader ? "00FF00" : "FFFFFF")) + "\"tr>");
        if (data != null) {
            for (Object object : data) {
                buffer.append("<td>");
                buffer.append(object);
                buffer.append("</td>");
            }
        }
        return buffer.append("</tr>").toString();
    }
}
