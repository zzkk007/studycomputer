package books.javacoreone;

import java.util.Arrays;

public class EmployeeSortTest {

    public static void main(String[] args) {

        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry Hacker", 35000.00);
        staff[1] = new Employee("Carl Cracker", 75000.00);
        staff[2] = new Employee("Tony Tester", 38000.00);

        Arrays.sort(staff);

        for (int i = 0; i < staff.length; i++) {
            System.out.println("name=" + staff[i].getName() + ", salary" + staff[i].getSalary());
        }






        System.out.println("jjj");


    }
}
