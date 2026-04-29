enum MedicalCondition{
    HEALTHY,
    SEA_SICK,
    PREGNANT,
    SPECIAL_DISABILITY;
}

public abstract class Customer {
    protected String name;
    protected int age;
    protected MedicalCondition medicalCondition;

    Customer(String name, int age, MedicalCondition medicalCondition) {
        this.name = name;
        this.age = age;
        this.medicalCondition = medicalCondition;
    }

    String getName() {
        return this.name;
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

    ChildCustomer(String name, int age, AdultCustomer guardian, MedicalCondition medicalCondition) {
        super(name, age, medicalCondition);
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

    AdultCustomer(String name, int age, String passportID, MedicalCondition medicalCondition) {
        super(name, age, medicalCondition);
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return super.toString() + "; Passport Number: " + this.passportID + " ||\n";
    }
}