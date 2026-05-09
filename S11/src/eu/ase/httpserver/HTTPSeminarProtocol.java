package eu.ase.httpserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HTTPSeminarProtocol {
    public String processInput(String theInput) {
        String theOutput = "";
        byte[] buffResp = new byte[4096];

        //GET /indextesr.html HTTP/1.1

        if(theInput.indexOf("GET") != 0) {
            theOutput = "HTTP/1.1 200 OK\r\nContent-Length: 10 \r\n NU STIU COMANDA \r\n\r\n";
        } else {
            String fileName = theInput.substring(theInput.indexOf("/")+1, theInput.indexOf(" HTTP/"));
            String fileExt = fileName.substring(fileName.indexOf(".") + 1);

            String contentType = "";
            String fileContent = "";

            if(fileExt.compareToIgnoreCase("html") == 0) {
                contentType = "text/html";
            } else if (fileExt.compareToIgnoreCase("txt") == 0) {
                contentType = "text/html";
            } else if (fileExt.compareToIgnoreCase("gif") == 0) {
                contentType = "image/gif";
            }

            try {
                FileInputStream fis = new FileInputStream(fileName);
                int bRead = 0;

                while((bRead = fis.read(buffResp)) != -1) {
                    fileContent += new String(buffResp, 0, bRead);
                }

                fis.close();

                theOutput = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "Content-Length"
                        + (fileContent.length())+ "\r\n\r\n" + fileContent + "\r\n";

            } catch (IOException e) {
                e.printStackTrace();
                theOutput = "HTTP/1.1 400\r\n\r\n";
            }

        }

        return theOutput;
    }
}
