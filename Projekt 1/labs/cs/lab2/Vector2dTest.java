package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Vector2dTest {

    @Test
    public void testToString() {
        Vector2d example = new Vector2d(3,6);
        Assert.assertEquals("(3,6)", example.toString());
        example = new Vector2d(-4, -11);
        Assert.assertEquals("(-4,-11)", example.toString());
    }

    @Test
    public void precedes() {
        Vector2d preceder = new Vector2d(1,2);
        Vector2d follower = new Vector2d(1,2);
        Assert.assertEquals(TRUE, preceder.precedes(follower) );
        follower = new Vector2d(2,1);
        Assert.assertEquals(FALSE, preceder.precedes(follower) );
        follower = new Vector2d(9,11);
        Assert.assertEquals(TRUE, preceder.precedes(follower) );
    }

    @Test
    public void follows() {
        Vector2d preceder = new Vector2d(1,2);
        Vector2d follower = new Vector2d(1,2);
        Assert.assertEquals(TRUE, follower.follows(preceder) );
        follower = new Vector2d(2,1);
        Assert.assertEquals(FALSE, follower.follows(preceder) );
        follower = new Vector2d(9,11);
        Assert.assertEquals(TRUE, follower.follows(preceder) );
    }

    @Test
    public void upperRight() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(4,-3);
        Assert.assertEquals("(4,1)", a.upperRight(b).toString());
        Assert.assertEquals("(4,1)", b.upperRight(a).toString());
        a = new Vector2d(4, -3);
        Assert.assertEquals("(4,-3)", a.upperRight(b).toString());
        Assert.assertEquals("(4,-3)", b.upperRight(a).toString());
        a = new Vector2d(7, 10);
        Assert.assertEquals("(7,10)", a.upperRight(b).toString());
        Assert.assertEquals("(7,10)", b.upperRight(a).toString());
        a = new Vector2d(-1, -1);
        Assert.assertEquals("(4,-1)", a.upperRight(b).toString());
        Assert.assertEquals("(4,-1)", b.upperRight(a).toString());

    }

    @Test
    public void lowerLeft() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(4,-3);
        Assert.assertEquals("(1,-3)", a.lowerLeft(b).toString());
        Assert.assertEquals("(1,-3)", b.lowerLeft(a).toString());
        a = new Vector2d(4, -3);
        Assert.assertEquals("(4,-3)", a.lowerLeft(b).toString());
        Assert.assertEquals("(4,-3)", b.lowerLeft(a).toString());
        a = new Vector2d(7, 10);
        Assert.assertEquals("(4,-3)", a.lowerLeft(b).toString());
        Assert.assertEquals("(4,-3)", b.lowerLeft(a).toString());
        a = new Vector2d(-1, -1);
        Assert.assertEquals("(-1,-3)", a.lowerLeft(b).toString());
        Assert.assertEquals("(-1,-3)", b.lowerLeft(a).toString());
    }

    @Test
    public void add() {
        Vector2d a = new Vector2d(-1,1);
        Vector2d b = new Vector2d(7,-3);
        Assert.assertEquals("(6,-2)", a.add(b).toString());
        Assert.assertEquals("(6,-2)", b.add(a).toString());
        b = new Vector2d(-1,14);
        Assert.assertEquals("(-2,15)", a.add(b).toString());
        Assert.assertEquals("(-2,15)", b.add(a).toString());
    }

    @Test
    public void subtract() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(4,-3);
        Assert.assertEquals("(-3,4)", a.subtract(b).toString());
        Assert.assertEquals("(3,-4)", b.subtract(a).toString());
        b = new Vector2d(-1,14);
        Assert.assertEquals("(2,-13)", a.subtract(b).toString());
        Assert.assertEquals("(-2,13)", b.subtract(a).toString());
    }

    @Test
    public void testEquals() {
        Vector2d a = new Vector2d(1,1);
        Vector2d b = new Vector2d(4,-3);
        Assert.assertEquals(FALSE, a.equals(b));
        Assert.assertEquals(FALSE, b.equals(a));
        a = new Vector2d(4, -3);
        Assert.assertEquals(TRUE, a.equals(b));
        Assert.assertEquals(TRUE, b.equals(a));
        a = new Vector2d(4, 3);
        Assert.assertEquals(FALSE, a.equals(b));
        Assert.assertEquals(FALSE, b.equals(a));
    }

    @Test
    public void opposite() {
        Vector2d a = new Vector2d(1,1);
        Assert.assertEquals("(-1,-1)", a.opposite().toString());
        a = new Vector2d(-2,11);
        Assert.assertEquals("(2,-11)", a.opposite().toString());
    }
}