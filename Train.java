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
        //this while loop statement ensures we never try to remove the locomotive
        while(current.getPrevious() != null) {
            //find it
            if(current.getElement().equals(car)) {
                //case1: caboose
                if(current.equals(this.caboose)) {
                    //does the train only have one car too though
                    if(current.equals(this.locomotive)) {
                        //so now we will have empty train
                        this.locomotive = null;
                        this.caboose = null;
                    }
                    else {
                        //its always okay to remove caboose
                        this.caboose = current.getPrevious();
                        this.caboose.setNext(null);
                        break;
                    }
                }
                //case2: anything in between
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
                        throw new TrainException("Cannot Remove Car");
                    }
                } 
            }
            if(current.getPrevious().equals(this.locomotive)) {
                //if we reached here it does not exist
                throw new TrainException("Cannot Remove Car - It does not exist");
            }
            //move through
            current = current.getPrevious();
        }
    }

    //method to try to remove a car
    //method to try to add a car
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
        String s = "";
        //go through the list
        DoubleNode<TrainCar> current = this.locomotive;
        while(current != null) {
            //if on the last car
            if(current.getNext() == null) {
                s += current.getElement().toString();
            }
            else {
                s += current.getElement().toString() + ", ";
            }
            current = current.getNext();
        }
        return s;
    }

    /*
    public static void main(String[] args) {
        Train myTrain = new Train();
        Reefer car1 = new Reefer(3, 10, "food");
        TrainCar car2 = new TrainCar(5, "food");
        TrainCar car3 = new TrainCar(5, "lumber");
        TrainCar car4 = new TrainCar(7, "lumber");
        TrainCar car5 = new TrainCar(5, "oil");
        TrainCar car6 = new TrainCar(3, "steel");
    }
     */
}
