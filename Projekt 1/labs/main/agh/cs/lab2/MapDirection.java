package agh.cs.lab2;

public enum MapDirection {
    N, NW, W, SW, S, SE, E, NE;
    public String toString(){
        switch(this){
            case E:
                return ">";
            case W:
                return "<";
            case N:
                return "\uD83E\uDC51";
            case S:
                return "\uD83E\uDC53";
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
    public Vector2d toUnitVector(){
        switch(this){
            case S:
                return new Vector2d(0,-1);
            case N:
                return new Vector2d(0,1);
            case W:
                return new Vector2d(-1,0);
            case E:
                return new Vector2d(1,0);
        }
        return null;
    }
}
