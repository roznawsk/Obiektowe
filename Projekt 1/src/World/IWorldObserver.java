package World;

import Basics.Vector2d;
import Entities.Animal.Animal;

public interface IWorldObserver {

    public void positionChanged(Animal element, Vector2d oldPos);

}
