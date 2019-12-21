package agh.cs.lab2;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsParserTest {

    @Test
    public void parse() {
        String[] str = {"l", "r", "f", "b"};
        MoveDirection[] directions = OptionsParser.parse(str);
        MoveDirection[] a = {MoveDirection.LEFT, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.BACKWARD};
        Assert.assertArrayEquals(a, directions);
    }
}