package classes;

import java.util.ArrayList;

/** Main state store for program. */
public class RecipeBook implements java.io.Serializable {
	private static int recipeID = 0;

	private ArrayList<Recipe> recipes;

	public RecipeBook() {
		this.recipes = new ArrayList<Recipe>();
	}
	
  /*
   * Creates a new recipe and adds to recipes list. 
   * How to use for the GUI squad:
   * 1. Get the name and description from the input and save them as strings.
   * 2. Look at ingredients. Get the input from the user for ingredients and create ingredient
		objects and store in an arraylist
   * 3. Do the same as #2, except just store the strings in an array.
   * 4. Call this createRecipe method on the recipe book and sit back and crack open a cold one.
   */
	public void createRecipe(
		String name, 
		String description, 
		ArrayList<Ingredient> ingredients, 
		ArrayList<String> steps ) {
			Recipe newRecipe = new Recipe(recipeID++, name, description, ingredients, steps);
			recipes.add(newRecipe);
	}

  /** ToDo */
	public void /** Change Return Type */ searchRecipe() {
		// ToDo: Maté
	}

	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
		this.recipeID++;
	}
}
