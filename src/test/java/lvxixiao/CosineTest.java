package lvxixiao;

import lvxixiao.bean.User;
import lvxixiao.util.Cosine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CosineTest {

    @Test
    public void CosineTest(){
        User user1 = new User(160f,60f);
        User user2 = new User(150f,50f);
        System.out.println(Cosine.simCosine(user1,user2));
    }


}
