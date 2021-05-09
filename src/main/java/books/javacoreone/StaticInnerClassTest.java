package books.javacoreone;

/**
 * 静态内部类
 */
public class StaticInnerClassTest {

    public static void main(String[] args) {

        double [] d = new double[20];

        for (int i = 0; i < d.length; i++) {
            d[i] = 100 * Math.random();
        }

        ArrayAlg.Pair p = ArrayAlg.minMax(d);

        System.out.println(p.getFirst());
        System.out.println(p.getSecond());


    }
}

class ArrayAlg{


    public static class Pair{

        private double first;
        private double second;

        public Pair(double f, double s){
            first = f;
            second = s;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    public static Pair minMax(double[] values){
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double v : values){
            if(min > v) min = v;
            if(max < v) max = v;
        }
        return new Pair(min, max);
    }
}
