package com.alexscode.teaching.tap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.alexscode.teaching.utilities.Element;

public class Pse implements TAPSolver {

    /*RIP 
    public double borneSupp(List<Integer> sol, List<Integer> ensemble,Instance ist){
        Objectives obj = new Objectives(ist);


        //TEST

        //TODO implémenter la borne supp, relaxation du glouton de base 
        int meilleur = ensemble.get(0);
        for(int i = 1; i < ensemble.size();i++){
            if(ist.costs[meilleur]/ist.interest[meilleur] < ist.costs[ensemble.get(i)]/ist.interest[ensemble.get(i)]){
                meilleur = ensemble.get(i);
            }
        }
        double interet_obj_coupe = (ist.getMaxDistance() - obj.distance(sol))*ist.interest[meilleur];
        return (interet_obj_coupe + solution avec ce qu'il reste dans ensemble);


    }
    */


    @Override
    public List<Integer> solve(Instance ist){
        Objectives obj = new Objectives(ist);
        //Création de la solution (vide), de la listes de ratios (vide) 
        List<Integer> demo = new ArrayList<>();
        List<List<Integer>> noeuds = new ArrayList<>();
        List<List<Integer>> tempNoeuds = new ArrayList<>();
        List<Integer> ensemble = new ArrayList<>();

        

        for (int i = 0; i < ist.size; i++){
            ensemble.add(i);
        }

        TAPSolver solver = new testGloutonEtDistance();
        demo = solver.solve(Instance.readFile("./instances/f4_tap_0_20.dat", 330, 27));
        System.out.println(demo);
        double lb = demo.stream().mapToDouble(j -> ist.interest[j]).sum();
        List<Integer> n = new ArrayList<>();
        List<Integer> n1 = new ArrayList<>();
        n.add(ensemble.get(0));
        noeuds.add(n);
        noeuds.add(n1);
        ensemble.remove(0);
        
        while (ensemble.isEmpty() != true){
            for (int i = 0;i < noeuds.size();i++) {
                //double ub = borneSupp(e, ensemble,ist);
                //System.out.println(ub);
                /* 
                if (ub < lb){
                    noeuds.remove(e);
                }
                */
                //else{
                List<Integer> nfils = new ArrayList<>();
                List<Integer> nfils2 = new ArrayList<>();
                nfils.addAll(noeuds.get(i));
                nfils2.addAll(noeuds.get(i));
                nfils.add(ensemble.get(0));
                tempNoeuds.add(nfils2);
                //Réarangement des objets pour minimiser la distance:
                //rearranger(nfils, ist);
                //System.out.println(nfils);
                //
                if((obj.distance(nfils) <= ist.getMaxDistance() && obj.time(nfils) <= ist.getTimeBudget())){
                    tempNoeuds.add(nfils);
                }
                
                //}
            }
            noeuds.clear();
            noeuds.addAll(tempNoeuds);
            tempNoeuds.clear();
            ensemble.remove(0); 
            
        

        }

        
        double sol = 0;
    while(noeuds.size() != 1){
        for (int i = 0;i < noeuds.size();i++) {
            sol = noeuds.get(i).stream().mapToDouble(j -> ist.interest[j]).sum();
            if(sol > lb){
                lb = sol;
            }else{
                noeuds.remove(noeuds.get(i));
            }
        }
    }
        

        
       
        return noeuds.get(0);
    }

    //Heuristique qui réarrange mes noeuds pour pas qu'uils se fassent dézinguer 
    public List<Integer> rearranger(List<Integer> list,Instance ist){
        List<Integer> rearranged = new ArrayList<>();
        List<Double> distance = new ArrayList<>();
        for (int i = 0; i < list.size();i++){

        }

        return rearranged;

    }
}
    

