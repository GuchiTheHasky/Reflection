package guchi.the.hasky.reflection.victim;

public class Victim extends Entity implements VictimMethods {
    private String name;
    private int age;
    private boolean isHungry;

    public Victim() {
        super();
    }

    public Victim(String name, int age, boolean isHungry, int weight) {
        super(weight);
        this.name = name;
        this.age = age;
        this.isHungry = isHungry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    final void finalMethod(String name) {
        System.out.println("I am final method: " + name);
    }

    @Override
    public String toString() {
        return "Victim: \n" +
                "name: " + name + ", age: " + age + ", isHungry: " + isHungry + '.';
    }

    @Override
    public boolean isAngry() {
        return false;
    }

    @Override
    public void eat() {
        System.out.println("I want meat. I don't have any parameters.");
    }

    private void first() {
        System.out.println("I am private method. I don't have any parameters.");
    }

    private void second() {
        System.out.println("And I am private and don't have any parameters.");
    }

    private String third(int age) {
        return "I am private, my age is: " + age;
    }

    private void becomeHungry() {
        isHungry = true;
    }
}
