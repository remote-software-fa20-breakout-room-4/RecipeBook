package classes;

import java.util.*;

public class Recipe implements java.io.Serializable {
    private static int id = 0;
    private String name;
    private String description;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> steps;

    public Recipe(
        String name, 
        String description,
        ArrayList<Ingredient> ingredients, 
        ArrayList<String> steps
        ) {
            this.id = id++;
            this.name = name;
            this.description = description;
            this.ingredients = ingredients;
            this.steps = steps;
    }

    public void listAll() {
        System.out.println(id);
        System.out.println(name);
        System.out.println(description);
        for(Ingredient ingredient: ingredients) {
            System.out.println(ingredient.getName());
            System.out.println(ingredient.getMeasurement());
        }
        for(String step: steps) {
            System.out.println(step);
        }
    }

}