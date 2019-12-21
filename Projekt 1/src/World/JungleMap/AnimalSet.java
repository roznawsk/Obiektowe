package World.JungleMap;

import Entities.Animal.Animal;
import World.World;

import java.util.ArrayList;

public class AnimalSet extends ArrayList<Animal> {

    @Override
    public String toString() {
        if(this.size() > 0)
            return this.get(0).toString();
        return "";
    }

}
