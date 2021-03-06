package lvxixiao.util;

import lvxixiao.bean.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserFactory {

  private static final Random random = new Random();

  public static List<User> produceUser(int number) {
    long startTime = System.currentTimeMillis();

    if(number > 1000000)
      throw new RuntimeException("超过100万的数量容易造成堆内存溢出");

    List<User> users = new ArrayList<User>(number);
    for(int i = 0 ; i < number ; i++){ // 创建User对象，并添加到list中
      int nameSize = randomSize(2,10 + 1); // 长度 2-10
      String name = randomChinese(nameSize);
      boolean gender = random.nextBoolean();
      boolean isMarried = random.nextBoolean();
      float age = random.nextFloat() * 95f + 5f; // 范围 5-100
      float height = random.nextFloat() * 140f + 60f; // 范围60-200f
      float weight = random.nextFloat() * 170f + 30f; // 范围30-200f
      int hobbySize = randomSize(0,60+1); // 长度0-60
      String hobby = randomChinese(hobbySize);
      int addressSize = randomSize(10,50+1); // 长度10-60
      String address = randomChinese(addressSize);
      int hisSize = randomSize(0,200 + 1); // 长度0-200
      String disHistory = randomChinese(hisSize);
      int habitSize = randomSize(0,200+1); // 长度0-200
      String habit = randomChinese(habitSize);
      int targetSize = randomSize(0,60+1); // 长度0-60
      String target = randomChinese(targetSize);
      User user = new User(name, gender, isMarried, age, height, weight, hobby, address,
          disHistory, habit, target);
      users.add(user);
    }

    long endTime = System.currentTimeMillis();

    System.out.println("随机生成" + number + "条数据耗时" + (endTime - startTime) + "ms");

    return users;
  }

  public static List<Object[]> produceObject(int number) {
    long startTime = System.currentTimeMillis();

    if(number > 1000000)
      throw new RuntimeException("超过100万的数量容易造成堆内存溢出");

    long memory = 0L; // 记录数据占用的字节数
    List<Object[]> users = new ArrayList<Object[]>(number);
    for(int i = 0 ; i < number ; i++){ // 将User对象的属性写入Object[]中
      int nameSize = randomSize(2,10 + 1); // 长度 2-10
      String name = randomChinese(nameSize);
      boolean gender = random.nextBoolean();
      boolean isMarried = random.nextBoolean();
      float age = random.nextFloat() * 95f + 5f; // 范围 5-100
      float height = random.nextFloat() * 140f + 60f; // 范围60-200f
      float weight = random.nextFloat() * 170f + 30f; // 范围30-200f
      int hobbySize = randomSize(0,60+1); // 长度0-60
      String hobby = randomChinese(hobbySize);
      int addressSize = randomSize(10,50+1); // 长度10-60
      String address = randomChinese(addressSize);
      int hisSize = randomSize(0,200 + 1); // 长度0-200
      String disHistory = randomChinese(hisSize);
      int habitSize = randomSize(0,200+1); // 长度0-200
      String habit = randomChinese(habitSize);
      int targetSize = randomSize(0,60+1); // 长度0-60
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
      //计算占用字节数 字符串总长度 * 2 + 3 个 float占用内存 + 2 个 boolean占用内存
      memory = memory + (nameSize + hobbySize + addressSize + hisSize + habitSize + targetSize)
          * 2L + 3L * 4L + 2L * 1L;
    }

    long endTime = System.currentTimeMillis();

    System.out.println(LocalDateTime.now()+":随机生成"+number+"条数据占用"+memory+"字节的内存");
    System.out.println(LocalDateTime.now()+":随机生成"+number+"条数据耗时"+(endTime - startTime)+ "ms");

    return users;
  }

  //

  // 产生随机数，随机数的范围[start,end)
  private static int randomSize(int start, int end){
    return random.nextInt(end) + start;
    }

  /*
  * 产生随机中文
  * UTF-8中文编码范围 u4e00-u9fa5 , 十进制范围为19968-40773
  */
  private static String randomChinese(int length) {
    int[] result = new int[length];
    for (int i = 0 ; i < length ; i++){
      result[i] = random.nextInt(40773-19968 + 1)+ 19968;
    }
    return new String(result,0,length);
  }
}
