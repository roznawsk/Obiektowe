package World;

import Basics.Vector2d;
import Entities.Animal.Animal;

import java.util.HashSet;
import java.util.LinkedList;

public class PlantEater {
    HashSet<Vector2d> processed = new HashSet<>();
    JungleMap map;

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
}
