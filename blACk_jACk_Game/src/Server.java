import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server {

    public static final int port = 42069;
    private Prompt prompt;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private List<Client> list;
    private PrintStream printStream;

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
        list = Collections.synchronizedList(new LinkedList<>());
        while (true) {
            // block waiting for a client to connect
            System.out.println("Waiting for a client connection");
            try {
                clientSocket = serverSocket.accept();
                printStream = new PrintStream(clientSocket.getOutputStream());
                prompt = new Prompt(clientSocket.getInputStream(), printStream);
                System.out.println("New client connection, socket: " + clientSocket.getPort());

            } catch (IOException e) {
                e.printStackTrace();
            }

            list.add(new Client(clientSocket, this));

            Client clientConnection = new Client(clientSocket, this);

            Thread clientThread = new Thread(clientConnection);
            clientThread.start();


        }

    }

    public void broadcast() {

        for (Client test : list) {
            StringInputScanner stringBroadcast = new StringInputScanner();
            stringBroadcast.setMessage(test.getName() + " joined the lobby!");
            stringBroadcast.show(printStream);
        }
    }


}
