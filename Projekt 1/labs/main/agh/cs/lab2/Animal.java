package agh.cs.lab2;

import agh.cs.lab7.AbstractWorldElement;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.ArrayList;

public class Animal extends AbstractWorldElement {
    private MapDirection direction;
    private IWorldMap map;
    protected ArrayList<IPositionChangeObserver> observers;

    protected ArrayList genome = new ArrayList(8);

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection initialDirection, ArrayList genome){
        this.genome = genome;
        observers = new ArrayList<>();
        this.map = map;
        this.position = initialPosition;
        this.direction = initialDirection;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection initialDirection, ArrayList genome1, ArrayList genome2){
        this(map, initialPosition, initialDirection, randomGenome(genome1, genome2));
    }

    protected static ArrayList randomGenome(ArrayList genome1, ArrayList genome2){
        ArrayList newGenome = new ArrayList(8);
        return newGenome;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer: observers)
            observer.positionChanged(oldPosition, newPosition);
    }

    public String toString(){
        return(this.direction.toString());
    }

    public MapDirection getDirection(){
        return this.direction;
    }

    public void move(MoveDirection direction){
        Vector2d oldPosition = position;
        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                Vector2d newLocation = this.position.add(this.direction.toUnitVector());
                if(map.canMoveTo(newLocation))
                    this.position = newLocation;
                break;
            case BACKWARD:
                newLocation = this.position.subtract(this.direction.toUnitVector());
                if(map.canMoveTo(newLocation))
                    this.position = newLocation;
                break;
            default:
                break;
        }
        positionChanged(oldPosition, position);
    }
}
