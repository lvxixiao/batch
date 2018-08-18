# 需求
1. 随机生成若干条用户记录，str必须包含中文且具有随机性
2. 请给出数据量为10万，100万，1000万三挡的批量操作的关键函数性能耗时、储存空间占用的性能报告
3. 请在注意代码可读性并在代码中注释清楚算法思路，确保代码在linux系统中可运行测试

加分需求：给出备份（导入、导出）当前数据库的程序or脚本

用户表字段：姓名(str12)，性别(bool)，婚否（bool），年龄(float)，身高(float)，体重(float)，
                   爱好(str60)，家庭地址(str60)，既往疾病史(str200)，生活习惯(str200)，运动目标（str60）
                   *str12表示字符长度为12

进阶需求一：在用户记录中查出年龄、性别、身高、体重最接近的10条记录

进阶需求二：采用数据挖掘算法实现找出相似度最接近的10条记录

# 数据库表设计

```
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
```

# 未能实现的需求

数据量10W的插入操作可以执行，但是执行速度不行。

使用Spring Jdbc的JdbcTemplate类中的batchUpdate(String,BatchPreparedStatementSetter)方法插入10W条数据，耗时2314915ms，约39分钟.

使用Spring Jdbc的JdbcTemplate类中的batchUpdate(String,List<Object[]>)方法插入10W条数据，耗时2243696ms，约38分钟.

虽然两个类方法实现不同，但效率差不多。

数据量100W的插入操作会引发堆内存溢出，可以将100W的操作拆分成10W进行，1000W的数据量同理。

未能将性能报告输出到文件中，考虑到需要到linux中运行，于是打算打开classpath中的log文件，将字符串写入文件，但是方法执行成功后字符串没有写入文件，原因未知。

存储空间性能报告，目前思路是记录每个创建的User对象(String属性字符串总长度 * 2 + float属性 * 4)占用byte的总和。

