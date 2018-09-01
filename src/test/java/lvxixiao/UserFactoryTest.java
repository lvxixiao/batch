package lvxixiao;

import lvxixiao.bean.User;
import lvxixiao.util.UserFactory;
import org.junit.Test;

import java.util.List;

public class UserFactoryTest {

  @Test
  public void testProduceUser() {
    List<User> list = UserFactory.produceUser(1000000);

    System.out.println(list.size());
  }
}
