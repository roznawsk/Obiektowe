package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab7.AbstractWorldElement;

public class Grass extends AbstractWorldElement {
    public Grass(Vector2d position){
        this.position = position;
    }
    public String toString(){
        return("*");
    }
}
