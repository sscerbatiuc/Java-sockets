/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.step.javasockets.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author sscerbatiuc
 */
public class DatagramServer {

    public static final int PORT = 8200;
    private DatagramSocket socket = null;
    DatagramPacket request, response = null;

    public void start() throws IOException {
        socket = new DatagramSocket(PORT);
        try {
            while (true) {
                // Declaram pachetul in care va fi receptionata cererea
                byte[] buf = new byte[50];
                request = new DatagramPacket(buf, buf.length);
                System.out.println(" Asteptam un pachet ... ");
                socket.receive(request);
                // Aflam adresa si portul de la care vine cererea
                InetAddress adress = request.getAddress();
                int port = request.getPort();
                // Construim raspunsul
                String mesaj = " Hello " + new String(request.getData()
                );
                buf = mesaj.getBytes();
                // Trimitem un pachet cu raspunsul catre client
                response = new DatagramPacket(buf, buf.length, adress, port);
                socket.send(response);
            }
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new DatagramServer().start();
    }
}
