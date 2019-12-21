package World;

import Utiity.AnimalParams;
import Entities.Animal.AnimalAttributes;
import Utiity.MapDirection;
import Utiity.MapParams;
import Utiity.Vector2d;
import Entities.Animal.Animal;
import World.JungleMap.AnimalSet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class MapInitializer {

    JSONObject parameters = (JSONObject) new JSONParser().parse(new FileReader("parameters.json"));

    private int initialAnimalCount = ((Long)parameters.get("initialAnimalCount")).intValue();
    private Double jungleToSavannaRatio = (Double)parameters.get("jungleRatio");
    private Double jungleRatio = jungleToSavannaRatio / (jungleToSavannaRatio + 1);


    private int w = ((Long)parameters.get("width")).intValue();
    private int h = ((Long)parameters.get("height")).intValue();
    private int jungleW = (int) Math.sqrt(jungleRatio * w * h * Math.sqrt( (double) w / h ));
    private int jungleH = (int) Math.sqrt(jungleRatio * w * h * Math.sqrt( (double) h / w ));

    private int centerX = w / 2 + getMapLL().x - 1;
    private int centerY = h / 2 + getMapLL().y - 1;

    public MapInitializer() throws IOException, ParseException {
        if(jungleToSavannaRatio <= 0)
            throw new IllegalArgumentException("JungleRatio must be greater than 0");
        if(w <= 0 || h <= 0)
            throw  new IllegalArgumentException("Both width and height must be greater than 0");
        if(initialAnimalCount < 0)
            throw new IllegalArgumentException("InitialAnimalCount must be greater than 0");
        if(getStartEnergy() <= 0)
            throw new IllegalArgumentException("StartEnergy must be greater than 0");
    }

    public int getSimulationSteps(){
        return ((Long)parameters.get("simulationSteps")).intValue();
    }

    public int getSleepTime(){
        return ((Long)parameters.get("simulationStepWaitMs")).intValue();
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
