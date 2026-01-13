import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private KeyboardSensor keyboard;
    private int speed; // New field for dynamic speed

    public Paddle(Rectangle rect, KeyboardSensor keyboard) {
        this.rect = rect;
        this.keyboard = keyboard;
        this.speed = 5; // Default speed
    }

    /**
     * Sets the speed of the paddle for the current level.
     * @param s the speed value from LevelInformation.
     */
    public void setSpeed(int s) {
        this.speed = s;
    }

    public void moveLeft() {
        double newX = rect.getUpperLeft().getX() - speed;
        // Ensure paddle stays within the left border (thick = 20)
        if (newX >= 20) {
            this.rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
        }
    }

    public void moveRight() {
        double newX = rect.getUpperLeft().getX() + speed;
        // Ensure paddle stays within the right border (800 - 20)
        if (newX + rect.getWidth() <= 780) {
            this.rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Simple bounce logic: reverse horizontal velocity if hit on the side, vertical if on top
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }
}