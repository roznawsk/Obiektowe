package Entities.Animal;

import Basics.MapDirection;
import Basics.Vector2d;
import World.IWorldObserver;
import World.JungleMap;

import java.util.ArrayList;

public class Animal implements IWorldObserver{

    private JungleMap map;
    private AnimalAttributes attribs;
    private ArrayList<IWorldObserver> observers = new ArrayList<>();

    //Constructors
    public Animal(AnimalAttributes animalParameters){
        this(null, animalParameters);
    }

    public Animal(JungleMap map, AnimalAttributes animalParameters){
        this.map = map;
        if(map != null)
            addObserver(map);
        this.attribs = animalParameters;
    }

    public Animal(Animal parent1, Animal parent2, Vector2d newPosition){
        this.map = parent1.map;
        this.observers = parent1.observers;
        double newEnergy = (0.25 * (parent1.getEnergy() + parent2.getEnergy()));
        Genome newGenome = new Genome(parent1.getGenome(), parent2.getGenome());
        this.attribs = new AnimalAttributes(parent1.attribs.getParams(), newPosition, MapDirection.random(), newEnergy, newGenome);
    }

    //toString
    public String toString(){
        return "\uD83E\uDD81";
    }

    //Getters
    public Vector2d getPosition() {
        return attribs.getPosition();
    }

    public Entities.Animal.Genome getGenome() {
        return attribs.getGenome();
    }

    public double getEnergy(){ return attribs.getEnergy(); }

    //Setters
    public void setMap(JungleMap map) {
        this.map = map;
        addObserver(map);
    }

    public void gainEnergy(int num){attribs.gainPlantEnergy(num);}

    public boolean canBreed(){
        return attribs.canBreed();
    }

    public void drainBreedingEnergy(){
        attribs.drainBreedingEnergy();
    }

    public void moveForward(){
        Vector2d oldPosition = new Vector2d(getPosition());
        Vector2d newPosition = attribs.getPosition().add(attribs.getDirection().toUnitVector());
        newPosition = map.params.inMapVector(newPosition);
//        System.out.println("C1: " + observers.size() + " " + getEnergy());
        attribs.drainMoveEnergy();
//        System.out.println("C2: " + observers.size() + " " + getEnergy());
        if(attribs.getEnergy() <= 0){
            positionChanged(this, oldPosition);
            return;
        }
        attribs.setPosition(newPosition);
        attribs.rotate();
        positionChanged(this, oldPosition);
    }

    public void addObserver(IWorldObserver observer){
        observers.add(observer);
    }

    @Override
    public void positionChanged(Animal element, Vector2d oldPos) {
        for(IWorldObserver observer : observers){
            observer.positionChanged(element, oldPos);
        }
    }

}
