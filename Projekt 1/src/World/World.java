package World;

import Entities.Animal.Animal;
import World.JungleMap.JungleMap;

import static java.lang.System.out;

public class World {
    public static void main(String[] args){
        try{
            MapInitializer mapInitializer = new MapInitializer();
            JungleMap map = new JungleMap(mapInitializer);

            MapVisualizer2 visualizer = new MapVisualizer2(map);
            visualizer.visualize();
            int sleepTime = mapInitializer.getSleepTime();
            int simulationSteps = mapInitializer.getSimulationSteps();
            int max = Math.max(map.params.width, map.params.height);

            Thread.sleep(max * max / 5);

            for(int  i = 0 ; i < simulationSteps ; i ++){
                map.nextTurn();
                Thread.sleep(sleepTime);
                if(i % (simulationSteps / 25) == 0)
                    out.println("Day " + i + ": "+map.getAnimals().size() + " animals");
            }
            out.println("Day " + (simulationSteps - 1) + ": "+map.getAnimals().size() + " animals");
            JSONWriter jsonWriter = new JSONWriter();
            for(Animal survivor : map.getAnimals()){
                jsonWriter.writeLn(survivor.getGenome().toString());
            }
            jsonWriter.save();

        }catch(IllegalArgumentException ex) {
            System.out.println(ex);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
