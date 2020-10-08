package classes;

import java.util.*;

public class Ingredient implements java.io.Serializable{
    private String name;
    private String measurement;

    public Ingredient(String name, String measurement) {
        this.name = name;
        this.measurement = measurement;
    }

    public String getName() {
        return name;
    }

    public String getMeasurement() {
        return measurement;
    }
}