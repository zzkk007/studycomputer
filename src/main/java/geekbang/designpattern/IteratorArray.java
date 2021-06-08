package geekbang.designpattern;

public class IteratorArray implements DynamicIterator{
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public String remove() {
        return null;
    }
}
