package geekbang.designpattern;

public class DynamicArray {

    private static final int DEFAULT_CAPACITY = 10;
    protected int size = 0;
    protected int capacity = DEFAULT_CAPACITY;
    protected Integer[] elements = new Integer[DEFAULT_CAPACITY];

    public int size(){
        return this.size;
    }

    public Integer get(int index){
        return elements[index];
    }

    public void add(Integer e){
        this.ensureCapacity();
        elements[size++] = e;
    }

    protected void ensureCapacity(){
        //...数组扩容
    }


}
