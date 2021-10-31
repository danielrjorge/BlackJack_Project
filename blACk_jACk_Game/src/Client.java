import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Client implements Runnable {

    private Socket socket;
    private Prompt prompt;
    private PrintStream printStream;
    private String name;
    private Server server;
    private IntegerInputScanner scanner;
    private int playAlone;

    public Client(Socket socket, Server server) {
        try {
            this.scanner = new IntegerInputScanner();
            this.socket = socket;
            this.server = server;
            this.playAlone = -1;
            this.printStream = new PrintStream(socket.getOutputStream());
            prompt = new Prompt(socket.getInputStream(), printStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        greetPlayer(); //asks player name and greets him/her
        if (prompt.getUserInput(askPlayAlone()).equals("yes")) {  //asks if player wants to play alone
            startAloneGame();  //start game alone
        } else {
            playAlone = 0;
        }
    }

    public String getName() {
        return this.name;
    }

    public Socket getSocket() {
        return socket;
    }

    public Prompt getPrompt() {
        return prompt;
    }

    public IntegerInputScanner getScanner() {
        return scanner;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public int isPlayAlone() {
        return playAlone;

    }

    private void startAloneGame() {
        playAlone = 1;
        server.getList().remove(this);
        LinkedList<Player> solo = new LinkedList<>();
        solo.add((Player) this);
        new Game(solo).startGame();
    }

    private StringSetInputScanner askPlayAlone() {
        Set<String> options = new HashSet<>();
        options.add("yes");
        options.add("no");
        StringSetInputScanner wantsToPlayAlone = new StringSetInputScanner(options);
        wantsToPlayAlone.setMessage("Do you wish to play alone?\n");
        return wantsToPlayAlone;
    }

    private void greetPlayer() {
        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("What is your name?\n");
        this.name = prompt.getUserInput(question1);
        question1.setMessage("\nHello " + this.name + "\n");
        question1.show(printStream);
    }

}
