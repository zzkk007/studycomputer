package books.javacoreone;

public class Employee implements Comparable<Employee>{

    private String name;

    private Double salary;

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void raiseSalary(double byPercent) {

        double raise = this.salary * byPercent / 100;
        salary += raise;
    }



    @Override
    public int compareTo(Employee o) {
        return Double.compare(this.salary, o.salary);
    }
}
