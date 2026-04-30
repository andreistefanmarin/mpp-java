package eu.ase.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastUDPClient {
    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(Integer.parseInt(args[0]));
            InetAddress address = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(address);
            DatagramPacket packet;
            for(int i=0; i < 5 ; i++) {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received from server (multicast):" + received);
            }
            socket.leaveGroup(address);
            socket.close();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }


    }
}
