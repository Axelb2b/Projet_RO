package com.alexscode.teaching.tap;

import java.util.ArrayList;
import java.util.List;

import com.alexscode.teaching.utilities.Element;

public class Pse implements TAPSolver {

    @Override
    public List<Integer> solve(Instance ist){
        Objectives obj = new Objectives(ist);
        //Création de la solution (vide), de la listes de ratios (vide) 
        List<Integer> demo = new ArrayList<>();
        List<Element> ratios = new ArrayList<>();

        return demo;
    }
    
}
