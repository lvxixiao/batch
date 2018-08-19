package lvxixiao;

import lvxixiao.config.LogConfig;
import org.junit.Test;

import java.io.IOException;

public class TestLog {
    @Test
    public void TestLog() throws IOException {
        LogConfig.logConfig("D:/workspace/batch/log");
    }
}
