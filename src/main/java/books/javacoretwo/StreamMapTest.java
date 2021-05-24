package books.javacoretwo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamMapTest {

    public static Stream<String> letters(String s){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i+1));
        }
        return  result.stream();
    }

    public static void main(String[] args) {

        String letters = "boat";

        List<String> wordList = new ArrayList<>();
        wordList.add("zhang");
        wordList.add("li");
        wordList.add("wang");


        Stream<String> lowerCaseWords = wordList.stream().map(String::toUpperCase);
        System.out.println(lowerCaseWords);

        Object[] objects = lowerCaseWords.toArray();
        for (Object o: objects){
            System.out.println(o.toString());
        }

        System.out.println("-------------");

        Stream<String> firstLetters = wordList.stream().map(s -> s.substring(0, 1));
        Object[] objects1 = firstLetters.toArray();
        for (Object o : objects1){
            System.out.println(o);
        }


    }

}
