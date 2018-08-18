package lvxixiao;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestFactory {

    @Test
    public void testProduceUser() {
        List<User> list = UserFactory.produceUser(1000000);
        System.out.println(list.size());
    }
}
