package com.alexscode.teaching.tap;

import java.util.ArrayList;
import java.util.List;

import com.alexscode.teaching.utilities.Element;

public class Pse implements TAPSolver {

    public double borneSupp(List<Integer> sol, List<Integer> ensemble){
        //TODO implémenter la borne supp, relaxation du glouton de base 
        double g = 1;
        return g;

    }

    @Override
    public List<Integer> solve(Instance ist){
        Objectives obj = new Objectives(ist);
        //Création de la solution (vide), de la listes de ratios (vide) 
        List<Integer> demo = new ArrayList<>();
        List<List<Integer>> noeuds = new ArrayList<>();
        List<Integer> ensemble = new ArrayList<>();


        for (int i = 0; i < ist.size; i++){
            ensemble.add(i);
        }

        TAPSolver solver = new testGloutonEtDistance();
        demo = solver.solve(Instance.readFile("./instances/f4_tap_1_400.dat", 6600, 540));
        double lb = demo.stream().mapToDouble(j -> ist.interest[j]).sum();


        
        while (ensemble != null){
            //TODO : pour chaque noeuds, calculer borneSupp ; si < lb, couper noeud;
            if (demo.isEmpty()){
                List<Integer> n = new ArrayList<>();
                List<Integer> n1 = new ArrayList<>();
                n.add(ensemble.get(0));
                ensemble.remove(0);
                noeuds.add(n);
                noeuds.add(n1);
            }else{
                for (List<Integer> e : noeuds) {
                    ub = 
                } 
            }

            //TODO : ajouter (exhaustivement) un objet (1 noeud père aura 2 noeuds fils, 2^n noeuds)
        }

        

        return demo;
    }
    
}
