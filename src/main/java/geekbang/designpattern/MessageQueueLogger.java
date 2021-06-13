package geekbang.designpattern;

import java.io.IOException;
import java.util.logging.Level;

public class MessageQueueLogger extends Logger{

    private MessageQueueLogger messageQueueLogger;

    public MessageQueueLogger(String name, boolean enabled, Level minPermittedLevel, MessageQueueLogger messageQueueLogger) {
        super(name, enabled, minPermittedLevel);
        this.messageQueueLogger = messageQueueLogger;
    }

    @Override
    protected void doLog(Level level, String message) throws IOException {
        //messageQueueLogger.send();
    }
}
