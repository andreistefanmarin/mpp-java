package eu.ase.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPMultiServerThread extends Thread {
    private Socket socket;

    public HTTPMultiServerThread(Socket client) {
        this.socket = client;
    }

    @Override
    public void run() {
        InputStream is = null;
        BufferedReader in = null;
        OutputStream os = null;
        PrintWriter out = null;

        try {
            is = this.socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));

            os = this.socket.getOutputStream();
            out = new PrintWriter(os, true);

            String inputLine = "";
            String outputLine = "";
            String processLine = "";
            while ( ( (inputLine = in.readLine() ) != null) && (inputLine.length() > 1) ) {
                processLine += inputLine;
            }

            HTTPSeminarProtocol objProtocol = new HTTPSeminarProtocol();
            outputLine = objProtocol.processInput(processLine);

            out.println(outputLine);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                out.close();
            }
        }
    }
}
