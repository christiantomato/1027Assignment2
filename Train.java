/*
CS 1027B – Assignment 2
Name: Christian Tamayo
Student Number: 251 433 749
Email: ctamayo@uwo.ca
Created: Feb 21, 2024
*/

public class Train {
    //instance variables
    private DoubleNode<TrainCar> locomotive;
    private DoubleNode<TrainCar> caboose;

    //constructor
    public Train() {
        //init
        this.locomotive = null;
        this.caboose = null;
    }

    //getters

    public DoubleNode<TrainCar> getLocomotive() {
        return this.locomotive;
    }

    public DoubleNode<TrainCar> getCaboose() {
        return this.caboose;
    }

    //adds car to the doubly linked list
    public void addCar(TrainCar car) {
        //if train is empty
        if(this.locomotive == null) {
            //create the first node of the list
            DoubleNode<TrainCar> firstTrainPart = new DoubleNode<>(car);
            //set everything up
            this.locomotive = firstTrainPart;
            this.caboose = firstTrainPart;
            this.locomotive.setPrevious(null);
            this.caboose.setNext(null);
        }
        else {
            //figure out where this car can be added - start from back caboose and loop through
            DoubleNode<TrainCar> current = this.caboose;
            while(current != null) {
                //check if connection is possible
                if(car.canConnect(current.getElement())) {
                    //case 1: caboose
                    if(current.equals(this.caboose)) {
                        //make new node
                        DoubleNode<TrainCar> newCaboose = new DoubleNode<>(car);
                        //set its connections
                        newCaboose.setNext(null);
                        newCaboose.setPrevious(this.caboose);
                        //update old caboose next connection to new caboose
                        this.caboose.setNext(newCaboose);
                        //set it as new caboose
                        this.caboose = newCaboose;
                        break;
                    }
                    //case 2: anything in middle (must connect to both sides!)
                    else if(car.canConnect(current.getNext().getElement())) {
                        //now it is valid!
                        DoubleNode<TrainCar> newCar = new DoubleNode<>(car);
                        //set its connections
                        newCar.setNext(current.getNext());
                        newCar.setPrevious(current);
                        //update old connections
                        current.getNext().setPrevious(newCar);
                        current.setNext(newCar);
                        break;
                    }
                }
                //exception
                if(current.equals(this.locomotive)) {
                    //if we got here, then too bad so sad :(
                    throw new TrainException("Car Could Not Be Added.");
                }
                //move through
                current = current.getPrevious();
            }
        }
    }

    //method to try to add a car
    public boolean tryAddCar(TrainCar car) {
        //try to add the car in this method, so exceptions will be caught here
        try {
            this.addCar(car);
            //return true if success
            return true;
        } 
        catch (TrainException e) {
            //return false if not able to
            return false;
        }
    }

    //removes a car from the doubly linked list
    public void removeCar(TrainCar car) {
        //loop through and find it
        DoubleNode<TrainCar> current = this.caboose;

        //check for empty train
        if(current == null) {
            throw new TrainException("Train is empty");
        }

        //loop through train from back to front
        while(current != null) {
            //find it
            if(current.getElement().equals(car)) {
                //case1: caboose
                if(current.equals(this.caboose)) {
                    //its always okay to remove caboose
                    this.caboose = current.getPrevious();
                    this.caboose.setNext(null);
                    break;
                }
                //case2: locomotive
                else if(current.equals(this.locomotive)) {
                    //its always okay I guess
                    this.locomotive = current.getNext();
                    this.locomotive.setPrevious(null);
                    break;
                }
                //case3: anything in between
                else {
                    //check if the removal will be okay
                    if(current.getPrevious().getElement().canConnect(current.getNext().getElement())) {
                        //we can remove (by letting it float off into cyberspace)
                        current.getPrevious().setNext(current.getNext());
                        current.getNext().setPrevious(current.getPrevious());
                        break;
                    }
                    else {
                        //we cannot remove
                        throw new TrainException("Removing violates connections");
                    }
                } 
            }
            
            if(current.equals(this.locomotive)) {
                //if we reached here it does not exist
                throw new TrainException("Car does not exist");
            }
            //move through
            current = current.getPrevious();
        }
    }

    //method to try to remove a car
    public boolean tryRemoveCar(TrainCar car) {
        //try to add the car in this method, so exceptions will be caught here
        try {
            this.removeCar(car);
            //return true if success
            return true;
        } 
        catch (TrainException e) {
            //return false if not able to
            return false;
        }
    }

    //toString
    public String toString() {
        //if train empty
        if(this.locomotive == null) {
            return "The train is empty";
        }

        String s = "";
        //go through the list
        DoubleNode<TrainCar> current = this.locomotive;
        while(current != null) {
            //if on the last car
            if(current == this.caboose) {
                s += current.getElement().toString();
            }
            else {
                s += current.getElement().toString() + ", ";
            }
            current = current.getNext();
        }
        return s;
    } 
}
