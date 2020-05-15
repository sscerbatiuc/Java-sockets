package edu.step.javasockets.multiple;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author sscerbatiuc
 */
public class MulticastSender {

    public static void main(String[] args) throws IOException {
        InetAddress grup = InetAddress.getByName("230.0.0.1");
        int port = 4444;
        byte[] buf;
        DatagramPacket packet = null;
        // Cream un socket cu un numar oarecare
        try (DatagramSocket socket = new DatagramSocket(0)) {
            // Trimitem un pachet catre toti clientii din grup
            buf = (" Hi group !").getBytes();
            packet = new DatagramPacket(buf, buf.length, grup, port);
            socket.send(packet);
        }
    }
}
