public class PrintHitLIstener implements HitListener {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println(beingHit);
    }


}
