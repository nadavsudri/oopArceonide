

public class BlockRemover implements HitListener{
    Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter remainingBlocks){
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block  b ,Ball hitter) {

        this.game.removeSprite(b);
        this.game.removeCollidable(b);
        this.remainingBlocks.decrease(1);

    }
}
