package eu.ase.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        udpClient(args);
    }

    public static void udpClient(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] buf = "What date and time is it?".getBytes();
            InetAddress dstAddress = InetAddress.getByName(args[0]);
            int dstPort = Integer.parseInt(args[1]);

            DatagramPacket packet = new DatagramPacket(buf, buf.length, dstAddress, dstPort);
            clientSocket.send(packet);

            byte[] bufResponse = new byte[256];
            DatagramPacket packetReceive = new DatagramPacket(bufResponse, bufResponse.length);
            clientSocket.receive(packetReceive);

            System.out.println("Client received from server = " + new String(packetReceive.getData()));
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
