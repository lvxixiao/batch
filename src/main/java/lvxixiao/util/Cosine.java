package lvxixiao.util;

import lvxixiao.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cosine {
    //使用余弦定理计算相似度
    public static double simCosine(User user1, User user2){
        //简化对比条件，年龄与性别不同则返回0
        if(user1.getAge() != user2.getAge() || (user1.isGender() != user2.isGender()))
            return 0;
        //身高为x轴，体重为y轴，忽略小数点后的数
        int x1 = Math.round(user1.getHeight());
        int x2 = Math.round(user2.getHeight());
        int y1 = Math.round(user1.getWeight());
        int y2 = Math.round(user2.getWeight());
        //两个向量夹角余弦的公式
        double sim = (x1*x2 + y1*y2) / (Math.sqrt((double)(x1*x1 + y1*y1)) * Math.sqrt((double)(x2*x2 + y2*y2)));

        return sim;
    }

}
