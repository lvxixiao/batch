package lvxixiao;

import lvxixiao.bean.User;
import lvxixiao.config.JdbcConfig;
import lvxixiao.config.LogConfig;
import lvxixiao.util.Batch;
import lvxixiao.util.Similarity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    if(args.length < 2){
      System.out.println("请输入日志的路径以及要插入的数据量");
      System.exit(0);
    }
    LogConfig.logConfig(args[0]);
    Batch batch = new Batch();
    batch.batchInsert(Integer.parseInt(args[1]));
    //检索10条年龄、性别、身高、体重最接近的10条数据
    Similarity sim = new Similarity();
    sim.similarity();
  }
}
