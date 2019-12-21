package Entities.Animal;

import Basics.MapParams;
import Basics.Vector2d;
import World.IWorldObserver;
import World.JungleMap;

import java.util.ArrayList;

public class Animal implements IWorldObserver{

    private JungleMap map;
    private AnimalParameters params;
    private ArrayList<IWorldObserver> observers = new ArrayList<>();

    //Constructors
    public Animal(AnimalParameters animalParameters){
        this(null, animalParameters);
    }

    public Animal(JungleMap map, AnimalParameters animalParameters){
        this.map = map;
        if(map != null)
            addObserver(map);
        this.params = animalParameters;
    }

    //toString
    public String toString(){
        return "\uD83E\uDD81";
    }

    //Getters
    public Vector2d getPosition() {
        return params.getPosition();
    }

    public Entities.Animal.Genome getGenome() {
        return params.getGenome();
    }

    public int getEnergy(){ return params.getEnergy(); }

    //Setters
    public void setMap(JungleMap map) {
        this.map = map;
        addObserver(map);
    }

    public void changeEnergy(int gain){
        this.params.changeEnergy(gain);
    }

    public void moveForward(){
        Vector2d oldPosition = new Vector2d(getPosition());
        Vector2d newPosition = params.getPosition().add(params.getDirection().toUnitVector());
        newPosition = MapParams.inMapVector(newPosition);
        params.changeEnergy(-AnimalParameters.getMoveEnergy());
        if(params.getEnergy() <= 0){
            positionChanged(this, oldPosition);
            return;
        }
        params.setPosition(newPosition);
        params.rotate();
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
