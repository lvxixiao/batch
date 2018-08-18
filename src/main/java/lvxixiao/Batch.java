package lvxixiao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Batch {

    /*

create table user(
    name varchar(12) not null,
    gender tinyint not null,
    isMarried tinyint not null,
    age float not null,
    height float not null,
    weight float not null,
    hobby varchar(60) not null,
    address varchar(60) not null,
    disHistory varchar(200) not null,
    habit varchar(200) not null,
    target varchar(60) not null
);
     */
    //这个方法太慢，插入10W条数据用了39分钟.
    //batchUpdate(String,BatchPreparedStatementSetter)，单个PreparedStatement发送多个更新语句。
    public void batchInsert(int number){
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
    public void batchInsert2(int number){
        JdbcTemplate template = JdbcConfig.getJdbcTemplate();

        long startTime = System.currentTimeMillis();
        String insert = "insert into user(name," +
                "gender,isMarried," +
                "age,height,weight," +
                "hobby,address,disHistory,habit,target)" +
                " values(?,?,?,?,?,?,?,?,?,?,?)";

        List<Object[]> values = UserFactory.produceObject(number);

        template.batchUpdate(insert,values);
        long endTime = System.currentTimeMillis();
        System.out.println("插入"+number+"条数据耗时"+(endTime - startTime)+ "ms");
    }

}
