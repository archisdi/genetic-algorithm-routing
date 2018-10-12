/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclerouting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class population {
    private ArrayList<individual> population;
    private ArrayList<node> NodeList;    
    private double TotalFitness;
    private double RataFitness;
    private final int nIndividu;
    private individual Best;
    

    public population(int n, ArrayList<node> NodeList) {
        this.population = new ArrayList<>();
        this.NodeList = NodeList;
        this.nIndividu = n;
        this.Best = null;
        this.TotalFitness = 0;
        this.RataFitness = 0;
    }
    
    public void Initialize(){
        for (int i = 0; i <= this.nIndividu - 1; i++) {
            individual krom = new individual();
            krom.initialize(this.NodeList);
            this.population.add(krom);
        }
        setFitness();
        setBest();
    }
    
    public void addIndividu(individual ind){
        this.population.add(ind);
    }

    public ArrayList<individual> getAllIndividu() {
        return this.population;
    }
    
    public individual getIndividu(int n){
        return this.population.get(n);
    }

    public int getN(){
        return this.nIndividu;
    }
    
    public ArrayList<node> getNodeList(){
        return this.NodeList;
    }
    
    public void setFitness() {
        double total = 0;
        for (individual ind : this.population) {
            total = total + ind.getFitness();
        }
        this.TotalFitness =  total;
        this.RataFitness = this.TotalFitness / this.nIndividu;
    }
    
    public double getTotalFitness(){
        return this.TotalFitness;
    }
    
    public double getRataFitness(){
        return this.RataFitness;
    }
    
    public void setBest(){
        this.Best = Collections.min(this.population, individual.CostCompare);
    }
    public individual getBest(){
        return this.Best;
    }

    void setPopulation(ArrayList<individual> pop) {
        this.population = pop;
    }
    
    

}
