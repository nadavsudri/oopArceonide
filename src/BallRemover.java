public class BallRemover implements HitListener{

    GameLevel game;
    private Counter remainingBalls;

    public BallRemover(GameLevel game, Counter remainingBalls){
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
