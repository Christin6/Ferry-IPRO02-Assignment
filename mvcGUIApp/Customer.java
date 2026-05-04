import java.util.ArrayList;

enum MedicalCondition{
    HEALTHY,
    SEA_SICK,
    PREGNANT,
    SPECIAL_DISABILITY;
}

public abstract class Customer {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected ArrayList<MedicalCondition> medicalCondition;

    Customer(String firstName, String lastName, int age, ArrayList<MedicalCondition> medicalCondition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.medicalCondition = medicalCondition;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    ArrayList<MedicalCondition> getMedicalCondition(){
        return this.medicalCondition;
    }
    

    public String toString() {
        return "|| Name: " + this.firstName + " " + this.lastName + "; Age: " + this.age + "; Medical Condition: " + this.medicalCondition;
    }
}

class ChildCustomer extends Customer {
    private final double CHILD_FARE_MULTIPLIER = 0.7;
    private AdultCustomer guardian;

    ChildCustomer(String firstName, String lastName, int age, AdultCustomer guardian, ArrayList<MedicalCondition> medicalCondition) {
        super(firstName, lastName, age, medicalCondition);
        this.guardian = guardian;
    }

    void setGuardian(AdultCustomer guardian) {
        this.guardian = guardian;
    }

    double getChildFareMultiplier() {
        return CHILD_FARE_MULTIPLIER;
    }

    @Override
    public String toString() {
        return super.toString() + "; Guardian: " + this.guardian.getName() + " ||\n";
    }
}

class AdultCustomer extends Customer {
    private String passportID;

    AdultCustomer(String firstName, String lastName, int age, String passportID, ArrayList<MedicalCondition> medicalCondition) {
        super(firstName, lastName, age, medicalCondition);
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return super.toString() + "; Passport Number: " + this.passportID + " ||\n";
    }
}