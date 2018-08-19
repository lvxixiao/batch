package lvxixiao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {

    public void log() throws IOException {
        String logPath = UserFactory.class.getClassLoader().getResource("log").getPath();
        PrintWriter log = new PrintWriter(new FileWriter(logPath));
        log.println("测试写入");
        log.flush();
        log.close();
    }

}
