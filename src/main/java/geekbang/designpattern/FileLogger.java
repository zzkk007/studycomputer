package geekbang.designpattern;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;

public class FileLogger extends Logger{

    private Writer fileWriter;


    public FileLogger(String name, boolean enabled, Level minPermittedLevel, String filePath) throws IOException {
        super(name, enabled, minPermittedLevel);
        this.fileWriter = new FileWriter(filePath);
    }

    @Override
    protected void doLog(Level level, String message) throws IOException {
        fileWriter.write("");
    }
}
