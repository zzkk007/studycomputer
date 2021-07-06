package geekbang.designpattern;

public abstract class TestCase {

    public void run(){

        if (doTest()){
            System.out.println("Test success.");
        }else {
            System.out.println("Test failed.");
        }

    }

    public abstract boolean doTest();

}
