package books.javacoretwo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollectingResults {

    public static <T> void show(String label, Set<T> set){
        System.out.println(label + ":" + set.getClass().getName());
        System.out.println("[" + set.stream().limit(10).map(Objects::toString).collect(Collectors.joining(", ")) + "]");
    }


    public static void main(String[] args) {

        //迭代器
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        // toArray
        Object[] objects = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        for (Object o: objects){
            System.out.println(o.toString());
        }

        Integer[] numbers = Stream.iterate(0, n -> n + 2).limit(10).toArray(Integer[]::new);
        for (Integer i : numbers){
            System.out.println(i);
        }

        String contents = "zhangsan,lisi,wanger";

        List<String> worldList = Arrays.asList(contents.split(","));

        System.out.println("======================================");
        for (String str : worldList){
            System.out.println(str);
        }
        System.out.println("================================");

        TreeSet<String> streamTreeSet = worldList.stream().collect(Collectors.toCollection(TreeSet::new));
        show("streamTreeSet", streamTreeSet);

    }


}
