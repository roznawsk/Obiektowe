package World;

import Entities.Animal.Animal;

import static java.lang.System.out;

public class World {
    public static void main(String[] args){
        try{
            MapInitializer mapInitializer = new MapInitializer();
            JungleMap map = new JungleMap(mapInitializer);

            MapVisualizer2 visualizer = new MapVisualizer2(map);
            visualizer.visualize();
            for(int  i = 0 ; i < 1000 ; i ++){
                map.nextTurn();
                visualizer.visualize();
//                for(int j = 0 ; j < map.params.mapUR.x)
                Thread.sleep(50);
//                double avg = 0;
//                for(Animal animal : map.animals)
//                    avg += animal.getEnergy();
//                out.println("Sum: "+avg+ " Avg: "+ avg/map.animals.size() + " animals: " + map.animals.size());
            }
            out.println(map.animals.size());

        }catch(IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
