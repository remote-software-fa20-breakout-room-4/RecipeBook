package classes;

import java.util.ArrayList;

/** Main state store for program. */
public class RecipeBook implements java.io.Serializable {
	private static int recipeId = 0;

	private ArrayList<Recipe> recipes;

	public RecipeBook() {
		this.recipes = new ArrayList<Recipe>();
	}
	
  /** Creates a new recipe and adds to recipes list. */
	public void createRecipe() {
		// ToDo: Kaleb
	}

  /** ToDo */
	public void /** Change Return Type */ searchRecipe() {
		// ToDo: Maté
	}

	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
		this.id++;
	}
}
