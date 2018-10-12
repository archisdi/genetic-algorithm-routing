/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclerouting;

import java.util.ArrayList;

/**
 *
 * @author Archie
 */
public class VehicleRouting {

    public static ArrayList<population> AllGenerations = new ArrayList<>();
    public static ArrayList<node> NodeList = new ArrayList<>();

    public static void main(String[] args) {

        InsertData();      //Memasukkan Semua Data Node
        //ShowData();       //Menampilkan Seluruh Data Node
        //GetAllCon(0);      //Menampilkan Seluruh Jarak antara node yang di inputkan
        int tot = 20;

        Initialize(tot);
        int size = AllGenerations.size() - 1;
        for (int i = 0; i <= 100; i++) {
            size = AllGenerations.size() - 1;
            algoritm NewGen = new algoritm(AllGenerations.get(size));
            AllGenerations.add(NewGen.getNewGen());
        }
        ShowGenerations();

    }

    public static void InsertData() {
        NodeList.add(new node(0, 82, 76, 0)); //Depot
        NodeList.add(new node(1, 96, 44, 19));
        NodeList.add(new node(2, 50, 5, 21));
        NodeList.add(new node(3, 49, 8, 6));
        NodeList.add(new node(4, 13, 7, 19));
        NodeList.add(new node(5, 29, 89, 7));
        NodeList.add(new node(6, 58, 30, 12));
        NodeList.add(new node(7, 84, 39, 16));
        NodeList.add(new node(8, 14, 24, 6));
        NodeList.add(new node(9, 2, 39, 16));
        NodeList.add(new node(10, 3, 82, 8));
        NodeList.add(new node(11, 5, 10, 14));
        NodeList.add(new node(12, 98, 52, 21));
        NodeList.add(new node(13, 84, 25, 16));
        NodeList.add(new node(14, 61, 59, 3));
        NodeList.add(new node(15, 1, 65, 22));
        NodeList.add(new node(16, 88, 51, 18));
        NodeList.add(new node(17, 91, 2, 19));
        NodeList.add(new node(18, 19, 32, 1));
        NodeList.add(new node(19, 93, 3, 24));
        NodeList.add(new node(20, 50, 93, 8));
        NodeList.add(new node(21, 98, 14, 12));
        NodeList.add(new node(22, 5, 42, 4));
        NodeList.add(new node(23, 42, 9, 8));
        NodeList.add(new node(24, 61, 62, 24));
        NodeList.add(new node(25, 9, 97, 24));
        NodeList.add(new node(26, 80, 55, 2));
        NodeList.add(new node(27, 57, 69, 20));
        NodeList.add(new node(28, 23, 15, 15));
        NodeList.add(new node(29, 20, 70, 2));
        NodeList.add(new node(30, 85, 60, 14));
        NodeList.add(new node(31, 98, 5, 9));

        for (node n : NodeList) {
            for (int i = 0; i <= (NodeList.size() - 1); i++) {
                if (n.getID() != NodeList.get(i).getID()) {
                    n.addConnection(NodeList.get(i));
                }
            }
        }
    }

    public static void ShowData() {
        for (node n : NodeList) {
            System.out.println("Node " + n.getID());
            System.out.println("Coordinate : " + n.getX() + " ," + n.getY());
            System.out.println("Demand : " + n.getDemand());
            System.out.println();
        }
    }

    public static void GetAllCon(int i) {
        System.out.println("All Relative Distance for Node " + NodeList.get(i).getID());
        System.out.println();
        for (connection c : NodeList.get(i).getConnection()) {
            System.out.println("Node ID  :" + c.getNode().getID());
            System.out.println("Distance :" + c.getDistance());
            System.out.println();
        }
    }

    public static void Initialize(int n) {
        population p = new population(n, NodeList);
        p.Initialize();
        AllGenerations.add(p);
        p = null;

    }

    public static void ShowGenerations() {
        int i = 0;
        for (population p : AllGenerations) {
            i++;
            System.out.println("-------------------------------------------------- Generation " + i + " --------------------------------------------------");
            for (individual ind : p.getAllIndividu()) {
                for (node krom : ind.getAllChromosome()) {
                    System.out.print(krom.getID() + "  ");
                }
                System.out.println("  , Cost : " + ind.getCost() + ", Fitness : " + ind.getFitness());
            }
            System.out.println("-----------------------------");
            System.out.println("Rata-rata Fitness : " + p.getRataFitness());
            System.out.print("Best : ");
            for (node krom : p.getBest().getAllChromosome()) {
                System.out.print(krom.getID() + "  ");
            }
            System.out.println();
            System.out.print("Cost : " + p.getBest().getCost());
            System.out.println(" Fitness : " + p.getBest().getFitness());
            System.out.println();
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Generation 1 Best : ");
        for (node krom : AllGenerations.get(0).getBest().getAllChromosome()) {
            System.out.print(krom.getID() + "  ");
        }
        System.out.println(" , Cost : " + AllGenerations.get(0).getBest().getCost());
        System.out.println("---");
        System.out.println("Generation " + i + " Best   : ");
        for (node krom : AllGenerations.get(AllGenerations.size() - 1).getBest().getAllChromosome()) {
            System.out.print(krom.getID() + "  ");
        }
        System.out.println(" , Cost : " + AllGenerations.get(AllGenerations.size() - 1).getBest().getCost());
        System.out.println();
        double eff = (100 / (AllGenerations.get(AllGenerations.size() - 1).getBest().getCost())) * ((AllGenerations.get(0).getBest().getCost()) - (AllGenerations.get(AllGenerations.size() - 1).getBest().getCost()));
        System.out.println("Efficiency : " + eff + "%");
    }

}
