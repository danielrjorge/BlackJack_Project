public class PlayHand implements Runnable {
    Player player;

    public PlayHand(Player player) {
        this.player = player;
    }

    @Override
    public void run() {

        System.out.println(player.getName() + " has played");

    }
}
