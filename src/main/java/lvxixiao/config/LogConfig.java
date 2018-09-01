package lvxixiao.config;

import java.io.*;
import java.time.LocalDate;


public class LogConfig {

  public static void logConfig(String logPath) throws IOException {

    File file = new File(logPath);

    if(!file.exists()) {
      throw new RuntimeException("文件路径不存在");
    }

    if(file.isDirectory()) { // 如果路径是目录，则根据日期在此目录下创建文件
      String newLogPath = file.getPath() + "/" + LocalDate.now();
      file = new File(newLogPath);
    }

    PrintStream ps = new PrintStream(file,"UTF-8");

    System.setOut(ps); // 设置System.out输出到文件
  }
}
