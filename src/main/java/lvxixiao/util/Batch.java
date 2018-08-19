package lvxixiao.util;

import lvxixiao.config.JdbcConfig;
import lvxixiao.bean.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Batch {

    private int MaxNumber = 100000;

    //这个方法太慢，插入10W条数据用了39分钟.
    //batchUpdate(String,BatchPreparedStatementSetter)，单个PreparedStatement发送多个更新语句。
    @Deprecated
    public void batchInsert2(int number){
        JdbcTemplate template = JdbcConfig.getJdbcTemplate();

        long startTime = System.currentTimeMillis();
        List<User> list = UserFactory.produceUser(number);

        String insert = "insert into user(name," +
                "gender,isMarried," +
                "age,height,weight," +
                "hobby,address,disHistory,habit,target)" +
                " values(?,?,?,?,?,?,?,?,?,?,?)";


        template.batchUpdate(insert, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getName());
                ps.setBoolean(2, list.get(i).isGender());
                ps.setBoolean(3, list.get(i).isMarried());
                ps.setFloat(4, list.get(i).getAge());
                ps.setFloat(5, list.get(i).getHeight());
                ps.setFloat(6, list.get(i).getWeight());
                ps.setString(7, list.get(i).getHobby());
                ps.setString(8, list.get(i).getAddress());
                ps.setString(9, list.get(i).getDisHistory());
                ps.setString(10, list.get(i).getHabit());
                ps.setString(11, list.get(i).getTarget());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("插入"+number+"条数据耗时"+(endTime - startTime)+ "ms");
    }
    //batchUpdate(String,java.util.List<java.lang.Object[]>)使用提供的sql语句和一批提供的参数执行批处理
    public void batchInsert(int number){
        long startTime = System.currentTimeMillis();

        JdbcTemplate template = JdbcConfig.getJdbcTemplate();
        String insert = "insert into user(name," +
                "gender,isMarried," +
                "age,height,weight," +
                "hobby,address,disHistory,habit,target)" +
                " values(?,?,?,?,?,?,?,?,?,?,?)";
        int i = 0;
        if(number > MaxNumber)
            i = number / MaxNumber;
        //将number按照定义的数量拆分执行插入，避免堆溢出
        do{
            List<Object[]> values = UserFactory.produceObject(MaxNumber);
            template.batchUpdate(insert,values);
            i--;
        } while(i > 0);
        long endTime = System.currentTimeMillis();
        System.out.println(LocalDateTime.now()+":插入"+number+"条数据耗时"+(endTime - startTime)+ "ms");
    }

}
