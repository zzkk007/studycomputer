package books.javacoreone;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

    public static void main(String[] args) {

        List<String> linkedList = new LinkedList<>();

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");

//        Iterator<String> iterator = linkedList.iterator();
//        while (iterator.hasNext()){
//            //System.out.println(iterator.next());
//            iterator.next();
//            iterator.remove();
//        }

        for (String s: linkedList) {
            System.out.println(s);
        }
    }

}
