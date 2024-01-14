package com.alexscode.teaching.tap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alexscode.teaching.utilities.Element;

public class Swap implements TAPSolver{
    @Override
    public List<Integer> solve(Instance ist) {
        
        ArrayList<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);
        List<Element> ratios = new ArrayList<>();
        //Liste des objets non sélectionnées par l'algo de liste
        List<Integer> pasDanssacADos = new ArrayList<>();
        ArrayList<Integer> demoCopy = new ArrayList<>();
        for(int i = 0;i < ist.size; i++){
            pasDanssacADos.add(i);
        }

        
        
        for (int i = 0; i< ist.size; i++){
            ratios.add(new Element(i,ist.costs[i]/ist.interest[i]));

        }

        Collections.sort(ratios);
        
        int index1 = 0;

        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()){
           
            demo.add(ratios.get(index1).index);
            pasDanssacADos.remove(pasDanssacADos.indexOf(ratios.get(index1).index));
            index1++;
            
        }
       
        demo.subList(0, demo.size() - 1);
    
    // Ajout de la recherche locale avec l'opérateur SWAP
    double min_sol = demo.stream().mapToDouble(j -> ist.interest[j]).sum();
    boolean meilleur = true;
    while (meilleur != false){ 
        for(int i = 0; i < demo.size();i++){
            demoCopy.clear();
            demoCopy.addAll(demo);
            for(int j = 0; j < pasDanssacADos.size(); j++){
                demoCopy.set(i,pasDanssacADos.get(j));
                if(obj.distance(demoCopy) < ist.getMaxDistance() && obj.time(demoCopy) < ist.getTimeBudget()){
                    if(min_sol < demoCopy.stream().mapToDouble(ju -> ist.interest[ju]).sum()){
                        min_sol = demoCopy.stream().mapToDouble(ju -> ist.interest[ju]).sum();
                        pasDanssacADos.remove(j);
                        pasDanssacADos.add(demoCopy.get(i));
                        demo.clear();
                        demo.addAll(demoCopy);
                    }
                }
            }
        }
        meilleur = false;
        
    }
    


return demo; 
    
    
}
}
    
