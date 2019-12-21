package Basics;

import Entities.Animal.Genome;

import java.util.Random;

public enum MapDirection {
    N, NW, W, SW, S, SE, E, NE;
    public String toString(){
        switch(this){
            case E:
                return String.format( "%3s", "\uD83E\uDC7A");
            case NW:
                return "\uD83E\uDC7C";
            case W:
                return "\uD83E\uDC78";
            case N:
                return "\uD83E\uDC79";
            case SW:
                return "🡿";
            case S:
                return "\uD83E\uDC7B";
            case SE:
                return "\uD83E\uDC7E";
            case NE:
                return "\uD83E\uDC7D";
        }
        return null;
    }
    public MapDirection next(){
        MapDirection A[] = values();
        return A[(this.ordinal()+1) % A.length];
    }
    public MapDirection previous(){
        MapDirection A[] = values();
        return A[(this.ordinal()+3) % A.length];
    }
    public static MapDirection random(){
        Random r = new Random();
        return MapDirection.values()[r.nextInt(8)];
    }
    public static MapDirection random(Genome genome){
        return MapDirection.N;
    }
    public Vector2d toUnitVector(){
        switch(this){
            case S:
                return new Vector2d(0,-1);
            case N:
                return new Vector2d(0,1);
            case NW:
                return new Vector2d(-1,1);
            case W:
                return new Vector2d(-1,0);
            case E:
                return new Vector2d(1,0);
            case SW:
                return new Vector2d(-1,-1);
            case SE:
                return new Vector2d(1,-1);
            case NE:
                return new Vector2d(1,1);
        }
        return null;
    }
}
