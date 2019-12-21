package World;

import static java.lang.System.out;

public class World {
    public static void main(String[] args){
        try{
            JungleMap map = MapInitializer.getMap();

            MapVisualizer2 visualizer = new MapVisualizer2(map);
            visualizer.visualize();
            for(int  i = 0 ; i < 300 ; i ++){
                map.nextTurn();
                visualizer.visualize();
                Thread.sleep(0);
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
