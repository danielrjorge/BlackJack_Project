import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintStream;

public class Bet implements Runnable {
    private Player player;
    private Game game;
    private MenuInputScanner menu;
    private String[] options;

    public Bet(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Bet() {

    }

    @Override
    public void run() {
        int chipsBet;

        try {
        player.getScanner().setMessage("Place your bet - Total chips available -> " + player.getChips() + "\n");
        options = new String[]{"5", "25", "50", "100", "200", "to quit\n"};
        menu = new MenuInputScanner(options);
            menu.show(new PrintStream(player.getSocket().getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        while (true) {
            chipsBet = player.getPrompt().getUserInput(player.getScanner());

            if (menuCases(chipsBet) == -1){
                game.getPlayers().remove(player);
                return;
            }else if(menuCases(chipsBet) == -50){
                while (menuCases(chipsBet) == -50){
                    chipsBet = player.getPrompt().getUserInput(player.getScanner());
                }

            }
            chipsBet = menuCases(chipsBet);
            player.getPrintStream().println("You bet -> " + chipsBet+ "\n");

            if (chipsBet > player.getChips()) {
                player.getScanner().setMessage("Not enough chips for this bet, try again \n");
            } else {
                break;
            }
        }

        player.setBet(chipsBet);
        player.setChips(player.getChips() - chipsBet);


    }

    public int menuCases(int choice){
        switch (choice){
            case 1:
                return 5;
            case 2:
                return 25;
            case 3:
                return 50;
            case 4:
                return 100;
            case 5:
                return 200;
            case 6:
                return -1;
            default:
                player.getScanner().setMessage("\nNot valid\n");
                return -50;

        }

    }
}
