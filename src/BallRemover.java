public class BallRemover implements HitListener{

    Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter remainingBalls){
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block  b ,Ball hitter) {
        System.out.println("Ball removed");
        this.game.removeBall(hitter);
        this.remainingBalls.decrease(1);

    }
}
