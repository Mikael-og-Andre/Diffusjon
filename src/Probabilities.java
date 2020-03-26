import java.util.HashMap;

public class Probabilities {

    HashMap<Neighbours, Double> probabilities;


    public Probabilities(double left_top, double left_mid, double left_bottom, double mid_top, double mid_mid, double mid_bottom, double right_top, double right_mid, double right_bottom) {
        probabilities = new HashMap<>();

        if ((left_top+left_mid+left_bottom+mid_top+mid_mid+mid_bottom+right_top+right_mid+right_bottom)<=1){
            probabilities.put(Neighbours.LEFT_TOP, left_top);
            probabilities.put(Neighbours.LEFT_MID, left_mid);
            probabilities.put(Neighbours.LEFT_BOT, left_bottom);
            probabilities.put(Neighbours.MID_TOP, mid_top);
            probabilities.put(Neighbours.MID_MID, mid_mid);
            probabilities.put(Neighbours.MID_BOT, mid_bottom);
            probabilities.put(Neighbours.RIGHT_TOP, right_top);
            probabilities.put(Neighbours.RIGHT_MID, right_mid);
            probabilities.put(Neighbours.RIGHT_BOT, right_bottom);
        }
        else {
            probabilities.put(Neighbours.LEFT_TOP, 0.07);
            probabilities.put(Neighbours.LEFT_MID, 0.07);
            probabilities.put(Neighbours.LEFT_BOT, 0.07);
            probabilities.put(Neighbours.MID_TOP, 0.07);
            probabilities.put(Neighbours.MID_MID, 0.5);
            probabilities.put(Neighbours.MID_BOT, 0.07);
            probabilities.put(Neighbours.RIGHT_TOP, 0.07);
            probabilities.put(Neighbours.RIGHT_MID, 0.07);
            probabilities.put(Neighbours.RIGHT_BOT, 0.07);
        }
    }

    public double getProbability(Neighbours neighbour){
        return probabilities.get(neighbour);
    }

    public Neighbours getCellSpot(double prob){
        if (prob<0) return Neighbours.MID_MID;

        if (probabilities.get(Neighbours.LEFT_TOP)>prob){
            return Neighbours.LEFT_TOP;
        }
        else if (probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.LEFT_MID;
        }
        else if (probabilities.get(Neighbours.LEFT_BOT)+probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.LEFT_BOT;
        }
        else if (probabilities.get(Neighbours.MID_TOP)+probabilities.get(Neighbours.LEFT_BOT)+probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.MID_TOP;
        }
        else if (probabilities.get(Neighbours.MID_BOT)+probabilities.get(Neighbours.MID_TOP)+probabilities.get(Neighbours.LEFT_BOT)+probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.MID_BOT;
        }
        else if (probabilities.get(Neighbours.RIGHT_TOP)+probabilities.get(Neighbours.MID_BOT)+probabilities.get(Neighbours.MID_TOP)+probabilities.get(Neighbours.LEFT_BOT)+probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.RIGHT_TOP;
        }
        else if (probabilities.get(Neighbours.RIGHT_MID)+probabilities.get(Neighbours.RIGHT_TOP)+probabilities.get(Neighbours.MID_BOT)+probabilities.get(Neighbours.MID_TOP)+probabilities.get(Neighbours.LEFT_BOT)+probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.RIGHT_MID;
        }
        else if (probabilities.get(Neighbours.RIGHT_BOT)+probabilities.get(Neighbours.RIGHT_MID)+probabilities.get(Neighbours.RIGHT_TOP)+probabilities.get(Neighbours.MID_BOT)+probabilities.get(Neighbours.MID_TOP)+probabilities.get(Neighbours.LEFT_BOT)+probabilities.get(Neighbours.LEFT_TOP)+probabilities.get(Neighbours.LEFT_MID)>prob){
            return Neighbours.RIGHT_BOT;
        }
        else {
            return Neighbours.MID_MID;
        }

    }
}
