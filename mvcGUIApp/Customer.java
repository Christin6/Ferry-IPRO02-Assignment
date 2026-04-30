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
    protected MedicalCondition medicalCondition;

    Customer(String firstName, String lastName, int age, MedicalCondition medicalCondition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.medicalCondition = medicalCondition;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    MedicalCondition getMedicalCondition(){
        return this.medicalCondition;
    }
    

    public String toString() {
        return "|| Name: " + this.name + "; Age: " + this.age + "; Medical Condition: " + this.medicalCondition;
    }
}

class ChildCustomer extends Customer {
    private final double CHILD_FARE_MULTIPLIER = 0.7;
    private AdultCustomer guardian;

    ChildCustomer(String firstName, String lastName, int age, AdultCustomer guardian, MedicalCondition medicalCondition) {
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

    AdultCustomer(String firstName, String lastName, int age, String passportID, MedicalCondition medicalCondition) {
        super(firstName, lastName, age, medicalCondition);
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return super.toString() + "; Passport Number: " + this.passportID + " ||\n";
    }
}