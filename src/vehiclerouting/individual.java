/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclerouting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Archisdiningrat
 */
public class individual {

    private ArrayList<node> chromosome;
    private final int capacity;
    private double cost, fitness;
    private node depot;

    public individual() {
        this.chromosome = new ArrayList<>();
        this.capacity = 100;
        this.cost = 0;
        this.fitness = 0;
    }

    public node getDepot() {
        return this.depot;
    }

    public void setDepot(node n) {
        this.depot = n;
    }

    public ArrayList<node> getAllChromosome() {
        return chromosome;
    }

    public node getChromosome(int i) {
        return this.chromosome.get(i);
    }

    public void addChromosome(node d) {
        this.chromosome.add(d);
    }

    public void setIndividu(ArrayList<node> individu) {
        this.chromosome = individu;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void initialize(ArrayList<node> node) {
        setDepot(node.get(0));
        for (int i = 1; i <= (node.size() - 1); i++) {
            this.chromosome.add(node.get(i));
        }
        Collections.shuffle(this.chromosome);
        Calculate();
    }

    private double setCost() {
        node CN = getDepot();
        double payload = 0, cst = 0;

        for (int i = 0; i <= (this.chromosome.size() - 1); i++) {
            payload = payload + CN.getDemand();
            cst = cst + CN.getConNode(this.chromosome.get(i)).getDistance();
            CN = this.chromosome.get(i);

            if (payload + CN.getDemand() > this.capacity) {
                cst = cst + this.chromosome.get(i - 1).getConNode(this.depot).getDistance();
                payload = 0;
                cst = cst + this.depot.getConNode(CN).getDistance();
            }
        }
        payload = payload + CN.getDemand();
        cst = cst + CN.getConNode(this.depot).getDistance();

        return cst;
    }

    private double setFitness(double cost) {
        double NewFit = 0;
        if (cost > 0) {
            NewFit = Math.floor((1000 / cost) * 10000) / 10000;
        } else {
            NewFit = Math.floor((1000 / 0.00000001) * 10000) / 10000;
        }

        return NewFit;
    }

    public void Calculate() {
        this.cost = setCost();
        this.fitness = setFitness(this.cost);
    }

    public double getCost() {
        return this.cost;
    }

    public double getFitness() {
        return this.fitness;
    }

    public static Comparator<individual> FitnessCompare = new Comparator<individual>() {

        @Override
        public int compare(individual c1, individual c2) {
            return Double.compare(c1.getFitness(), c2.getFitness());
        }

    };

    public static Comparator<individual> CostCompare = new Comparator<individual>() {

        @Override
        public int compare(individual c1, individual c2) {
            return Double.compare(c1.getCost(), c2.getCost());
        }

    };

}
