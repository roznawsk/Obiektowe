package Basics;

import World.MapInitializer;

public class MapParams {
    public static final int height = MapInitializer.getHeight();
    public static final int width = MapInitializer.getWidth();

    public static final Vector2d mapLL = MapInitializer.getMapLL();
    public static final Vector2d mapUR = MapInitializer.getMapUR();
    public static final Vector2d jungleLL = MapInitializer.getJungleLL();
    public static final Vector2d jungleUR = MapInitializer.getJungleUR();

    public static Vector2d inMapVector(Vector2d vec){
        int x = vec.x;
        int y = vec.y;
        if(x < mapLL.x)
            x = mapUR.x;
        if(x > mapUR.x)
            x = mapLL.x;
        if(y < mapLL.y)
            y = mapUR.y;
        if(y > mapUR.y)
            y = mapLL.y;
        return new Vector2d(x,y);
    }

    @Override
    public String toString() {
        return String.format("Corners: "+ mapLL +", "+ mapUR +"%n"+
                "Jungle Corners: "+ jungleLL +", "+ jungleUR);
    }
}

