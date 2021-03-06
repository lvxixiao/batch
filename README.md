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
# 思路

main函数启动时接收日志路径以及生成的数据量。

System.out会被重定向为日志路径(如果是路径则按日期生成文件，如果是文件则直接写入文件)，需要注意的时文件每次程序写入都会被覆盖。

调用Btach类中的batchInsert(int)方法进行批量操作，为了避免堆溢出每次批量操作最多执行10W，超过10W的数据会被分批执行。

调用Similarity类中的similarity()方法，会查找数据中年龄、性别、身高、体重最接近的10条记录。

# 说明

数据量10W的插入操作可以执行，但是执行速度不行。

个人笔记本配置为i5-6300HQ 8GB内存 1T机械硬盘。运行时观察任务管理器，推测程序运行的瓶颈在机械硬盘的写入速度(800KB/秒)。

使用Spring Jdbc的JdbcTemplate类中的batchUpdate(String,BatchPreparedStatementSetter)方法插入10W条数据，耗时2314915ms，约39分钟.

使用Spring Jdbc的JdbcTemplate类中的batchUpdate(String,List<Object[]>)方法插入10W条数据，耗时2243696ms，约38分钟.

虽然两个类方法实现不同，但效率差不多。

数据量100W的插入操作会引发堆内存溢出，可以将100W的操作拆分成10W进行，1000W的数据量同理。

由于时间的关系，并没有对100W以及1000W的数据量进行测试。

存储空间性能报告，目前思路是记录每个创建的User对象(String属性字符串总长度 * 2 + float属性 * 4 + boolean属性 * 1)占用byte的总和。

# 进阶需求一说明

采用余弦定理计算相似度，将身高和体重映射为向量坐标系中的x轴与y轴。

为了简化计算，对于年龄，性别不同的两个user，相似度为0，并且浮点数均四舍五入取整。

