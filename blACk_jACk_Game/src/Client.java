import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private Prompt prompt;
    private PrintStream printStream;
    private String name;
    private Server server;


    public Client(Socket socket, Server server) {

        this.socket = socket;
        this.server = server;

        try {
            this.printStream = new PrintStream(socket.getOutputStream());
            prompt = new Prompt(socket.getInputStream(), printStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("What is your name?\n");
        this.name = prompt.getUserInput(question1);
        question1.setMessage("\nHello " + this.name + "\n");
        question1.show(printStream);
        server.broadcast();

    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }
}
