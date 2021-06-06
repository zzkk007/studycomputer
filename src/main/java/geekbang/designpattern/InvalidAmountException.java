package geekbang.designpattern;

public class InvalidAmountException extends Throwable {

    public InvalidAmountException(String exception) {
        System.out.println(exception);
    }
}
