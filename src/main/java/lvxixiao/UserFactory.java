package lvxixiao;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserFactory {

    private static final Random random = new Random();

    /*
    private static final String logPath = UserFactory.class.getClassLoader().getResource("log").getPath();

    private static PrintWriter log;

    static {
        try {
            log = new PrintWriter(new FileWriter(new File(logPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    public static List<User> produceUser(int number){
        long startTime = System.currentTimeMillis();
        if(number > 1000000)
            throw new RuntimeException("超过100万的数量容易造成堆内存溢出");

        List<User> users = new ArrayList<User>(number);
        //创建User对象，并添加到list中
        for(int i = 0 ; i < number ; i++){
            //nextInt(int bound)函数的随机数范围为[0,bound)，因此需要加1
            // 长度 2-10
            int nameSize = randomSize(2,10 + 1);
            String name = randomChinese(nameSize);
            boolean gender = random.nextBoolean();
            boolean isMarried = random.nextBoolean();
            // 范围 5-100
            float age = random.nextFloat() * 95f + 5f;
            // 范围60-200f
            float height = random.nextFloat() * 140f + 60f;
            // 范围30-200f
            float weight = random.nextFloat() * 170f + 30f;
            // 长度0-60
            int hobbySize = randomSize(0,60+1);
            String hobby = randomChinese(hobbySize);
            // 长度10-60
            int addressSize = randomSize(10,50+1);
            String address = randomChinese(addressSize);
            // 长度0-200
            int hisSize = randomSize(0,200 + 1);
            String disHistory = randomChinese(hisSize);
            // 长度0-200
            int habitSize = randomSize(0,200+1);
            String habit = randomChinese(habitSize);
            // 长度0-60
            int targetSize = randomSize(0,60+1);
            String target = randomChinese(targetSize);
            User user = new User(name,gender,isMarried,age,height,weight,hobby,address,disHistory,habit,target);
            users.add(user);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("随机生成"+number+"条数据耗时"+(endTime - startTime)+ "ms");
        return users;
    }

    public static List<Object[]> produceObject(int number){
        long startTime = System.currentTimeMillis();
        if(number > 1000000)
            throw new RuntimeException("超过100万的数量容易造成堆内存溢出");

        List<Object[]> users = new ArrayList<Object[]>(number);
        //将User对象的属性写入Object[]中
        for(int i = 0 ; i < number ; i++){
            //nextInt(int bound)函数的随机数范围为[0,bound)，因此需要加1
            // 长度 2-10
            int nameSize = randomSize(2,10 + 1);
            String name = randomChinese(nameSize);
            boolean gender = random.nextBoolean();
            boolean isMarried = random.nextBoolean();
            // 范围 5-100
            float age = random.nextFloat() * 95f + 5f;
            // 范围60-200f
            float height = random.nextFloat() * 140f + 60f;
            // 范围30-200f
            float weight = random.nextFloat() * 170f + 30f;
            // 长度0-60
            int hobbySize = randomSize(0,60+1);
            String hobby = randomChinese(hobbySize);
            // 长度10-60
            int addressSize = randomSize(10,50+1);
            String address = randomChinese(addressSize);
            // 长度0-200
            int hisSize = randomSize(0,200 + 1);
            String disHistory = randomChinese(hisSize);
            // 长度0-200
            int habitSize = randomSize(0,200+1);
            String habit = randomChinese(habitSize);
            // 长度0-60
            int targetSize = randomSize(0,60+1);
            String target = randomChinese(targetSize);

            Object[] user = new Object[11];
            user[0] = name;
            user[1] = gender;
            user[2] = isMarried;
            user[3] = age;
            user[4] = height;
            user[5] = weight;
            user[6] = hobby;
            user[7] = address;
            user[8] = disHistory;
            user[9] = habit;
            user[10] = target;

            users.add(user);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("随机生成"+number+"条数据耗时"+(endTime - startTime)+ "ms");
        return users;
    }

    //随机数的范围[start,end)
    private static int randomSize(int start, int end){
        return random.nextInt(end) + start;
    }
    /*
     * 产生随机中文
     * UTF-8中文编码范围 u4e00-u9fa5 , 十进制范围为19968-40773
    */
    private static String randomChinese(int length){
        int[] result = new int[length];
        for (int i = 0 ; i < length ; i++){
            result[i] = random.nextInt(40773-19968 + 1)+ 19968;
        }
        return new String(result,0,length);
    }
}
