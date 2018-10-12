/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclerouting;

/**
 *
 * @author Archie
 */
public class connection {
    private node node;
    private double distance;

    public connection(node node, double distance) {
        this.node = node;
        this.distance = distance;
    }

    public node getNode() {
        return node;
    }

    public void setNode(node node) {
        this.node = node;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
}
