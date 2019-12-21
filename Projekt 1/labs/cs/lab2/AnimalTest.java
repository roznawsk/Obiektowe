package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void move() {

    }

    @Test
    public void input(){

    }

    @Test
    public void positionChanged() {
        GrassField map = new GrassField(5);
        Animal foo = new Animal(map, new Vector2d(0,0));
        GrassField map2 = new GrassField(6);
        map.place(foo);
        map2.place(foo);
        foo.addObserver(map2);
        map.moveAnimal(foo, MoveDirection.FORWARD);
        Assert.assertEquals(foo, map.objectAt(new Vector2d(0,1)));
        Assert.assertEquals(foo, map2.objectAt(new Vector2d(0,1)));
        map.moveAnimal(foo, MoveDirection.FORWARD);
        Assert.assertEquals(foo, map.objectAt(new Vector2d(0,2)));
        Assert.assertEquals(foo, map2.objectAt(new Vector2d(0,2)));
        foo.removeObserver(map2);
        map.moveAnimal(foo, MoveDirection.BACKWARD);
        Assert.assertEquals(foo, map.objectAt(new Vector2d(0,1)));
        Assert.assertEquals(foo, map2.objectAt(new Vector2d(0,2)));
    }
}