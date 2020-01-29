/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.step.javasockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author sscerbatiuc
 */
public class SocketClient {

    public static void main(String[] args) {
        try {
            System.out.println("Client: connecting to localhost:9999");
            Socket serverSocket = new Socket("127.0.0.1", 9999);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String line;
            do {
                line = serverReader.readLine();
                if (line != null) {
                    System.out.println(line);
                }
                line = console.readLine();
                writer.println(line);
            } while (!line.equals("exit"));

            serverReader.close();
            writer.close();
            serverSocket.close();
        } catch (Exception err) {
            System.err.println(err);
        }
    }
}
