package agh.cs.lab5;

import agh.cs.lab2.*;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import java.lang.Math;
import java.util.ArrayList;

public class JungleField {

    protected Vector2d lowerleft;
    protected Vector2d upperright;
    protected Vector2d jungleLowerleft;
    protected Vector2d jungleUpperright;

    protected Hashtable<Vector2d, Animal> animalMap = new Hashtable<>();
    protected Hashtable<Vector2d, Animal> animals = new Hashtable<>();

    protected Hashtable<Vector2d, Grass> plantMap;
    protected ArrayList<Grass> plants;

    public JungleField(Vector2d lowerleft, Vector2d upperright, Vector2d jungleLowerleft, Vector2d jungleUpperright,
                       LinkedList<Animal> initialAnimals){
        this.upperright = upperright;
        this.lowerleft = lowerleft;
        this.jungleLowerleft = jungleLowerleft;
        this.jungleUpperright = jungleUpperright;

    }

    public String toString(){
        MapVisualizer picture = new MapVisualizer(this);
        return  picture.draw(lowerleft, upperright);
    }

    public void run(MoveDirection[] directions){
        for(int i=0, a=0;i<directions.length;i++, a++, a%=animals.size()){
            moveAnimal(animals.get(a), directions[i]);
        }
    }

    public boolean place(Animal animal){
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            this.animalMap.put(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException("Field "+animal.getPosition()+" is already occupied");
    }

    public void moveAnimal(Animal animal, MoveDirection direction){
        Vector2d oldPosition = animal.getPosition();
        animalMap.remove(animal.getPosition(), animal);
        animal.move(direction);
        animalMap.put(animal.getPosition(), animal);
        System.out.println(animalMap.values());
    }

    public boolean isOccupied(Vector2d position){
        if(!(position.precedes(upperright) && position.follows(lowerleft))) return false;
        return(animalMap.get(position) != null || grassMap.get(position) != null);
    }

    public Object objectAt(Vector2d position){
        Object whoAt = animalMap.get(position);
        if(whoAt != null) return whoAt;
        return grassMap.get(position);
    }

    public boolean canMoveTo(Vector2d position){
        return objectAt(position) == null || objectAt(position).getClass() == Grass.class ;
    }
}