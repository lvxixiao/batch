package lvxixiao.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcConfig {

  private JdbcConfig() {}

  private static final BasicDataSource dataSource = dataSource();

  private static BasicDataSource dataSource() {

    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName("com.mysql.jdbc.Driver");
    ds.setUrl("jdbc:mysql://localhost:3306/batch?characterEncoding=utf-8&useSSL=false");
    ds.setUsername("root");
    ds.setPassword("root");
    ds.setInitialSize(5);

    return ds;
  }

  private static class JdbcTemplateSingleton {
    private static final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public static JdbcTemplate getJdbcTemplate(){
        return JdbcTemplateSingleton.jdbcTemplate;
    }
}
