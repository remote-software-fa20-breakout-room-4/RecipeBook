import classes.RecipeBook;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;


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
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found. Made a new one");
            recipeBook = new RecipeBook();
        }
    }
}