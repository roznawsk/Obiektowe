package World;

import Entities.Animal.Animal;
import java.util.ArrayList;

class AnimalSet extends ArrayList<Animal> {

    @Override
    public String toString() {
        if(this.size() > 0)
            return this.get(0).toString();
        return "";
    }

}
