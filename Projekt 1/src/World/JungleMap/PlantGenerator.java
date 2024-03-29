package World.JungleMap;

import Utiity.Vector2d;
import Entities.Plant;
import World.IPlantObserver;

import java.util.ArrayList;
import java.util.Random;

public class PlantGenerator implements IPlantObserver {
    private Random r =  new Random();
    private JungleMap map;

    private ArrayList<IPlantObserver> observers = new ArrayList<>();

    public PlantGenerator(JungleMap map){
        this.map = map;
    }

    public void addPlants(){
        Plant p1 = getJunglePlant();
        if(p1 != null){
            map.plantMap.put(p1.getPosition(), p1);
            existenceUpdate(p1.getPosition());
        }
        p1 = getMapPlant();
        if(p1 != null){
            map.plantMap.put(p1.getPosition(), p1);
            existenceUpdate(p1.getPosition());
        }
    }

    private Plant getJunglePlant(){
        ArrayList<Vector2d> availablePositions = new ArrayList<>();

        for(int x = map.params.jungleLL.x ; x <= map.params.jungleUR.x ; x++) {
            for (int y = map.params.jungleLL.y; y <= map.params.jungleUR.y; y++) {
                if (!map.isOccupied(new Vector2d(x, y)))
                    availablePositions.add(new Vector2d(x, y));
            }
        }
        if(availablePositions.size() == 0)
            return null;
        Vector2d newPos = availablePositions.get(r.nextInt(availablePositions.size()));
        return new Plant(newPos);
    }

    private Plant getMapPlant(){
        if(map.params.mapLL.equals(map.params.jungleLL) && map.params.mapUR.equals(map.params.jungleUR))
            return null;
        Vector2d newPos;
        for(int i = 0 ; i < 100 ; i++){
            do{
                newPos = Vector2d.randomBetween(map.params.mapLL, map.params.mapUR);
            }while(newPos.follows(map.params.jungleLL) && newPos.precedes(map.params.jungleUR));
            if(!map.isOccupied(newPos))
                return new Plant(newPos);
        }
        return null;
    }

    @Override
    public void existenceUpdate(Vector2d position){
        for(IPlantObserver observer : observers){
            observer.existenceUpdate(position);
        }
    }

    public void addObserver(IPlantObserver observer){
        observers.add(observer);
    }

}
