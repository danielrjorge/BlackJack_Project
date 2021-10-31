import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class PlayHand implements Runnable {
    Player player;
    Game game;
    MenuInputScanner menu;
    String[] options, secondOption;

    public PlayHand(Player player, Game game) {
        this.player = player;
        this.game = game;
        options = new String[]{"Hit", "Stand", "Double"};
        secondOption = new String[]{"Hit", "Stand"};
        menu = new MenuInputScanner(options);
    }

    @Override
    public void run() {
        displayMenu();
        int choice = chooseOption();
        switch (choice) {
            case 1:
                game.hit(player);
                break;
            case 2:
                game.stay(player);
                break;
            case 3:
                //as we removed chips when betting, now you only need to check if it is lower than another equal bet (bet * 2)
                if (player.getChips() < player.getBet()) {
                    player.getPrintStream().println("You don't have enough chips for double");
                    break;
                }
                game.doubleHit(player);
                break;
            default:
                break;
        }

        //change ace value to 1 and recalculate points
        for(Card card: player.getPlayerHand()){
            if(card.getCardName() == CardNames.ACE && player.getPoints() > 21){
                card.setCardPoints(1);
                break;
            }
        }

        player.setPoints(player.calculateHandPoints());


        if (player.getPoints() > game.MAXPOINTS) {
            player.setBust();
            player.getPrintStream().println("You are bust! Wait for next round");
        }

        while (!player.isHasStood() && !player.isBust()) {
            int secondChoice = chooseSecondOption();
            switch (secondChoice) {
                case 1:
                    game.hit(player);
                    break;
                case 2:
                    game.stay(player);
                    break;
                default:
                    break;
            }

            for(Card card: player.getPlayerHand()){
                if(card.getCardName() == CardNames.ACE && player.getPoints() > 21){
                    card.setCardPoints(1);
                }
            }

            if (player.getPoints() > game.MAXPOINTS) {
                player.setBust();
                player.getPrintStream().println("You are bust! Wait for next round");
            }
        }

    }

        public void displayMenu () {
            menu.setMessage("Choose your play");
        }

        public int chooseOption (){
            menu = new MenuInputScanner(options);
            int choose = player.getPrompt().getUserInput(menu);
            System.out.println("User wants to " + options[choose - 1]);
            return choose;
        }

        public int chooseSecondOption(){
            menu = new MenuInputScanner(secondOption);
            int choose = player.getPrompt().getUserInput(menu);
            System.out.println("User wants to " + secondOption[choose - 1]);
            return choose;
        }


    }
