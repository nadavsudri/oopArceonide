import biuoop.DrawSurface;

import java.util.ArrayList;

public class SpriteCollection {
    private ArrayList<Sprite> sprites;
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    public void setSprites(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    public void addSprite(Sprite s){
        this.sprites.add(s);
    }
    public void removeSprite(Sprite s){
        this.sprites.remove(s);
    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for (Sprite s : this.sprites) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}