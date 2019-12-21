package agh.cs.lab2;
import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        ArrayList<MoveDirection> directions = new ArrayList<MoveDirection>();
        for(String arg : args){
            switch(arg){
                case "f":
                case "forward":
                    directions.add(MoveDirection.FORWARD);
                    break;
                case "b":
                case "backward":
                    directions.add(MoveDirection.BACKWARD);
                    break;
                case "l":
                case "left":
                    directions.add(MoveDirection.LEFT);
                    break;
                case "r":
                case "right":
                    directions.add(MoveDirection.RIGHT);
                    break;
                default:
                    throw new IllegalArgumentException(arg + " is not legal move specification");
                    //break;
            }
        }
        return directions.toArray(new MoveDirection[0]);
    }
}
