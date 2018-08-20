package sr.unasat.smn.entities;

public class Category {

    private String catName;

    public Category(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    @Override
    public String toString(){
        return getCatName();
    }
}
