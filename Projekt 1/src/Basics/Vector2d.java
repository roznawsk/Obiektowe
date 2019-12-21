package Basics;

import java.util.Random;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d other){
        this.x = other.x;
        this.y = other.y;
    }

    public String toString(){
        return("(" + this.x + "," + this.y + ")");
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }

    public static Vector2d randomBetween(Vector2d lowerleft, Vector2d upperright){
        Random r = new Random();
        return new Vector2d(r.nextInt(upperright.x - lowerleft.x) + lowerleft.x,
                r.nextInt(upperright.y - lowerleft.y) + lowerleft.y);
    }
    public Boolean precedes(Vector2d other){
        return (this.x <= other.x && this.y <= other.y);
    }
    public Boolean follows(Vector2d other){
        return(this.x >= other.x && this.y >= other.y);
    }
    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y+other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return (this.x == that.x && this.y == that.y);
    }
    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }
}
