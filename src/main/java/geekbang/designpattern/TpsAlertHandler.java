package geekbang.designpattern;

public class TpsAlertHandler extends AlertHandler{


    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        long tps = apiStatInfo.getErrorCount() / apiStatInfo.getDurationOfSeconds();

        if (tps > rule.getMatchedRule(apiStatInfo.getApi())){
            notification.notify();
        }

    }
}
