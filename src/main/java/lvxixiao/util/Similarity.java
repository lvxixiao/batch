package lvxixiao.util;

import lvxixiao.bean.User;
import lvxixiao.config.JdbcConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Similarity {
  // 查询数据中年龄、性别、身高、体重最相似的10条数据
  public List<User> similarity() {

    int age = 5;
    List<User> simUser = new ArrayList<>();
    double maxSumSim = similarity(age,false, 0, simUser);
    maxSumSim = similarity(age,true,maxSumSim,simUser);

    System.out.println("最相似的10条数据相似度[0,9]为:" + maxSumSim);
    System.out.println(simUser);

    return simUser;
  }

  private double similarity(int age, boolean gender, double maxSumSim, List<User> simUser) {
    JdbcTemplate template =JdbcConfig.getJdbcTemplate();
    // 按年龄和性别检索数据，数据按身高体重降序排列
    String sql = "select name,round(age) as age, gender, round(height) as height,round(weight) as"
        + " weight from user where round(age) = ? and gender = ? order by round(height) desc, "
        + "round(weight) desc";
    Object[] args = new Object[2];
    args[1] = gender;

    for(int i = age ; i < 101 ; i++){
      args[0] = i;
      List<User> users = template.query(sql, args, new RowMapper<User>() {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
          User user = new User();
          user.setName(rs.getString(1));
          user.setAge(rs.getFloat(2));
          user.setGender(rs.getBoolean(3));
          user.setHeight(rs.getFloat(4));
          user.setWeight(rs.getFloat(5));
          return user;
        }
      });
      // 计算相似度
      Map<Double,List<User>> map = similarity(users);

      Set<Map.Entry<Double, List<User>>> set = map.entrySet();
      Iterator<Map.Entry<Double, List<User>>> it = set.iterator();
      if(it.hasNext()){
        Map.Entry<Double, List<User>> entry = it.next();
        double sumSim = entry.getKey();
        // 相似度比较
        if(maxSumSim < sumSim) {
          maxSumSim = sumSim;
          //清空原来的数据
          simUser.clear();
          //将新的最高相似度的数据写入。
          simUser.addAll(entry.getValue());
        }
      }
    }
    return maxSumSim;
  }

  /*
   *  调用这个接口，确保users已经按身高、体重排列，且age与gender相等
   *  height  weight
   *  170     50
   *  170     40
   *  160     50
   *  160     40
   */
  private Map<Double,List<User>> similarity(List<User> users) {

    double maxSumSim = 0;
    List<User> simUser = new ArrayList<>(10);
    for(int i = 0 ; i < users.size() - 9 ; i++){ // 从0开始，到size - 9 结束
      double sumSim = 0;
      for(int j = i + 1; j < i + 10; j++){
        sumSim = sumSim + Cosine.simCosine(users.get(i),users.get(j));
      }
      if(maxSumSim < sumSim){ // 如果当前总相似度比记录的相似度大，则更改数据.
        maxSumSim = sumSim;
        simUser.clear();
        for(int z = i ; z < (i+10) ; z++){
          simUser.add(users.get(z));
        }
      }
    }

    Map<Double,List<User>> map = new HashMap<>();
    map.put(maxSumSim,simUser);

    return map;
  }
}
