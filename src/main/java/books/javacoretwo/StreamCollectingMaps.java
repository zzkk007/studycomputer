package books.javacoretwo;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectingMaps {

    public static class Person {

        private int id;
        private String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return getClass().getName() + "[id= " + id + ",name= " + name + "]";
        }

        public static Stream<Person> people() {
            return Stream.of(new Person(1001, "peter"), new Person(1002, "Paul"), new Person(1003, "Mary"));
        }

        public static void main(String[] args) {

            Map<Integer, String> idToName =  people().collect(Collectors.toMap(Person::getId, Person::getName));

            Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));

        }
    }


}
