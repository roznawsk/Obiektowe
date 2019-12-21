package Entities.Animal;

import Basics.MapDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genome {
    ArrayList<MapDirection> genome = new ArrayList();


    //Constructors
    public Genome(){
        MapDirection[] directions = MapDirection.values();
        Random r = new Random();
        for(int i = 0 ; i < 8 ; i++)
            genome.add(directions[i]);
        while(genome.size() < 32)
            genome.add(directions[r.nextInt(8)]);
        Collections.sort(genome);
    }

    public Genome(Genome g){
        this.genome = new ArrayList<>(g.genome);
    }

    public Genome(Genome genome1, Genome genome2){
        Random r = new Random();
        Genome g1;
        Genome g2;
        if(r.nextInt(2) == 0){
            g1 = genome1;
            g2 = genome2;
        }
        else{
            g2 = genome1;
            g1 = genome2;
        }
        MapDirection[] directions = MapDirection.values();
        int cut1 = r.nextInt(30) + 1, cut2 = r.nextInt(31 - cut1) + cut1 + 1;
        for(int i = 0; i < cut1 ; i ++)
            genome.add(g1.genome.get(i));
        for(int i = cut1 ; i < cut2 ; i++)
            genome.add(g2.genome.get(i));
        for(int i = cut2 ; i < 32 ; i++)
            genome.add(g1.genome.get(i));
        int who = r.nextInt(2);
        Collections.sort(genome);
        for(MapDirection direction : directions){
            if(!genome.contains(direction)){
                int index = r.nextInt(30) + 1;
                if(genome.get(index).equals(genome.get(index + 1)) || genome.get(index).equals(genome.get(index - 1))){
                    genome.set(index, direction);
                    Collections.sort(genome);
                }
            }
        }
    }

    @Override
    public String toString() {
        return genome.toString();
    }
}
