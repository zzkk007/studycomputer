package books.javacoreone;

import java.util.Arrays;

/**
 * lambda 表达式
 */
public class LambdaTest {

    public static void main(String[] args) {

        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order:");
        Arrays.sort(planets, (first, second)-> first.length() - second.length());
        System.out.println(Arrays.toString(planets));
    }
}
