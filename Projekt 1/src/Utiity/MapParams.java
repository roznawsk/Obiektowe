package Utiity;

import World.MapInitializer;

public class MapParams {
    public final int height;
    public final int width;

    public final Vector2d mapLL;
    public final Vector2d mapUR;
    public final Vector2d jungleLL;
    public final Vector2d jungleUR;

    public MapParams(MapInitializer init){
        this.height = init.getHeight();
        this.width = init.getWidth();
        this.mapLL = init.getMapLL();
        this.mapUR = init.getMapUR();
        this.jungleLL = init.getJungleLL();
        this.jungleUR = init.getJungleUR();
//        System.out.println(jungleLL + "   "+ jungleUR);
    }

    public Vector2d inMapVector(Vector2d vec){
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

