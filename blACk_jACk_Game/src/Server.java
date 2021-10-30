import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int port = 42069;
    private Prompt prompt;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private LinkedList<Player> list;
    private PrintStream printStream;
    private Game game;
    private ExecutorService multipleClients;

    public Server() {
        System.out.println("Binding to port " + port);

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);
            this.multipleClients = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        game = new Game(list, prompt);
        list = new LinkedList<>();
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


            Player clientConnection = new Player(clientSocket, this);
            list.add(clientConnection);
            multipleClients.submit(clientConnection);
            game.startGame();


        }

    }

    public void broadcast() {

        for (int i = 0; i < list.size(); i++) {
            StringInputScanner stringBroadcast = new StringInputScanner();
            stringBroadcast.setMessage(list.get(i).getName() + " joined the lobby!");
            stringBroadcast.show(printStream);
            System.out.println(Thread.currentThread().getName());
        }
    }

    public Game getGame() {
        return game;
    }
}
