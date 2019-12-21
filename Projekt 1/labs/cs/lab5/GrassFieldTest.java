package agh.cs.lab5;

import agh.cs.lab2.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrassFieldTest {

    @Test
    public void isOccupied() {
        GrassField testmap = new GrassField(5);
        Animal foo1 = new Animal(testmap, new Vector2d(0,0));
        Assert.assertFalse(testmap.isOccupied(new Vector2d(0,0)));
        testmap.place(foo1);
        Assert.assertTrue(testmap.isOccupied(new Vector2d(0,0)));
        Assert.assertFalse(testmap.isOccupied(new Vector2d(1,0)));
    }

    @Test
    public void objectAt() {
        GrassField testmap = new GrassField(5);
        Animal foo1 = new Animal(testmap, new Vector2d(1,1));
        testmap.place(foo1);
        Assert.assertEquals(foo1, testmap.objectAt(new Vector2d(1,1)));
        Assert.assertEquals(null, testmap.objectAt(new Vector2d(-1,1)));
        Assert.assertEquals(null, testmap.objectAt(new Vector2d(2,1)));
        testmap.moveAnimal(foo1, MoveDirection.FORWARD);
        Assert.assertEquals(foo1, testmap.objectAt(new Vector2d(1,2)));
        Assert.assertEquals(null, testmap.objectAt(new Vector2d(1,1)));
        Assert.assertEquals(null, testmap.objectAt(new Vector2d(4,4)));
    }

    @Test
    public void canMoveTo() {
        GrassField testmap = new GrassField(3); //5x5
        Animal foo1 = new Animal(testmap);
        Assert.assertTrue(testmap.canMoveTo(new Vector2d(0,0)));
        testmap.place(foo1);
        Assert.assertFalse(testmap.canMoveTo(new Vector2d(0,0)));
        Assert.assertTrue(testmap.canMoveTo(new Vector2d(1,0)));
        Assert.assertFalse(testmap.canMoveTo(new Vector2d(-1,1)));
        Animal foo2 = new Animal(testmap, new Vector2d(2, 1));
        Assert.assertTrue(testmap.canMoveTo(new Vector2d(2,1)));
        testmap.place(foo2);
        Assert.assertFalse(testmap.canMoveTo(new Vector2d(2,1)));
        Assert.assertTrue(testmap.canMoveTo(new Vector2d(2,4)));
        testmap.moveAnimal(foo2, MoveDirection.BACKWARD);
        Assert.assertTrue(testmap.canMoveTo(new Vector2d(2,1)));
    }
}