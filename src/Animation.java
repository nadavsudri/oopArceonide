import biuoop.DrawSurface;

/**
 * The Animation interface represents a single animation logic.
 */
public interface Animation {
    /**
     * Handles one frame of animation logic.
     * @param d the drawing surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Logic for stopping the animation.
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}