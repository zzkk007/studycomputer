package books.javacoreone;

public class Manager extends Employee{

    private double bonus;

    public Manager(String name, Double salary) {
        super(name, salary);
        bonus = 0;
    }

    public Double getSalary(){
        double baseSalary = super.getSalary();
        return baseSalary + this.bonus;
    }

    public void setBonus(double bonus){
        this.bonus = bonus;
    }
}
