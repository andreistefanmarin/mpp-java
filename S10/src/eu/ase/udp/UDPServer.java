package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer {
    public static void main(String[] args) {
        byte[] bRecv = null;
        byte[] bResponse = null;

        try(DatagramSocket socket = new DatagramSocket(7778)) {
            System.out.println("Mu UDP Server is binding in port 7778");
            while(true) {
                bRecv = new byte[256];
                DatagramPacket packet = new DatagramPacket(bRecv, bRecv.length);
                socket.receive(packet);
                System.out.println("UDP Client: " + packet.getAddress() + ":" + packet.getPort() + " sent to server: " +
                        new String(packet.getData()).trim());
                String respS = null;
                if("What date and time is it?".equals(new String(packet.getData()).trim())) {
                    respS = new Date().toString();
                } else {
                    respS = "I don't understand!";
                }
                bResponse = respS.getBytes();

                InetAddress addressSender = packet.getAddress();
                int portSender = packet.getPort();

                DatagramPacket respPacket = new DatagramPacket(bResponse, bResponse.length, addressSender, portSender);
                socket.send(respPacket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
