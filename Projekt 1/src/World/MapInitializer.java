package World;

import Basics.AnimalParams;
import Entities.Animal.AnimalAttributes;
import Basics.MapDirection;
import Basics.MapParams;
import Basics.Vector2d;
import Entities.Animal.Animal;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class MapInitializer {

    JSONObject parameters = (JSONObject) new JSONParser().parse(new FileReader("parameters.json"));

    private int initialAnimalCount = ((Long)parameters.get("initialAnimalCount")).intValue();
    private Double jungleRatio = (Double)parameters.get("jungleRatio");


    private int w = ((Long)parameters.get("width")).intValue();
    private int h = ((Long)parameters.get("height")).intValue();
    private int jungleW = (int) Math.sqrt(jungleRatio * w * h * Math.sqrt( (double) w / h ));
    private int jungleH = (int) Math.sqrt(jungleRatio * w * h * Math.sqrt( (double) h / w ));

    private int centerX = w / 2 + getMapLL().x - 1;
    private int centerY = h / 2 + getMapLL().y - 1;

    public MapInitializer() throws IOException, ParseException {
    }

    public MapParams getParams(){
        return new MapParams(this);
    }

    public int getWidth(){
        return w;
    }

    public int getHeight(){
        return h;
    }

    public Vector2d getMapLL(){
        return new Vector2d(0, 0);
    }

    public Vector2d getMapUR(){
        int mapURx = getMapLL().x + w - 1;
        int mapURy = getMapLL().y + h - 1;
        return new Vector2d(mapURx, mapURy);
    }

    public Vector2d getJungleLL(){
        return new Vector2d(centerX - jungleW / 2, centerY - jungleH / 2);
    }

    public Vector2d getJungleUR(){
        return new Vector2d(centerX + jungleW / 2, centerY + jungleH / 2);
    }

    public int getStartEnergy(){
        return ((Long)parameters.get("startEnergy")).intValue();
    }

    public int getPlantEnergy(){
        return ((Long)parameters.get("plantEnergy")).intValue();
    }

    public int getMoveEnergy(){
        return ((Long)parameters.get("moveEnergy")).intValue();
    }

    public AnimalSet generateRandomAnimals(){
        AnimalSet initialAnimals = new AnimalSet();
        AnimalParams animalParams = new AnimalParams(this);
        for(int i = 0 ; i < initialAnimalCount ; i ++){
            Vector2d initialPosition = Vector2d.randomBetween(getMapLL(), getMapUR());
            MapDirection initialDirection = MapDirection.random();
            initialAnimals.add(new Animal(new AnimalAttributes(animalParams, initialPosition)));
        }
        return initialAnimals;
    }

}
