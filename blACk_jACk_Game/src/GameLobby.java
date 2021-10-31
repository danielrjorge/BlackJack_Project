public class GameLobby implements Runnable {

    private Game game;

    public GameLobby(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        try {
            game.startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}