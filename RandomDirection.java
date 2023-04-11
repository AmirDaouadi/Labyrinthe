import java.util.Random;

public class RandomDirection {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static int getRandomDirection() {
        Random rand = new Random();
        int direction = rand.nextInt(4);

        return direction;
    }
}