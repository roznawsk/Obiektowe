package Entities;

import Utiity.Vector2d;

public class Plant{

    private Vector2d position;
    public Plant(Vector2d position){
        this.position = position;
    }
    public String toString(){
        return "\uD83C\uDF34";
    }

    public Vector2d getPosition() {
        return position;
    }
}
