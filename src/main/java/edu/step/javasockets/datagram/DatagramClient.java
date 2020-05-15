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
public class DatagramClient {

    public static void main(String[] args) throws IOException {
        // Adresa IP si portul la care ruleaza serverul
        InetAddress adresa = InetAddress.getByName("127.0.0.1");
        int port = 8200;
        DatagramSocket communicationSocket = null;
        DatagramPacket packet;
        byte buf[];
        try {
            // Construim un socket pentru comunicare
            communicationSocket = new DatagramSocket();
            // Construim si trimitem pachetul cu cererea catre server
            buf = " Duke ".getBytes();
            packet = new DatagramPacket(buf, buf.length, adresa, port);
            communicationSocket.send(packet);
            // Asteaptam pachetul cu raspunsul de la server
            buf = new byte[50];
            packet = new DatagramPacket(buf, buf.length);
            communicationSocket.receive(packet);
            // Afisam raspunsul (" Hello Duke !")
            System.out.println(new String(packet.getData()));
        } finally {
            if (communicationSocket != null) {
                communicationSocket.close();
            }
        }
    }
}
