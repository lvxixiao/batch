package lvxixiao;

import lvxixiao.bean.User;
import lvxixiao.config.JdbcConfig;
import lvxixiao.util.Cosine;
import lvxixiao.util.Similarity;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimilarityTest {
    //10条数据相同
    /*
    @Test
    public void similarityTestList(){
        List<User> users = new ArrayList<>(20);

        User user1 = new User(170f,60f);
        users.add(user1);

        User user2 = new User(170f,60f);
        users.add(user2);

        User user3 = new User(160f,60f);
        for(int i = 0 ; i < 18 ; i++){
            users.add(user3);
        }
        Similarity sim = new Similarity();
        Map<Double,List<User>> map = sim.similarity(users);
        Set keys = map.keySet();
        keys.forEach(key -> {
            System.out.println(key);
            System.out.println(map.get(key).size());
        });
    }
    */
    @Test
    public void similarityTest(){
        Similarity sim = new Similarity();
        List<User> users = sim.similarity();
    }
}
