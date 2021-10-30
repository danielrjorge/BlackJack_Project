import org.academiadecodigo.bootcamp.Prompt;

import java.net.Socket;
import java.util.LinkedList;

public class MainTests {

    public static void main(String[] args) {
        Player player1 = new Player(), player2 = new Player(), player3 = new Player();
        Prompt prompt = new Prompt(System.in, System.out);

        LinkedList<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        Game game = new Game(players, prompt);

        game.distributeHands();

    }
}
