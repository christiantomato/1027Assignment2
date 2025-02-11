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
            //figure out where this car can be added

        }

    }

    //method
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

    public static void main(String[] args) {
        Train myTrain = new Train();
        TrainCar myCar = new TrainCar(10, "Wood");
        myTrain.addCar(myCar);
        System.out.println(myTrain);
    }
}
