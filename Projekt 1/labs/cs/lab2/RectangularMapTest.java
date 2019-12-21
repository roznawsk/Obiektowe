package agh.cs.lab2;

import agh.cs.lab5.RectangularMap;
import org.junit.Assert;
import org.junit.Test;

public class RectangularMapTest {

    @Test
    public void moveAnimal() {
        RectangularMap testmap = new RectangularMap(3,3);
        Animal foo1 = new Animal(testmap);
        Assert.assertFalse(testmap.isOccupied(new Vector2d(0,0)));
        testmap.place(foo1);
        Assert.assertTrue(testmap.isOccupied(new Vector2d(0,0)));
        testmap.moveAnimal(foo1, MoveDirection.FORWARD);
        Assert.assertFalse(testmap.isOccupied(new Vector2d(0,0)));
        Assert.assertTrue(testmap.isOccupied(new Vector2d(0,1)));
        testmap.moveAnimal(foo1, MoveDirection.RIGHT);
        Assert.assertFalse(testmap.isOccupied(new Vector2d(0,0)));
        Assert.assertTrue(testmap.isOccupied(new Vector2d(0,1)));
        testmap.moveAnimal(foo1, MoveDirection.FORWARD);
        Assert.assertFalse(testmap.isOccupied(new Vector2d(0,1)));
        Assert.assertTrue(testmap.isOccupied(new Vector2d(1,1)));
    }

    @Test
    public void isOccupied() {
        RectangularMap testmap = new RectangularMap(3,3);
        Animal foo1 = new Animal(testmap, new Vector2d(0,0));
        Assert.assertFalse(testmap.isOccupied(new Vector2d(0,0)));
        testmap.place(foo1);
        Assert.assertTrue(testmap.isOccupied(new Vector2d(0,0)));
        Assert.assertFalse(testmap.isOccupied(new Vector2d(1,0)));
    }

    @Test
    public void canMoveTo() {
        RectangularMap testmap = new RectangularMap(3,3);
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
        Assert.assertFalse(testmap.canMoveTo(new Vector2d(2,4)));
        testmap.moveAnimal(foo2, MoveDirection.BACKWARD);
        Assert.assertTrue(testmap.canMoveTo(new Vector2d(2,1)));
    }

    @Test
    public void place() {
        RectangularMap testmap = new RectangularMap(3,3);
        Animal foo1 = new Animal(testmap);
        testmap.place(foo1);
        Assert.assertEquals(1, testmap.getAnimals().size());
        Assert.assertEquals(foo1, testmap.getAnimals().toArray()[0]);
    }

    @Test
    public void objectAt() {
        RectangularMap testmap = new RectangularMap(3,3);
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
}