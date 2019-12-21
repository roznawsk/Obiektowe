package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapDirectionTest {

    @Test
    public void next() {
        MapDirection a = MapDirection.NORTH;
        Assert.assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        a = MapDirection.EAST;
        Assert.assertEquals(MapDirection.SOUTH, a.next());
        a = MapDirection.SOUTH;
        Assert.assertEquals(MapDirection.WEST, a.next());
        a = MapDirection.WEST;
        Assert.assertEquals(MapDirection.NORTH, a.next());
    }

    @Test
    public void previous() {
        MapDirection a = MapDirection.NORTH;
        Assert.assertEquals(MapDirection.WEST, a.previous());
        a = MapDirection.WEST;
        Assert.assertEquals(MapDirection.SOUTH, a.previous());
        a = MapDirection.SOUTH;
        Assert.assertEquals(MapDirection.EAST, a.previous());
        a = MapDirection.EAST;
        Assert.assertEquals(MapDirection.NORTH, a.previous());
    }

}