package sr.unasat.smn.entities;

public class Restaurant {

    private String name, location;
    private int weigth;
    private char label;

/*    public Restaurant(String name, String location, int weigth) {
        this.name = name;
        this.location = location;
        this.weigth = weigth;
    }*/

    public Restaurant(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getWeigth() {
        return weigth;
    }

    @Override
    public String toString(){
        return getName();
    }
}
