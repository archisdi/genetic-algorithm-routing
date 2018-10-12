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
public class node {

    private int ID;
    private double demand, X, Y;
    private ArrayList<connection> connection;

    public node(int ID, int X, int Y, double demand) {
        this.ID = ID;
        this.X = X;
        this.Y = Y;
        this.demand = demand;
        this.connection = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public void addConnection(node n) {
        connection c = new connection(n, calculateDistance(n));
        this.connection.add(c);
    }

    private double calculateDistance(node n) {
        return Math.sqrt(Math.pow(((n.getX() - this.X) + (n.getY() - this.Y)), 2));
    }

    public connection getConNode(node n) {
        connection ret = null;
        for (int i = 0; i <= (this.connection.size() - 1); i++) {
            if (this.connection.get(i).getNode() == n) {
                ret = this.connection.get(i);
                break;
            }
        }
        return ret;
    }

    public ArrayList<connection> getConnection() {
        return this.connection;
    }

}
