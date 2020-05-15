/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.step.javasockets.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author sscerbatiuc
 */
public class Client {

    public static void main(String[] args) throws IOException {
        // Adresa IP a serverului
        String adresaServer = "127.0.0.1";
        // Portul la care serverul ofera serviciul
        int PORT = 8100;
        Socket socket = null;
        PrintWriter outChannel = null;
        BufferedReader inChannel = null;
        String request, response;
        try {
            socket = new Socket(adresaServer, PORT);

            outChannel=  new PrintWriter(socket.getOutputStream(), true);
            inChannel = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Trimitem o cerere
            request = " John";
            outChannel.println(request);
            
            // Asteaptam raspunsul de la server (" Hello Duke !")
            response = inChannel.readLine();
            System.out.println(response);
        } catch (UnknownHostException e) {
            System.err.println(" Serverul nu poate fi gasit \n" + e);
            System.exit(1);
        } finally {
            if (outChannel != null) {
                outChannel.close();
            }
            if (inChannel != null) {
                inChannel.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}
