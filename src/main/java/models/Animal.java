package models;

public class Animal {
    private String name;
    private boolean is_predator;

    public Animal(String name, boolean is_predator){
        this.name = name;
        this.is_predator = is_predator;
    }

    public String getName() {
        return name;
    }

    public boolean getIs_predator() {
        return is_predator;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setIs_predator(boolean is_predator) {
        this.is_predator = is_predator;
    }

    @Override
    public String toString() {
        return String.format("Animal[name=%s, is_predator=%b]", this.name, this.is_predator);
    }
}
