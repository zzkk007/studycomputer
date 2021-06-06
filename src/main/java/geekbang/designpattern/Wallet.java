package geekbang.designpattern;


import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {

    private String id ;

    private long creatTime;

    private BigDecimal balance;

    private long balanceLastModifiedTime;

    public Wallet(){
        this.id = UUID.randomUUID().toString();
        this.creatTime = System.currentTimeMillis();
        this.balance = BigDecimal.ZERO;
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

    public void increaseBalance(BigDecimal increasedAmount) throws InvalidAmountException {
        if (increasedAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountException("...");
        }
        this.balance.add(increasedAmount);
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

    public void decreaseBalance(BigDecimal decreasedAmount) throws InvalidAmountException {
        if (decreasedAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountException("..");
        }

        if (decreasedAmount.compareTo(this.balance) < 0){
            throw new InvalidAmountException("");
        }
        this.balance.subtract(decreasedAmount);
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }






    public String getId() {
        return id;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getBalanceLastModifiedTime() {
        return balanceLastModifiedTime;
    }
}
