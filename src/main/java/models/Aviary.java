package models;

public class Aviary {
    private String location_Name;
    private int id;
    private int number;
    private  String[] array_Animals;
    public  Aviary(String location_Name, int id, int number, String[] array_Animals){
        this.location_Name = location_Name;
        this.id=id;
        this.number=number;
        this.array_Animals = array_Animals;
    }

    public String getLocation_Name() {
        return location_Name;
    }

    public int getId() {
        return id;
    }

    public void setLocation_Name(String location_Name) {
        this.location_Name = location_Name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String[] getArray_Animals() {
        return array_Animals;
    }

    public void setArray_Animals(String[] array_Animals) {
        this.array_Animals = array_Animals;
    }
}
