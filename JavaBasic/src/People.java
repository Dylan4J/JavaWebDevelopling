import java.util.Objects;

public class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {//注意：这里的形参是一个Object对象
        if (this == obj){//第一步：直接判断两个对象是否引用指向同一个对象
            return true;
        }
        if (this == null) {//第二步：判断显示隐式参数是否为空
            return false;
        }
        if (this.getClass() != obj.getClass()) {//第三步：判断两个对象是否是同一类，注意这里判断的是不等于
            return false;
        }

        Worker worker = (Worker)obj;//第四步：将传进来的参数转换成和显式参数一样的对象
        return  (Objects.equals(name,worker.getName())//注意：这里调用了objects.equals()方法，相比于equals方法
                //这种方法更加完善
                && age ==worker.getAge()); //第五步：比较各个实例域是否相同
    }


}
