import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
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
    private LinkedList<Player> allClients;
    private ExecutorService multipleClients;
    private ExecutorService multipleGames;
    private final int maxClients = 6;



    public Server() {
        System.out.println("Binding to port " + port);

        try {
            allClients = new LinkedList<>();
            serverSocket = new ServerSocket(port);
            System.out.println("Server started: " + serverSocket);
            this.multipleClients = Executors.newCachedThreadPool();
            this.multipleGames = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() throws InterruptedException {
        while (allClients.size() < maxClients) {
            list = new LinkedList<>();
            game = new Game(list);
            while (list.size() < 2) {
                // block waiting for a client to connect
                System.out.println("Waiting for a client connection");
                try {
                    clientSocket = serverSocket.accept();
                    Player clientConnection = new Player(clientSocket, this);
                    allClients.add(clientConnection);
                    multipleClients.submit(clientConnection);
                    printStream = new PrintStream(clientSocket.getOutputStream());
                    prompt = new Prompt(clientSocket.getInputStream(), printStream);
                    System.out.println("New client connection, socket: " + clientSocket.getPort());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        try {
            clientSocket = serverSocket.accept();
            printStream = new PrintStream(clientSocket.getOutputStream());
            prompt = new Prompt(clientSocket.getInputStream(), printStream);
            System.out.println("New client connection, socket: " + clientSocket.getPort());
            printStream.println("Server full!");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public LinkedList<Player> getList() {
        return list;
    }

    public LinkedList<Player> getAllClients() {
        return allClients;
    }

    public void startGame(){
        System.out.println(list.size());
        game.setPlayers(list);
        GameLobby gameLobby = new GameLobby(game);
        multipleGames.submit(gameLobby);
    }
}