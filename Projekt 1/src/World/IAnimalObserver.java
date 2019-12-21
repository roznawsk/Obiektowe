package World;

import Utiity.Vector2d;
import Entities.Animal.Animal;

public interface IAnimalObserver {

    void positionChanged(Animal element, Vector2d oldPos);

}
