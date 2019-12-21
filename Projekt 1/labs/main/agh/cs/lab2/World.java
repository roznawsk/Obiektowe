package agh.cs.lab2;
import agh.cs.lab5.GrassField;
import agh.cs.lab5.RectangularMap;

import static java.lang.System.out;

public class World {
    public static void main(String[] args){
        try{
            /*BasicObjects.MoveDirection[] directions = new Basic.OptionsParser().parse(args);
            GrassField gField = new GrassField(3);
            gField.place(new MapObjects.Animal(gField, new BasicObjects.Vector2d(0,1)));
            gField.place(new MapObjects.Animal(gField, new BasicObjects.Vector2d(0,0)));
            gField.place(new MapObjects.Animal(gField, new BasicObjects.Vector2d(1,0)));
            gField.run(directions);
            out.println(gField.toString());*/
            out.println(MapDirection.N);
        }catch(IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        }

    }
}
