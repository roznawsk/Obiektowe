package agh.cs.lab5;

import agh.cs.lab2.Animal;
import java.util.Hashtable;
import agh.cs.lab2.MapVisualizer;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab5.AbstractWorldMap;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        this.animalMap = new Hashtable<>();
        this.lowerleft = new Vector2d(0,0);
        this.upperright = new Vector2d(width-1, height-1);
        this.animals = new Hashtable<>();
    }

    protected void setBounds(){
        upperright = mapBoundary.comptUpperRight();
        lowerleft = mapBoundary.comptLowerLeft();
    }

    public void moveAnimal(Animal animal, MoveDirection direction){
        animalMap.remove(animal.getPosition(), animal);
        animal.move(direction);
        animalMap.put(animal.getPosition(), animal);
    }

    public boolean isOccupied(Vector2d position){
        if(!(position.precedes(upperright) && position.follows(lowerleft))) return false;
        return(animalMap.get(position) != null);
    }

    public boolean canMoveTo(Vector2d position){
        return position.follows(this.lowerleft) && position.precedes(this.upperright) && !isOccupied(position);
    }

    public boolean place(Animal animal){
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            this.animalMap.put(animal.getPosition(), animal);
            return true;
        }
        if(isOccupied(animal.getPosition()))
            throw new IllegalArgumentException("Field "+animal.getPosition()+" is already occupied");
        else
            throw new IllegalArgumentException("MapObjects.Animal outside Map.World map");
    }

    public Object objectAt(Vector2d position){
        return animalMap.get(position);
    }
}
