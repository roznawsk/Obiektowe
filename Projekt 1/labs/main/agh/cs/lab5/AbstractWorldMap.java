package agh.cs.lab5;

import agh.cs.lab2.*;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    public Collection<Animal> getAnimals(){
        return (Collection<Animal>) animals.elements();
    }

    public abstract boolean place(Animal animal);

    public abstract void moveAnimal(Animal animal, MoveDirection direction);

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animalMap.get(oldPosition);
        animalMap.remove(oldPosition);
        animals.remove(oldPosition);
        animals.put(oldPosition, animal);
        animalMap.put(newPosition, animal);
    }


}
