package geekbang.designpattern;

// 麻雀
public class Sparrow implements Tweetable, Egglayable{

    private FlyAbility flyAbility = new FlyAbility();  // 组合

    public void fly(){
        flyAbility.fly();       // 委托
    }

    @Override
    public void tweet() {

    }

    @Override
    public void layEgg() {

    }

    public static void main(String[] args) {

        Sparrow sparrow = new Sparrow();
        sparrow.fly();

    }
}
