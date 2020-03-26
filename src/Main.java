public class Main {
    public static void main(String[] args) {
        Probabilities chances = new Probabilities(0.07,0.07,0.07,0.07,0.5,0.07,0.07,0.07,0.07);
        Simulator sim = new Simulator(100,100, 12312312, chances,10000);
        sim.simulateSteps(100);
    }
}
