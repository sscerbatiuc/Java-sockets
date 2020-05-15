package edu.step.javasockets.simple;

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
public class Server {
    // Definim portul pe care se gaseste serverul
    // (in afara intervalului 1 -1024)

    public static final int PORT = 8100;

    public Server() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                System.out.println(" Asteptam un client ... ");
                Socket socket = serverSocket.accept();
                // Executam solicitarea clientului intr -un fir de executie 
                ClientThread t = new ClientThread(socket);
                t.start();
            }
        } catch (IOException e) {
            System.err.println(" Eroare IO \n" + e);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }

}

class ClientThread extends Thread {

    Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        // Executam solicitarea clientului
        String cerere, raspuns;
        try {
            // in este fluxul de intrare de la client
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            // out este flux de iesire catre client
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream());
            
            // Primim cerere de la client
            cerere = in.readLine();
            
            // Trimitem raspuns clientului
            raspuns = " Hello " + cerere + "!";
            out.println(raspuns);
            out.flush();
        } catch (IOException e) {
            System.err.println(" Eroare IO \n" + e);
        } finally {
            
            // Inchidem socketul deschis pentru clientul curent
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(" Socketul nu poate fi inchis \n" + e);
            }
        }
    }
}
