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
        Objectives obj = new Objectives(ist);
        //Création de la solution (vide), de la listes de ratios (vide) 
        List<Integer> demo = new ArrayList<>();
        List<Element> ratios = new ArrayList<>();
        
        //création de la lite de ratios coût/interêt pour chaque objet (condition de l'algo de liste)
        for (int i = 0; i< ist.size; i++){
            ratios.add(new Element(i,ist.costs[i]/ist.interest[i]));
        }

        //Tri de la liste d'Element sur la valeur
        Collections.sort(ratios);
        //compteur de sortie pour parcourir
        int index1 = 0;

        //boucle qui va remplir la solution  jusqu'à la limite (coût ou distance)
        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()){
            demo.add(ratios.get(index1++).index);
            System.out.println(obj.distance(demo));
            System.out.println(obj.time(demo));    
        }
        //Enlever la requête qui fait "déborder" là solution
        List<Integer> sub = new ArrayList<>(demo.subList(0, demo.size()-1));



    

return sub; 
    
    
}
}
