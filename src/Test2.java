import classes.*;
import java.util.*;
import java.io.*;

public class Test2 {
    public static void main(String[] args) {
        RecipeBook rb = null;
        try {
            FileInputStream fileIn = new FileInputStream("test/test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            rb = (RecipeBook) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized from test/test.ser");
            System.out.println("Exit");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found made a new one");
            rb = new RecipeBook();
        }
        rb.listAllRecipes();
    }
}