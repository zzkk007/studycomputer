package books.javacoreone;

public class ManagerTest {

    public static void main(String[] args) {

        Manager boss = new Manager("zk", 1.00);
        boss.setBonus(2000000000.0);

        Employee[] staff = new Employee[2];

        staff[0] = boss;
        staff[1] = new Employee("ll", 29999.0);

        for (Employee e : staff){
            System.out.println("name:" +  e.getName() + ", salary= " + e.getSalary());
        }
    }
}
