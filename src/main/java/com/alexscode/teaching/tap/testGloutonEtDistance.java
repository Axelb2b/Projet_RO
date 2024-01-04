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
        List<Element> RatioNonPermanent = new ArrayList<>();
        List<Element> distancesAvecI = new ArrayList<>();
        List<Element> NewRatioPourI = new ArrayList<>();
        List<Element> NewRatioPourITri = new ArrayList<>();
        List<Element> ListeBoucle = new ArrayList<>();

        
        while (obj.distance(demo) < ist.getMaxDistance() && obj.time(demo) < ist.getTimeBudget()){
            RatioNonPermanent=ratios;
            ListeBoucle.add(ratios.get(0));
            for (int i =0; i<RatioNonPermanent.size()-1;i++){
                int identifiant1=ratios.get(i).index;
                for (int j=1;i<ratios.size();j++){
                    int identifiant2=ratios.get(j).index;
                    double distancesEntre1Et2 = ist.distances[identifiant1][identifiant2];
                    //distancesAvecI.add(new Element(i,distancesEntre1Et2));
                    double newRatio = ratios.get(j).value + distancesEntre1Et2;
                    NewRatioPourI.add(new Element(i,newRatio));
                }
                NewRatioPourITri=NewRatioPourI;
                Collections.sort(NewRatioPourITri);
                int meilleur=NewRatioPourITri.get(0).index;
                //chercher élément qui est en "meilleur" position dans la liste NewRatioPourI
                //faire en sorte que i devienne maintenant meilleur
                ListeBoucle.add(NewRatioPourITri.get(meilleur));

                //supprimer "i" de la liste RatioNonPermanent
                RatioNonPermanent.remove(ratios.get(i));

                //vider les listes NewRatioPourI et NewRatioPourITri
                NewRatioPourI.clear();
                NewRatioPourITri.clear();
            }    

           //mettre cette ligne
            demo.add(ListeBoucle.get(index1++).index);
            
        }
        List<Integer> sub = new ArrayList<>(demo.subList(0, demo.size()-1));





        return sub; 
        
    
    }
    
}
