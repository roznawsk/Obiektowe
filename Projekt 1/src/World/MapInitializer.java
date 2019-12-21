package World;

import Entities.Animal.AnimalParameters;
import Basics.MapDirection;
import Basics.MapParams;
import Basics.Vector2d;
import Entities.Animal.Animal;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MapInitializer {

    MapParams params = new MapParams();
    protected LinkedList<Animal> initialAnimals = new LinkedList<>();
    static JSONObject parameters;

    static {
        try {
            parameters = (JSONObject) new JSONParser().parse(new FileReader("parameters.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static int initialAnimalCount = ((Long)parameters.get("initialAnimals")).intValue();
    private static Double jungleRatio = (Double)parameters.get("jungleRatio");
    private static int centerX = MapParams.width / 2 + MapParams.mapLL.x;
    private static int centerY = MapParams.height / 2 + MapParams.mapLL.y;

    private static int w = MapParams.width;
    private static int h = MapParams.height;
    private static int jungleW = (int) Math.sqrt(jungleRatio * w * h * Math.sqrt( (double) w / h ));
    private static int jungleH = (int) Math.sqrt(jungleRatio * w * h * Math.sqrt( (double) h / w ));

    public static JungleMap getMap(){
        return new JungleMap(generateRandomAnimals(initialAnimalCount));
    }

    public static int getWidth(){
        return ((Long) parameters.get("width")).intValue();
    }

    public static int getHeight(){
        return ((Long) parameters.get("height")).intValue();
    }

    public static Vector2d getMapLL(){
        return new Vector2d(0, 0);
    }

    public static Vector2d getMapUR(){
        int mapURx = MapParams.mapLL.x + MapParams.width;
        int mapURy = MapParams.mapLL.y + MapParams.height;
        return new Vector2d(mapURx, mapURy);
    }

    public static Vector2d getJungleLL(){
        return new Vector2d(centerX - jungleW / 2, centerY - jungleH / 2);
    }

    public static Vector2d getJungleUR(){
        return new Vector2d(centerX + jungleW / 2, centerY + jungleH / 2);
    }

    private static AnimalSet generateRandomAnimals(int number){
        AnimalSet initialAnimals = new AnimalSet();
        while(number-- > 0){
            Vector2d initialPosition = Vector2d.randomBetween(MapParams.mapLL, MapParams.mapUR);
            MapDirection initialDirection = MapDirection.random();
            initialAnimals.add(new Animal(new AnimalParameters(initialPosition, initialDirection, AnimalParameters.getStartEnergy())));
        }
        return initialAnimals;
    }

}
