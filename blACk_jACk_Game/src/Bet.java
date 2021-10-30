public class Bet implements Runnable{
    private Player player;
    public Bet(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        System.out.println(player.getSocket());
        int chipsBet;

        player.getScanner().setMessage("Place your bet - Total chips available -> " + player.getChips());

        while (true) {
            chipsBet = player.getPrompt().getUserInput(player.getScanner());

            if (chipsBet > player.getChips()) {
                player.getScanner().setMessage("Not enough chips for this bet, try again");
            } else {
                break;
            }
        }

        player.setBet(chipsBet);
        player.setChips(player.getChips() - chipsBet);


    }
}
