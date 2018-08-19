package lvxixiao;

import org.junit.Test;

public class TestJdbc {

    @Test
    public void testFind(){
        Batch batch = new Batch();
        batch.batchInsert2(10000000);
    }
}
