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
        List<Element> ratiosTampon = new ArrayList<>();
        
        //création de la lite de ratios coût/interêt pour chaque objet (condition de l'algo de liste)
        for (int i = 0; i< ist.size; i++){
            ratios.add(new Element(i,ist.costs[i]/ist.interest[i]));
        }

        //Tri de la liste d'Element sur la valeur
        Collections.sort(ratios);

        int index1 = 0;
        //ajout du premier élément dans la solution
        demo.add(ratios.get(index1).index);

        double distance = 0;

        //boucle qui va remplir la solution jusqu'à la limite (coût ou distance)
        while (obj.distance(demo) <= ist.getMaxDistance() && obj.time(demo) <= ist.getTimeBudget()){
            int objASupp = demo.get(index1);
            //boucle pour supprimer de la liste l'élément qui vient d'être choisi
            for(int i  = 0; i<ratios.size();i++){
                if(ratios.get(i).index == objASupp){
                    ratios.remove(i);
                    break;
                }
            }

            ratiosTampon.addAll(ratios);

            //boucle qui ajoute la notion de distance dans le ratio

            for(int i = 0;i<ratios.size();i++){
                distance = ist.distances[demo.get(index1)][ratiosTampon.get(i).index];
                ratiosTampon.get(i).value += distance/ist.costs[i] ;
            }
            //Tri de la liste d'Element sur la valeur
            Collections.sort(ratiosTampon);
            //ajout du meilleur élément dans la solution
            demo.add(ratiosTampon.get(0).index);
            ratiosTampon.clear();
            index1++;
        } 

        //Enlever la requête qui fait "déborder" la solution
        List<Integer> sub = new ArrayList<>(demo.subList(0, demo.size()-1));
        return sub;
    }
    
}
