import classes.*;
import java.util.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        ArrayList<Ingredient> is = new ArrayList<Ingredient>();
        ArrayList<String> steps = new ArrayList<String>();
        is.add(new Ingredient("Lettuce", "1 head"));
        steps.add("Cut the lettuce");
        Recipe salad = new Recipe("salad", "healthy meal", is, steps);
        RecipeBook rb = new RecipeBook();
        rb.addRecipe(salad);
        
        try {
            FileOutputStream fileOut = new FileOutputStream("test/test.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(rb);
            out.close();
            fileOut.close();
            System.out.println("Serialized at test/test.ser");
            System.out.println("Exit");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}