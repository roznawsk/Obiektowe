package Entities.Animal;

import Basics.AnimalParams;
import Basics.MapDirection;
import Basics.Vector2d;

public class AnimalAttributes {
    private AnimalParams params;
    private Vector2d position;
    private MapDirection direction;
    private Genome genome;
    private double energy;

    //Constructors
    public AnimalAttributes(AnimalParams params, Vector2d position){
        this(params, position, MapDirection.random(), params.startEnergy, new Genome());
    }

    public AnimalAttributes(AnimalParams params, Vector2d position, MapDirection direction, double energy, Genome genome){
        this.params = params;
        this.position = position;
        this.direction = direction;
        this.energy = energy;
        this.genome = genome;
    }

    public void rotate(){
        direction = genome.randomDirection();
    }

    public boolean canBreed(){
        return energy >= params.startEnergy / 2;
    }

    //Getters

    public Vector2d getPosition() {
        return new Vector2d(position);
    }

    public MapDirection getDirection(){
        return direction;
    }

    public Entities.Animal.Genome getGenome() {
        return new Genome(genome);
    }

    public double getEnergy() {
        return energy;
    }

    public AnimalParams getParams(){
        return params;
    }

    //Setters

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public void gainPlantEnergy(int num){
        this.energy += params.plantEnergy / num;
    }

    public void drainMoveEnergy(){
        this.energy -= params.moveEnergy;
    }

    public void drainBreedingEnergy(){
        this.energy -= this.energy * 0.25;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}
