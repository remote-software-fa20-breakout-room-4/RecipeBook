import classes.RecipeBook;
import classes.Recipe;
import classes.Ingredient;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;


/**  This is an example of how you would deserialize the recipe book object */

public class ExampleReader {
    public static void main(String[] args) {
        RecipeBook recipeBook = null;
        try {
            FileInputStream fileIn = new FileInputStream("example/example.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            recipeBook = (RecipeBook) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized from example/example.ser");
            System.out.println("Exit");

            Recipe foundRecipe = recipeBook.searchRecipe("e");
	        System.out.println(foundRecipe.getName());
	        System.out.println(foundRecipe.getDescription());

        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Fix:");
            System.out.println("example/example.ser not found");
            System.out.println("Be sure to compile and run ExampleWriter.java first.");
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found. Made a new one");
            recipeBook = new RecipeBook();
        }
    }
}