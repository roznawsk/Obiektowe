package World;

import Entities.Animal.AnimalAttributes;
import Entities.Animal.Genome;
import Basics.MapDirection;
import Basics.MapParams;
import Basics.Vector2d;
import Entities.Animal.Animal;
import Entities.Plant;

import java.util.*;

public class JungleMap implements IWorldObserver{

    public final MapParams params;

    protected Hashtable<Vector2d, AnimalSet> animalMap = new Hashtable<>();
    protected HashSet<Animal> animals = new HashSet<>();

    protected Hashtable<Vector2d, Plant> plantMap = new Hashtable<>();

    private PlantGenerator plantGenerator = new PlantGenerator(this);
    private AnimalBreeder animalBreeder = new AnimalBreeder(this);
    private PlantEater plantEater = new PlantEater(this);

    public HashSet<Animal> getAnimals(){
        return new HashSet<>(animals);
    }

    public JungleMap(MapInitializer init){
        this.params = init.getParams();
        for(int i = params.mapLL.x ; i <= params.mapUR.x ; i++){
            for(int j = params.mapLL.y ; j <= params.mapUR.y ; j++){
                animalMap.put(new Vector2d(i, j), new AnimalSet());
            }
        }
        AnimalSet initialAnimals = init.generateRandomAnimals();
        for(Animal animal : initialAnimals){
            this.place(animal);
            animal.setMap(this);
        }
    }

    public void nextTurn(){
        plantGenerator.addPlants();
        run();
        plantEater.eatPlants();
        animalBreeder.breedAnimals();
    }

    public void run(){
        HashSet<Animal> animalsCp = new HashSet<>(animals);
        for(Animal animal : animalsCp){
            animal.moveForward();
        }
    }

    public boolean place(Animal animal){
        if(!animal.getPosition().precedes(params.mapUR) &&
                !animal.getPosition().follows(params.mapLL)) {
            throw new IllegalArgumentException("Field "+animal.getPosition()+" out of map!");
        }
//        System.out.println();
//        System.out.println(animal.getPosition());
        AnimalSet animalsAt = animalMap.get(animal.getPosition());
        animalsAt.add(animal);
        animalMap.put(animal.getPosition(), animalsAt);   //unnecessary after changes
        animals.add(animal);
        return true;
    }

    public void remove(Animal animal){
//        System.out.println(animal.getPosition());
        if(!animalMap.containsKey(animal.getPosition()))
            return;
        AnimalSet animalsAt = animalMap.get(animal.getPosition());
        animalsAt.remove(animal);
        animals.remove(animal);
    }

    public boolean isOccupied(Vector2d position){
        if(!(position.precedes(params.mapUR) && position.follows(params.mapLL))) return false;
        return animalMap.get(position).size() > 0 || plantMap.get(position) != null;
    }

    public Object objectAt(Vector2d position){
        AnimalSet whoAt = animalMap.get(position);
        if(whoAt != null && whoAt.size() > 0) return whoAt;
        return plantMap.get(position);
    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPos) {
        if(animal.getEnergy() <= 0){
//            System.out.println(animal.getPosition() + "  " + oldPos);
            remove(animal);
            return;
        }
        animalMap.get(oldPos).remove(animal);
        animalMap.get(animal.getPosition()).add(animal);
    }
}
