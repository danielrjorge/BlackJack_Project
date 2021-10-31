import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class PlayHand implements Runnable {
    Player player;
    Game game;
    MenuInputScanner menu;
    String[] options;

    public PlayHand(Player player, Game game) {
        this.player = player;
        this.game = game;
        options = new String[]{"Hit", "Stand", "Double"};
        menu = new MenuInputScanner(options);
    }

    @Override
    public void run() {
        displayMenu();
        int choice = chooseOption(menu);
        switch (choice) {
            case 1:
                game.hit(player);
                break;
            case 2:
                game.stay(player);
                break;
            case 3:
                if (player.getChips() < player.getBet() * 2){
                    System.out.println("Not enough maney");
                    break;
                }
                game.doubleHit(player);
                break;
            default:
                break;
        }
    }

    public void displayMenu() {
        menu.setMessage("Choose your play");
    }

    public int chooseOption(MenuInputScanner menu) {
        int choose = player.getPrompt().getUserInput(menu);
        System.out.println("User wants to " + options[choose - 1]);
        return choose;

    }


}
