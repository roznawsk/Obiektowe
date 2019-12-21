package World.JungleMap;

import Utiity.MapDirection;
import Utiity.Vector2d;
import Entities.Animal.Animal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class AnimalBreeder {

    private JungleMap map;
    HashSet<Vector2d> processed = new HashSet<>();

    public AnimalBreeder(JungleMap map){
        this.map = map;
    }

    public void breedAnimals() {
        LinkedList<Animal> toAdd = new LinkedList<Animal>();

        for (Animal animal : map.animals) {
            Vector2d currentPosition = animal.getPosition();
            if (canProcess(currentPosition)) {
                processed.add(animal.getPosition());
                ArrayList<Animal> topTwo = getTopTwo(currentPosition);
                if (topTwo.size() == 2) {
                    Vector2d availablePlace = freeSpaceNear(currentPosition);
                    if(availablePlace != null){
                        Animal baby = new Animal(topTwo.get(0), topTwo.get(1), availablePlace);
                        topTwo.get(0).drainBreedingEnergy();
                        topTwo.get(1).drainBreedingEnergy();
                        toAdd.add(baby);
                    }
                }
            }
        }
        for (Animal baby : toAdd) {
            map.place(baby);
        }
        processed.clear();
    }

    private boolean canProcess(Vector2d position){
        return !processed.contains(position) && map.animalMap.get(position).size() > 1;
    }

    private ArrayList<Animal> getTopTwo(Vector2d position){
        AnimalSet animalsAt = map.animalMap.get(position);
        Animal a1 = animalsAt.get(0);
        Animal a2 = animalsAt.get(1);
        for(Animal next : animalsAt){
            if(next.getEnergy() > a1.getEnergy() && !next.equals(a2)){
                a2 = a1;
                a1 = next;
            }
            else if(next.getEnergy() > a2.getEnergy() && !next.equals(a1)){
                a2 = next;
            }
        }
        ArrayList<Animal> topTwo = new ArrayList<>();
        if(!a1.canBreed() || !a2.canBreed()){
            return topTwo;
        }
        topTwo.add(a1);
        topTwo.add(a2);
        return topTwo;

    }

    private Vector2d freeSpaceNear(Vector2d position){
        ArrayList<Vector2d> moves = new ArrayList<>();
        for(MapDirection dir : MapDirection.values()){
            Vector2d newPosition = map.params.inMapVector(dir.toUnitVector().add(position));
            if(!map.isOccupied(newPosition) && !processed.contains(newPosition))
                moves.add(newPosition);
        }
        if(moves.size() == 0)
            return null;
        Random r = new Random();
        return new Vector2d(moves.get(r.nextInt(moves.size())));
    }
}
