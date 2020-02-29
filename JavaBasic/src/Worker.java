public class Worker extends People implements Animal{
    int ID;

    public Worker(String name, int age,int ID) {
        super(name,age);
        this.ID = ID;
    }

    public void changName(String newName){
        super.setName(newName);
    }

    @Override
    public boolean equals(Object obj) {//注意：这里的形参是一个Object对象
        if (!super.equals(obj)){//第一步：这里先进行一次父类equals比较
            return false;
        }
        Worker worker = (Worker)obj;//第二步：这里进行一次转换
        return  this.ID == worker.ID;//第三步：比较子类中特有的成员属性
    }

    public static void main(String[] args) {
        Worker mike = new Worker("mike",25,001);
        System.out.println(mike.speak());
    }


}
