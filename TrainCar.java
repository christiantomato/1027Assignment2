/*
CS 1027B – Assignment 2
Name: Christian Tamayo
Student Number: 251 433 749
Email: ctamayo@uwo.ca
Created: Feb 10, 2024
*/

public class TrainCar {
    //instance variables as specified
    private int weight;
    private String freight;

    //constructor
    public TrainCar(int weight, String freight) {
        //initialize instance variables
        this.weight = weight;
        this.freight = freight;
    }

    //getters

    public int getWeight() {
        return this.weight;
    }

    public String getFreight() {
        return this.freight;
    }

    //setters

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    public void setFreight(String newFreight) {
        this.freight = newFreight;
    }

    //toString
    public String toString() {
        String s = "";
        s += "<" + this.freight + ", " + this.weight + ">";
        return s;
    }

    //checks valid connections
    public boolean canConnect(TrainCar other) {
        //they can connect if either weight or freight equal
        if(this.weight == other.weight || this.freight.equals(other.freight)) {
            return true;
        }
        return false;
    }

    //defines equality in TrainCars
    public boolean equals(TrainCar other) {
        //they are equal if share same weight and freight, and other isn't a reefer
        if(this.weight == other.weight && this.freight.equals(other.freight) && !(other instanceof Reefer)) {
            return true;
        }
        return false;
    }
}
