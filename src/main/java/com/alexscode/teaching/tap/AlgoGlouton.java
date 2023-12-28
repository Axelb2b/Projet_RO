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
    
    // Ajout de la recherche locale avec l'opérateur API 
    double min_sol = demo.stream().mapToDouble(j -> ist.interest[j]).sum();
    System.out.println(min_sol);   
    boolean meilleur = true;    
    while ( meilleur != false){
          
        
        for(int i = 0; i < demo.size()-1;i++){
            Collections.swap(demo, i, i+1);
            voisins.add(new Element(i, demo.stream().mapToDouble(j -> ist.interest[j]).sum()));
            Collections.swap(demo, i+1,i);
            System.out.println(voisins.get(i));
        }
        
        
        Collections.sort(voisins);
        Collections.swap(demo, voisins.get(voisins.size()-1).index,voisins.get(voisins.size()-1).index+1 );
        voisins.clear();

        if(min_sol == demo.stream().mapToDouble(j -> ist.interest[j]).sum() ){
            meilleur = false;
        }else{
            min_sol = demo.stream().mapToDouble(j -> ist.interest[j]).sum();

        }
        

     
            
}
//*/
return demo; 
    
    
}
}
