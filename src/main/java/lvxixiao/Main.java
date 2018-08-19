package lvxixiao;

import lvxixiao.config.LogConfig;
import lvxixiao.util.Batch;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length < 3){
            System.out.println("请输入日志的路径以及要插入的数据量");
            System.exit(0);
        }
        LogConfig.logConfig(args[0]);
        Batch batch = new Batch();
        batch.batchInsert(Integer.parseInt(args[1]));
    }
}
