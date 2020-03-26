import java.util.Random;

public class Randomizer {

    private Random rand;

    public Randomizer(long seed){
        rand = new Random(seed);
    }

    public double getRandom(){
        return rand.nextDouble();
    }
}
