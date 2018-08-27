package lvxixiao.bean;

public class User {
    /*
    姓名(str12)，性别(bool)，婚否（bool），年龄(float)，身高(float)，体重(float)，
                   爱好(str60)，家庭地址(str60)，既往疾病史(str200)，生活习惯(str200)，运动目标（str60）
                   *str12表示字符长度为12
     */

    private String name; // 名字 长度2-12

    private boolean gender;  // 性别

    private boolean isMarried; // 婚否

    private float age; // 年龄 范围 5-100

    private float height; // 身高 范围60-200f

    private float weight; // 体重 范围30-200f

    private String hobby; // 爱好 长度0-60

    private String address; // 地址 长度10-60

    private String disHistory; // 既往病史 长度0-200

    private String habit; // 生活习惯 长度0-200

    private String target; // 运动目标 长度0-60

    public User() {
    }

    public User(float height, float weight) {
        this.height = height;
        this.weight = weight;
    }

    public User(String name, boolean gender, boolean isMarried, float age , float height, float weight, String hobby, String address, String disHistory, String habit, String target) {
        this.name = name;
        this.gender = gender;
        this.isMarried = isMarried;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.hobby = hobby;
        this.address = address;
        this.disHistory = disHistory;
        this.habit = habit;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisHistory() {
        return disHistory;
    }

    public void setDisHistory(String disHistory) {
        this.disHistory = disHistory;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name="+name+
                ", age=" + age +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
