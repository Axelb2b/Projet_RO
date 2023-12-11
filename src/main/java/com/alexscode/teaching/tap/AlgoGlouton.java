package com.alexscode.teaching.tap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alexscode.teaching.utilities.Element;

public class AlgoGlouton implements TAPSolver{

    @Override
    public List<Integer> solve(Instance ist) {
        
        List<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);
        List<Element> ratios = new ArrayList<>();
        List<Element> voisins = new ArrayList<>();
        
        
        for (int i = 0; i< ist.size; i++){
            ratios.add(new Element(i,ist.costs[i]/ist.interest[i]));

        }

        Collections.sort(ratios);
        
         int index1 = 0;

        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()){
           
            demo.add(ratios.get(index1++).index);
            
        }
        demo.subList(0, demo.size() - 1);

    boolean meilleur = true;    
    while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget() && meilleur != false){
        List<Integer> sequence = demo;  
        double min_sol = sequence.stream().mapToDouble(j -> ist.interest[j]).sum();
        for(int i = 0; i < ist.getSize()-1;i++){
            Collections.swap(sequence, i, i+1);
            voisins.add(new Element(i, sequence.stream().mapToDouble(j -> ist.interest[j]).sum()));
        }
    Collections.sort(voisins);
    Collections.swap(demo, voisins.get(-1).index,voisins.get(-1).index+1 );
    if(min_sol == demo.stream().mapToDouble(j -> ist.interest[j]).sum() ){
        meilleur = false;
    }else{
        min_sol = demo.stream().mapToDouble(j -> ist.interest[j]).sum();
    }


     
            
}
return demo; 
    
    
}
}
