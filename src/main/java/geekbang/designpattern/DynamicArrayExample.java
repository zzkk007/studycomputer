package geekbang.designpattern;

public class DynamicArrayExample {

    public static void test(DynamicArray dynamicArray){
        dynamicArray.add(4);
        dynamicArray.add(1);
        dynamicArray.add(3);

        for (int i = 0; i < dynamicArray.size; i++) {
            System.out.println(dynamicArray.get(i));
        }
    }


    public static void main(String[] args) {
        DynamicArray dynamicArray = new SortedDynamicArray();
        test(dynamicArray);
    }
}
