import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket socket;

    private Prompt prompt;
    private PrintStream printStream;

    public Client(){

    }

    public Client(Socket socket) {
        this.socket = socket;
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
        String name = prompt.getUserInput(question1);
        question1.setMessage("\nHello " + name);
        question1.show(printStream);


    }

    public Socket getSocket() {
        return socket;
    }
}
