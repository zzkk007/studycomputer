package geekbang.designpattern;

public interface DynamicIterator {

    boolean hasNext();
    String next();
    String remove();
}
