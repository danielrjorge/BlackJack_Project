import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Prompt prompt = new Prompt(System.in, System.out);

        StringInputScanner question1 = new StringInputScanner();

        question1.setMessage("What is your name?");
        String name = prompt.getUserInput(question1);
        System.out.println(name);
    }

    public Socket getSocket() {
        return socket;
    }
}
