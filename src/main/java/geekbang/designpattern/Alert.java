package geekbang.designpattern;

import java.util.ArrayList;
import java.util.List;

public class Alert {

    private List<AlertHandler> alertHandlers = new ArrayList<>();

    private void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }
}
