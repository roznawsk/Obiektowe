package agh.cs.lab7;

import agh.cs.lab2.Animal;
import agh.cs.lab2.Vector2d;

public abstract class AbstractWorldElement {
    protected Vector2d position;

    public abstract String toString();

    public Vector2d getPosition(){
        return this.position;
    }

    public int compareToX(AbstractWorldElement that){
        if(this.position.x == that.getPosition().x){
            if(this.position.y == that.getPosition().y){
                if(this.getClass().equals(that.getClass()))
                    return 0;
                if(this instanceof Animal)
                    return 1;
                return -1;
            }
            return this.position.y - that.getPosition().y;
        }
        return this.position.x - that.getPosition().x;
    }

    public int compareToY(AbstractWorldElement that){
        if(this.position.y == that.getPosition().y){
            if(this.position.x == that.getPosition().x){
                if(this.getClass().equals(that.getClass()))
                    return 0;
                if(this instanceof Animal)
                    return 1;
                return -1;
            }
            return this.position.x - that.getPosition().x;
        }
        return this.position.y - that.getPosition().y;
    }
}
