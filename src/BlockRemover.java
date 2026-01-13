

public class BlockRemover implements HitListener{
    GameLevel game;
    private Counter remainingBlocks;

    public BlockRemover(GameLevel game, Counter remainingBlocks){
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
