/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclerouting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Archie
 */
public class algoritm {

    private population PrevGeneration, NextGeneration;
    private ArrayList<individual> tempPop;
    private individual BestIndividu;

    public algoritm(population PrevGeneration) {
        this.PrevGeneration = PrevGeneration;
        this.NextGeneration = new population(this.PrevGeneration.getN(), this.PrevGeneration.getNodeList());
        this.tempPop = new ArrayList<>();
        this.BestIndividu = this.PrevGeneration.getBest();

        ParentSelection();
        Recombination();
        Mutation();
        Elitism();

        this.NextGeneration.setPopulation(tempPop);
        this.NextGeneration.setFitness();
        this.NextGeneration.setBest();
        Collections.sort(this.NextGeneration.getAllIndividu(), individual.CostCompare);
    }

    public population getNewGen() {
        return this.NextGeneration;
    }

    private void ParentSelection() {
        for (int i = 0; i <= (this.PrevGeneration.getAllIndividu().size() - 1); i++) {
            this.tempPop.add(RouletteWheel());

            if (i % 2 != 0) {
                while (this.tempPop.get(i) == this.tempPop.get(i - 1)) {
                    this.tempPop.set(i, RouletteWheel());
                }
            }
        }
    }

    private individual RouletteWheel() {
        individual chs = null;
        double random = Math.random();
        double range = 0;
        double prob = 0;
        for (int i = 0; i <= (this.PrevGeneration.getAllIndividu().size() - 1); i++) {
            prob = (1 / this.PrevGeneration.getTotalFitness()) * (this.PrevGeneration.getIndividu(i).getFitness());
            if ((random > range) && (random <= (range + prob))) {
                chs = this.PrevGeneration.getIndividu(i);
                break;
            } else {
                range = range + prob;
            }
        }
        return chs;
    }

    private void Recombination() {

        for (int i = 0; i <= this.tempPop.size() - 1; i = i + 2) {
            ArrayList<individual> newI = new ArrayList<>();
            newI = Combine(tempPop.get(i), tempPop.get(i + 1));
            tempPop.set(i, newI.get(0));
            tempPop.set(i + 1, newI.get(1));
        }
    }

    private ArrayList<individual> Combine(individual a, individual b) {
        ArrayList<individual> combined = new ArrayList<>();
        individual newA = new individual(), newB = new individual();

        newA.setDepot(a.getDepot());
        newB.setDepot(b.getDepot());

        if (GenerateRandom() <= 0.8) {
            //System.out.println("Combine");
            int cut = GenerateRandomINT();
            for (int i = 0; i <= cut; i++) {
                newA.addChromosome(a.getChromosome(i));
                newB.addChromosome(b.getChromosome(i));
            }

            for (int i = cut; i <= (a.getAllChromosome().size() - 1); i++) {
                for (int j = 0; j <= 30; j++) {
                    if (!newA.getAllChromosome().contains(b.getChromosome(j))) {
                        newA.addChromosome(b.getChromosome(j));
                        break;
                    }
                }
            }

            for (int i = cut; i <= (b.getAllChromosome().size() - 1); i++) {
                for (int j = 0; j <= 30; j++) {
                    if (!newB.getAllChromosome().contains(a.getChromosome(j))) {
                        newB.addChromosome(a.getChromosome(j));
                        break;
                    }
                }
            }

            newA.Calculate(); // Hitung Ulang Fitness dan Cost
            newB.Calculate();
            combined.add(newA);
            combined.add(newB);
        } else {
            //System.out.println("Not Combine");
            combined.add(a);
            combined.add(b);
        }
        return combined;
    }

    private void Mutation() {
        for (int i = 0; i <= (this.tempPop.size() - 1); i++) {
            tempPop.set(i, Mutate(tempPop.get(i)));
        }
    }

    private individual Mutate(individual ind) {
        individual NewI = new individual();
        node temp = null;
        int n1, n2;

        if (GenerateRandom() <= 0.1) {
            n1 = GenerateRandomINT();
            n2 = GenerateRandomINT();
            while (n1 == n2) {
                n1 = GenerateRandomINT();
                n2 = GenerateRandomINT();
            }
            temp = ind.getChromosome(n1);
            ind.getAllChromosome().set(n1, ind.getChromosome(n2));
            ind.getAllChromosome().set(n2, temp);

            NewI = ind;
            NewI.Calculate(); // Hitung Ulang Fitness dan Cost
        } else {
            NewI = ind;
        }
        return NewI;
    }

    private void Elitism() {
        this.tempPop.set((this.tempPop.indexOf(Collections.min(this.tempPop, individual.FitnessCompare))), BestIndividu);
        this.tempPop.set((this.tempPop.indexOf(Collections.min(this.tempPop, individual.FitnessCompare))), BestIndividu);
    }

    private double GenerateRandom() {
        double random = Math.random();
        return random;
    }

    private int GenerateRandomINT() {
        Random rand = new Random();
        return rand.nextInt(30) + 1;
    }

}
