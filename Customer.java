import java.util.ArrayList;

public abstract class Customer {
    protected String name;
    protected int age;
    protected ArrayList<FerryTrip> bookedTrip;

    Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return "Name: " + this.name + "\n" + "Age: " + this.age + "\n"; 
    }
}

class ChildCustomer extends Customer {
    private final double CHILD_FARE_MULTIPLIER = 0.7;
    private AdultCustomer guardian;

    ChildCustomer(String name, int age, AdultCustomer guardian){
        super(name, age);
        this.guardian = guardian;
    }

    @Override
    public String toString(){
        return super.toString() + "Guardian: " + this.guardian;
    }
}

class AdultCustomer extends Customer {
    private String passportID;
    
    AdultCustomer(String name, int age, String passportID){
    super(name, age);
    this.passportID = passportID;
  }

  @Override
  public String toString(){
    return super.toString() + "Passport: " + this.passportID;
  }
}