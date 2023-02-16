package guchi.the.hasky.reflection.victim;

public class Entity {
    private int weight;
    public Entity(){
        weight = 0;
    }
    public Entity(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
