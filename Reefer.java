/*
CS 1027B – Assignment 2
Name: Christian Tamayo
Student Number: 251 433 749
Email: ctamayo@uwo.ca
Created: Feb 10, 2024
*/

//inherits from TrainCar
public class Reefer extends TrainCar {
    //instance variables
    private int temp;

    //constructor
    public Reefer(int weight, int temp, String freight) {
        //init
        super(weight, freight);
        this.temp = temp;
    }

    //temp getter
    public int getTemp() {
        return this.temp;
    }

    //temp setter
    public void setTemp(int newTemp) {
        this.temp = newTemp;
    }

    //toString (reuse super class toString)
    public String toString() {
        String s = "";
        s += super.toString().replace(">", ", ") + this.temp + "C" + ">";
        return s;
    }

    //checks valid connections
    public boolean canConnect(TrainCar other) {
        //utilize super method to check freight and weight similarities
        if(super.canConnect(other)) {
            return true;
        }
        //if not, theres still a possibility other is a reefer with similar temperature
        else if(other instanceof Reefer) {
            //check if they are within 5 degrees of each other, and use late binding since we are determing type at runtime.
            if((this.temp - ((Reefer) other).temp) <= 5 && (this.temp - ((Reefer) other).temp) >= -5) {
                return true;
            }
            return false;
        }
        return false;
    }

    //defines equality for reefers
    public boolean equals(TrainCar other) {
        //check if both are reefers
        if(other instanceof Reefer) {
            //then check values - we can't use the super class because that one would return false if other is a Reefer, so in fact the assignment details are wrong.
            if(this.getWeight() == ((Reefer) other).getWeight() && this.temp == ((Reefer) other).temp && this.getFreight().equals(((Reefer) other).getFreight())) {
                return true;
            }
        }
        return false;
    }
}
