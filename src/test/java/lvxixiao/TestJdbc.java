package lvxixiao;

import lvxixiao.config.LogConfig;
import lvxixiao.util.Batch;
import org.junit.Test;

import java.io.IOException;

public class TestJdbc {

    @Test
    public void testFind() throws IOException {
        LogConfig.logConfig("D:/workspace/batch/log");
        Batch batch = new Batch();
        batch.batchInsert(1000000);
    }
}
