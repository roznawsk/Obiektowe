package World;

import Entities.Animal.AnimalParameters;
import Entities.Animal.Genome;
import Basics.MapDirection;
import Basics.MapParams;
import Basics.Vector2d;
import Entities.Animal.Animal;
import Entities.Plant;

import java.util.*;

public class JungleMap implements IWorldObserver{

    protected Hashtable<Vector2d, AnimalSet> animalMap = new Hashtable<>();
    protected HashSet<Animal> animals = new HashSet<>();

    protected Hashtable<Vector2d, Plant> plantMap = new Hashtable<>();

    public JungleMap(AnimalSet initialAnimals){
        for(int i = MapParams.mapLL.x ; i <= MapParams.mapUR.x ; i++){
            for(int j = MapParams.mapLL.y ; j <= MapParams.mapUR.y ; j++){
                animalMap.put(new Vector2d(i, j), new AnimalSet());
            }
        }
        for(Animal animal : initialAnimals){
            this.place(animal);
            animal.setMap(this);
        }
        spawnPlants();
    }

    public void nextTurn(){
        run();
//        System.out.print("Run done,");
        eatPlants();
//        System.out.print("Process done,");
        breed();
//        System.out.print("Breed done");
//        System.out.println();
        spawnPlants();
    }

    private void eatPlants(){
        HashSet<Vector2d> processed = new HashSet<>();

        for(Animal animal : animals){
            if(processed.contains(animal.getPosition()))
                continue;
            if(plantMap.containsKey(animal.getPosition())){
                plantMap.remove(animal.getPosition());
                int currentEnergy = -1;
                LinkedList<Animal> sameEnergy = new LinkedList<>();
                for(Animal nextAnimal : animalMap.get(animal.getPosition())){
                    if(nextAnimal.getEnergy() > currentEnergy){
                        sameEnergy.clear();
                        sameEnergy.add(nextAnimal);
                        currentEnergy = nextAnimal.getEnergy();
                    }
                    else if(nextAnimal.getEnergy() == currentEnergy){
                        sameEnergy.add(nextAnimal);
                    }
                }
                for(Animal toFeed : sameEnergy){
                    toFeed.changeEnergy(AnimalParameters.getPlantEnergy()/sameEnergy.size());
                }
            }
        }
        processed.clear();
    }

    private void spawnPlants(){
        Random r = new Random();
        Vector2d pos = null;
        //Generate plant outside Jungle
        for(int i = 0 ; i < 100 ; i++){
            pos = Vector2d.randomBetween(MapParams.mapLL, MapParams.mapUR);
            if(!(pos.follows(MapParams.jungleLL) && pos.precedes(MapParams.jungleUR))
                    && !isOccupied(pos)){
                break;
            }
        }
        Plant plant;
        if(pos != null){
            plant = new Plant(pos);
            plantMap.put(pos, plant);
        }
        ArrayList<Vector2d> availablePositions = new ArrayList<>();
        for(int x = MapParams.jungleLL.x ; x <= MapParams.jungleUR.x ; x++) {
            for (int y = MapParams.jungleLL.y; y <= MapParams.jungleUR.y; y++) {
                if (!isOccupied(new Vector2d(x, y)))
                    availablePositions.add(new Vector2d(x, y));
            }
        }
        System.out.println(availablePositions.size());
        if(availablePositions.size() == 0)
            return;
        pos = availablePositions.get(r.nextInt(availablePositions.size()));
        plant = new Plant(pos);
        plantMap.put(pos, plant);
    }

    private void breed(){
        HashSet<Vector2d> processed = new HashSet<>();
        LinkedList<Animal> toAdd = new LinkedList<Animal>();
        for(Animal animal : animals){
            if(processed.contains(animal.getPosition()) || animalMap.get(animal.getPosition()).size() < 2)
                continue;
            processed.add(animal.getPosition());
            animalMap.get(animal.getPosition());
            Animal animal1 = new Animal(new AnimalParameters(null, null, -1));
            Animal animal2 = animal1;
            for(Animal foo : animalMap.get(animal.getPosition())){
                if(foo.getEnergy() > animal1.getEnergy()) {
                    animal2 = animal1;
                    animal1 = foo;
                }
                else if(foo.getEnergy() > animal2.getEnergy()){
                    animal2 = foo;
                }
            }
            Animal baby = createBaby(animal.getPosition(), animal1, animal2, processed);
            if(baby != null)
                toAdd.add(baby);
        }
        for(Animal animal : toAdd){
            place(animal);
        }
    }

    private Animal createBaby(Vector2d position, Animal par1, Animal par2, HashSet<Vector2d> processed){
        if(par1.getEnergy() < AnimalParameters.getStartEnergy()/2 || par2.getEnergy() < AnimalParameters.getStartEnergy()/2)
            return null;
        ArrayList<Vector2d> moves = new ArrayList<>();
        for(MapDirection dir : MapDirection.values()){
            Vector2d newPosition = MapParams.inMapVector(dir.toUnitVector().add(position));
            if(!isOccupied(newPosition) && !processed.contains(newPosition))
                moves.add(newPosition);
        }
        if(moves.size() == 0)
            return null;
        Random r = new Random();
        Vector2d babyPos = new Vector2d(moves.get(r.nextInt(moves.size())));
        int babyEnergy = (int) (0.25 * (par1.getEnergy() + par2.getEnergy()) );
        par1.changeEnergy((int) (-0.25 * par1.getEnergy()));
        par2.changeEnergy((int) (-0.25 * par2.getEnergy()));
        return new Animal(this, new AnimalParameters(babyPos, MapDirection.random(), babyEnergy,
                new Genome(par1.getGenome(), par2.getGenome())));
    }

    public void run(){
        HashSet<Animal> animalsCp = new HashSet<>(animals);
        for(Animal animal : animalsCp){
            animal.moveForward();
        }
    }

    public boolean place(Animal animal){
        if(!animal.getPosition().precedes(MapParams.mapUR) &&
                !animal.getPosition().follows(MapParams.mapLL)) {
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
        System.out.println(animal.getPosition());
        if(!animalMap.containsKey(animal.getPosition()))
            return;
        AnimalSet animalsAt = animalMap.get(animal.getPosition());
        animalsAt.remove(animal);
        animals.remove(animal);
    }

    public boolean isOccupied(Vector2d position){
        if(!(position.precedes(MapParams.mapUR) && position.follows(MapParams.mapLL))) return false;
        return(animalMap.get(position) != null || plantMap.get(position) != null);
    }

    public Object objectAt(Vector2d position){
        AnimalSet whoAt = animalMap.get(position);
        if(whoAt != null && whoAt.size() > 0) return whoAt;
        return plantMap.get(position);
    }

//    @Override
//    public void remove(IWorldMapElement element) {
//
//    }

    @Override
    public void positionChanged(Animal animal, Vector2d oldPos) {
        if(animal.getEnergy() <= 0){
            System.out.println(animal.getPosition() + "  " + oldPos);
            remove(animal);
            return;
        }
        animalMap.get(oldPos).remove(animal);
        animalMap.get(animal.getPosition()).add(animal);
    }
}
