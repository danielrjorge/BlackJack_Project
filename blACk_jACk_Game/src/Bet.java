public class Bet implements Runnable {
    private Player player;

    public Bet(Player player) {
        this.player = player;
    }

    public Bet() {

    }

    @Override
    public void run() {
        int chipsBet;

        player.getScanner().setMessage("Place your bet - Total chips available -> " + player.getChips() + "\n");

        while (true) {
            chipsBet = player.getPrompt().getUserInput(player.getScanner());

            if (chipsBet > player.getChips()) {
                player.getScanner().setMessage("Not enough chips for this bet, try again \n");
            } else {
                break;
            }
        }
        player.getPrintStream().println("Waiting for other players...");

        player.setBet(chipsBet);
        player.setChips(player.getChips() - chipsBet);


    }
}
