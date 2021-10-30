import org.academiadecodigo.bootcamp.Prompt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {

    public static final int port = 42069;
    private Prompt prompt;
    private PrintStream printStream;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private LinkedList<Client> list;

    public Server() {

        System.out.println("Binding to port " + port);

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        list = new LinkedList<>();
        while (true) {
            // block waiting for a client to connect
            System.out.println("Waiting for a client connection");
            try {
                clientSocket = serverSocket.accept();
                System.out.println("New client connection, socket: " + clientSocket.getInetAddress());

            } catch (IOException e) {
                e.printStackTrace();
            }

            list.add(new Client(clientSocket));

            Client clientConnection = new Client(clientSocket);

            Thread clientThread = new Thread(clientConnection);
            clientThread.start();

        }

    }


}
