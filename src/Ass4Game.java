import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

public class Ass4Game {
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        GameFlow flow = new GameFlow(runner, gui.getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>();

        // logic for levels selection based on args or default
        levels.add(new Level1());
       // levels.add(new Level2());
        //levels.add(new Level3());
        levels.add(new Level4());

        flow.runLevels(levels);
    }
}