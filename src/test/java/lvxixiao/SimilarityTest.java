package lvxixiao;

import lvxixiao.bean.User;
import lvxixiao.util.Similarity;
import org.junit.Test;
import java.util.List;


public class SimilarityTest {

  @Test
  public void similarityTest(){

    Similarity sim = new Similarity();
    List<User> users = sim.similarity();
  }
}
