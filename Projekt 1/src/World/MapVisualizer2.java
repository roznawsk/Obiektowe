package World;
import Utiity.Vector2d;
import Entities.Animal.Animal;
import World.JungleMap.JungleMap;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;


public class MapVisualizer2 extends JFrame implements IAnimalObserver, IPlantObserver{
    private JungleMap map;
    private JButton startSim;
    private JPanel interiorRepresentation;
    private Hashtable<Vector2d, JLabel> interior = new Hashtable<>();
    private int fontSize;

    public MapVisualizer2(JungleMap map) {
        this.map = map;
        setTitle("World simulation");
        setSize(1000, 500);
        getContentPane().setBackground(new Color(0x00002F));
        this.fontSize = (int) (80 / Math.sqrt(Math.max(map.params.height, map.params.width)));
        initInterior();
        for(Animal animal : map.getAnimals())
            animal.addObserver(this);
        map.addObserver(this);

    }

    private void initInterior(){
        interiorRepresentation = new JPanel();
        interiorRepresentation.setLayout(new GridLayout(map.params.height , map.params.width ));

        for(int i = map.params.mapUR.y ; i >= map.params.mapLL.y ; i--) {
            for (int j = map.params.mapLL.x; j <= map.params.mapUR.x; j++) {
                JLabel l = new JLabel(drawObject(new Vector2d(j, i)));
                l.setBorder(null);
                l.setForeground(new Color(0x00D0D2));
                l.setFont(new Font("Unicode", Font.PLAIN, fontSize));
                interior.put(new Vector2d(j, i), l);
                interiorRepresentation.add(l);
            }
        }
        interiorRepresentation.setBackground(new Color(0x000022));
    }

    private String drawObject(Vector2d vec){
        Object objectAt = map.objectAt(vec);
        if(objectAt != null)
            return objectAt.toString();
        return " ";
    }

    public void visualize(){
        this.getContentPane().removeAll();
        initInterior();
        add(interiorRepresentation);
        setVisible(true);
    }

    @Override
    public void positionChanged(Animal element, Vector2d oldPos) {
        interior.get(oldPos).setText(drawObject(oldPos));
        interior.get(element.getPosition()).setText(drawObject(element.getPosition()));
//        System.out.println("PC from " + oldPos + " to " + element.getPosition());
        interiorRepresentation.repaint();
    }

    @Override
    public void existenceUpdate(Vector2d position) {
        interior.get(position).setText(drawObject(position));
//        System.out.println("PC from " + oldPos + " to " + element.getPosition());
        interiorRepresentation.repaint();
    }
}
