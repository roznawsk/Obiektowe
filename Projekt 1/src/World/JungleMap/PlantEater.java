package World.JungleMap;

import Utiity.Vector2d;
import Entities.Animal.Animal;
import World.IPlantObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class PlantEater implements IPlantObserver {
    private HashSet<Vector2d> processed = new HashSet<>();
    private JungleMap map;

    private ArrayList<IPlantObserver> observers = new ArrayList<>();

    public PlantEater(JungleMap map){
        this.map = map;
    }

    public void eatPlants(){
        for(Animal animal : map.animals){
            Vector2d currentPosition = animal.getPosition();
            if(!processed.contains(currentPosition) && map.plantMap.containsKey(currentPosition)){
                map.plantMap.remove(animal.getPosition());
                processed.add(currentPosition);
                LinkedList<Animal> sameEnergy = getSameEnergy(currentPosition);
                for(Animal toFeed : sameEnergy){
                    toFeed.gainEnergy(sameEnergy.size());
                }
            }
        }
        processed.clear();
    }

    private LinkedList<Animal> getSameEnergy(Vector2d position){
        double currentEnergy = -1;
        LinkedList<Animal> sameEnergy = new LinkedList<>();
        for(Animal nextAnimal : map.animalMap.get(position)){
            if(nextAnimal.getEnergy() > currentEnergy){
                sameEnergy.clear();
                sameEnergy.add(nextAnimal);
                currentEnergy = nextAnimal.getEnergy();
            }
            else if(nextAnimal.getEnergy() == currentEnergy){
                sameEnergy.add(nextAnimal);
            }
        }
        return sameEnergy;
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
