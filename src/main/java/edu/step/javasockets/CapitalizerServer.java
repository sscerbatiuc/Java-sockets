package edu.step.javasockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sscerbatiuc
 */
public class CapitalizerServer {

    private ServerSocket server;

    public CapitalizerServer(int portnum) {
        try {
            server = new ServerSocket(portnum);
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    public void serve() {
        try {
            while (true) {
                System.out.println("Java capitalization server. Waiting for client to connect...");
                Socket client = server.accept();

                BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                writer.println("Type something or 'exit' to quit.");
                String message;
                do {
                    System.out.println("Wating for the message...");
                    message = clientReader.readLine();
                    
                    if (message != null) {
                        System.out.println("Got: " + message + ". Sending: " + message.toUpperCase());
                        writer.println("Response: " + message.toUpperCase());
                    }
                } while (!message.equals("exit"));
                clientReader.close();
                writer.close();
                client.close();
            }
        } catch (Exception err) {
            System.err.println(err);
        }
    }

    public static void main(String[] args) {
        CapitalizerServer s = new CapitalizerServer(9999);
        s.serve();
    }
}
