package com.alexscode.teaching.tap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alexscode.teaching.utilities.Element;

public class testGloutonEtDistance implements TAPSolver{

    @Override
    public List<Integer> solve(Instance ist) {
        
        List<Integer> demo = new ArrayList<>();
        Objectives obj = new Objectives(ist);
        List<Element> ratios = new ArrayList<>();
        
        
        for (int i = 0; i< ist.size; i++){
            ratios.add(new Element(i,ist.costs[i]/ist.interest[i]));
        }

        Collections.sort(ratios);
        int index1 = 0;
        List<Double> differentesDistances = new ArrayList<>();
        List<Element> ratiosFinal = new ArrayList<>();
        
        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()){
           //calcule la distance entre les 2 éléments (éléments de la liste de ratios)
            for (int i =0; i<ratios.size()-1;i++){
                int identifiant1=ratios.get(i).index;
                int identifiant2=ratios.get(i+1).index;
                
                double distancesEntre1Et2 = ist.distances[identifiant1][identifiant2];
                differentesDistances.add(distancesEntre1Et2);
            }
           //refaire un ratio en impliquant la distance dans le calcul
            for (int i = 0; i< ratios.size()-1; i++){

                double resultat = ratios.get(i).value + ratios.get(i + 1).value + differentesDistances.get(i);
                ratiosFinal.add(new Element(i, resultat));

            }

           //mettre cette ligne
            demo.add(ratiosFinal.get(index1++).index);
            
        }
        demo.subList(0, demo.size() - 1);






        return demo; 
    
    }
    
}
