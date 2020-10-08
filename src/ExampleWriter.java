import classes.RecipeBook;
import classes.Recipe;
import classes.Ingredient;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.File;

/*
 * This creates 5 sample recipes, puts them into a recipe book and serializes them.
 * This example recipe book is saved to example/example.ser and is how we can test on a recipe book.
 */

public class ExampleWriter {
	public static void main(String[] args) {
		RecipeBook recipeBook = new RecipeBook();

		/**  Recipe 1. */
		Recipe recipe1 = new Recipe(1);
		ArrayList<Ingredient> ingredients1 = new ArrayList<Ingredient>();
		ArrayList<String> steps1 = new ArrayList<String>();

		ingredients1.add(new Ingredient("Lettuce", "1/2 head"));
		ingredients1.add(new Ingredient("Tomato", "1 medium"));
		ingredients1.add(new Ingredient("Croutons", "1 handful"));
		ingredients1.add(new Ingredient("Caeser Dressing", "1/2 cup"));

		steps1.add("Wash the lettuce and tomato");
		steps1.add("Cut the lettuce and tomato");
		steps1.add("Add lettuce, tomato, croutons, and dressing in large bowl");
		steps1.add("Mix well");
		steps1.add("Enjoy");
		
		recipe1.setName("Salad");
		recipe1.setDescription("A healthy, filling, meal for anytime of the day.");
		recipe1.setIngredients(ingredients1);
		recipe1.setSteps(steps1);

		/** Recipe 2. */
		Recipe recipe2 = new Recipe(2);
		ArrayList<Ingredient> ingredients2 = new ArrayList<Ingredient>();
		ArrayList<String> steps2 = new ArrayList<String>();

		ingredients2.add(new Ingredient("Potato Bun", "1"));
		ingredients2.add(new Ingredient("Shredded Lettuce", "1/2 cup"));
		ingredients2.add(new Ingredient("Tomato", "1 medium"));
		ingredients2.add(new Ingredient("Swiss Cheese", "1 slice"));
		ingredients2.add(new Ingredient("Ground Beef", "4 ounces"));
		ingredients2.add(new Ingredient("Ketchup", "1 tablespoon"));

		steps2.add("Wash the lettuce and tomato");
		steps2.add("Cut the tomato");
		steps2.add("Warm skillet");
		steps2.add("Form ground beef into a patty shape.");
		steps2.add("Cook patty on skillet for about 6 minutes on each side");
		steps2.add("Place patty on bun.");
		steps2.add("Add lettuce, tomato, swiss cheese, and ketchup on the patty");
		steps2.add("Enjoy");
		
		recipe2.setName("Burger");
		recipe2.setDescription("A classic american sandwich.");
		recipe2.setIngredients(ingredients2);
		recipe2.setSteps(steps2);

		/** Recipe 3. */
		Recipe recipe3 = new Recipe(3);
		ArrayList<Ingredient> ingredients3 = new ArrayList<Ingredient>();
		ArrayList<String> steps3 = new ArrayList<String>();

		ingredients3.add(new Ingredient("Rice", "3 cups"));
		ingredients3.add(new Ingredient("Soy Sauce", "1/4 cup"));
		ingredients3.add(new Ingredient("Green Onions", "3 stalks"));
		ingredients3.add(new Ingredient("Peas", "1/3 cup"));
		ingredients3.add(new Ingredient("Eggs", "2 medium"));
		ingredients3.add(new Ingredient("Garlic", "3 cloves"));
		ingredients3.add(new Ingredient("Seasame Oil", "2 tablespoon"));

		steps3.add("Cook the rice");
		steps3.add("Wash the green onions");
		steps3.add("Cut The garlic and green onions");
		steps3.add("Cook eggs");
		steps3.add("Heat wok and pour in seasame oil");
		steps3.add("Cook garlic and whites of green onions in wok for about 3 minutes");
		steps3.add("Add in rice and soy sauce");
		steps3.add("Cook for about 2 minutes");
		steps3.add("Add in peas, cooked eggs, and whites of green onion");
		steps3.add("Cook for another minute");
		steps3.add("Enjoy");
		
		recipe3.setName("Fried Rice");
		recipe3.setDescription("A chinese staple.");
		recipe3.setIngredients(ingredients3);
		recipe3.setSteps(steps3);

		/**  Recipe 4. */
		Recipe recipe4 = new Recipe(4);
		ArrayList<Ingredient> ingredients4 = new ArrayList<Ingredient>();
		ArrayList<String> steps4 = new ArrayList<String>();

		ingredients4.add(new Ingredient("Maruchan Ramen", "1 pack"));
		ingredients4.add(new Ingredient("Water", "1 cup"));

		steps4.add("Boil water in medium saucepan");
		steps4.add("Add in Ramen");
		steps4.add("Cook ramen for 3 minutes");
		steps4.add("Add in maruchan flavoring");
		steps4.add("Cook and stir for another minutes");
		steps4.add("Pour into bowl and enjoy");
		
		recipe4.setName("Ramen");
		recipe4.setDescription("A favorite amongst broke college students");
		recipe4.setIngredients(ingredients4);
		recipe4.setSteps(steps4);

		/** Add Recipes to the Book */
		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);
		recipeBook.addRecipe(recipe4);

		try {
			File dir = new File("example");
			dir.mkdir();
			FileOutputStream fileOut = new FileOutputStream("example/example.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(recipeBook);
			out.close();
			fileOut.close();
			System.out.println("Serialized at example/example.ser");
			System.out.println("Exit");
		} catch (IOException i) {
				i.printStackTrace();
		}
	}
}