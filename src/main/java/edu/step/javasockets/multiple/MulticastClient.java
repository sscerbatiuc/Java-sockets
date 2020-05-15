/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.step.javasockets.multiple;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author sscerbatiuc
 */
public class MulticastClient {

    public static void main(String[] args) throws IOException {
// Adresa IP si portul care reprezinta grupul de clienti
        InetAddress group = InetAddress.getByName("230.0.0.1");
        int port = 4444;
        MulticastSocket socket = null;
        byte buf[];
        try {
            // Ne alaturam grupului aflat la adresa si portul specificate 
            socket = new MulticastSocket(port);
            socket.joinGroup(group);
            // Asteaptam un pachet venit pe adresa grupului
            buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            System.out.println(" Asteptam un pachet ... ");
            socket.receive(packet);
            System.out.println(new String(packet.getData()).trim());
        } finally {
            if (socket != null) {
                socket.leaveGroup(group);
                socket.close();
            }
        }
    }
}
