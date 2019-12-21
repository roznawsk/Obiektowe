package Entities.Animal;

import Basics.MapDirection;
import Basics.Vector2d;

import java.util.Random;

public class AnimalParameters {
    private Vector2d position;
    private MapDirection direction;
    private Genome genome;
    private int energy;
    private int id;

    private static int startEnergy;
    private static int plantEnergy;
    private static int moveEnergy;

    //Constructors
    public AnimalParameters(Vector2d position, MapDirection direction, int energy){
        this(position, direction, energy, new Genome());
    }

    public AnimalParameters(Vector2d position, MapDirection direction, int energy, Genome genome){
        this.position = position;
        this.direction = direction;
        this.energy = energy;
        this.genome = genome;
    }

    public void rotate(){
        Random r = new Random();
        direction = MapDirection.values()[r.nextInt(8)];
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

    public int getEnergy() {
        return energy;
    }

    public static int getStartEnergy() {
        return startEnergy;
    }

    public static int getPlantEnergy() {
        return plantEnergy;
    }

    public static int getMoveEnergy() {
        return moveEnergy;
    }
    //Setters

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public void setDirection(MapDirection direction) {
        this.direction = direction;
    }

    public void changeEnergy(int gain) {
        this.energy += gain;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public static void setStartEnergy(int startEnergy) {
        AnimalParameters.startEnergy = startEnergy;
    }

    public static void setMoveEnergy(int moveEnergy) {
        AnimalParameters.moveEnergy = moveEnergy;
    }

    public static void setPlantEnergy(int plantEnergy) {
        AnimalParameters.plantEnergy = plantEnergy;
    }
}
