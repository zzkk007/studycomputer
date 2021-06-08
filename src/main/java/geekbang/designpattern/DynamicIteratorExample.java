package geekbang.designpattern;

public class DynamicIteratorExample {

    public static void print(DynamicIterator iterator) {
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    public static void main(String[] args) {

        DynamicIterator arrayIterator = new IteratorArray();
        print(arrayIterator);

        DynamicIterator linkedListIterator = new IteratorLinkedList();
        print(linkedListIterator);
    }
}
