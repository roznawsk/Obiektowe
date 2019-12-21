package agh.cs.lab5;

import agh.cs.lab2.Animal;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractWorldMapTest {

    @Test
    public void run() {
        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT};
        RectangularMap testmap = new RectangularMap(5,5);
        Animal foo1 = new Animal(testmap, new Vector2d(2, 2));
        Animal foo2 = new Animal(testmap, new Vector2d(3, 2));
        testmap.place(foo1);
        testmap.place(foo2);
        testmap.run(directions);
        Assert.assertEquals(new Vector2d(2,3), foo1.getPosition());
        Assert.assertEquals(MapDirection.WEST, foo1.getDirection());
        Assert.assertEquals(new Vector2d(3,1), foo2.getPosition());
        Assert.assertEquals(MapDirection.WEST, foo1.getDirection());
    }
}