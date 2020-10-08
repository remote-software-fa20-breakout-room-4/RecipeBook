package classes;

import java.util.ArrayList;

/** Main state store for program. */
public class RecipeBook implements java.io.Serializable {
	private static int recipeID = 0;

	private ArrayList<Recipe> recipes;

	public RecipeBook() {
		this.recipes = new ArrayList<Recipe>();
	}
	
  /** Creates a new recipe and adds to recipes list. */
	public void createRecipe() {
		// ToDo: Kaleb
	}

  /** ToDo */
	public Recipe searchRecipe(String searchedName) {
	// ToDo: Máté

		for(int i = 0; i < recipes.size(); i++) {
			String currentRecipeName = this.recipes.get(i).getName();
			if(currentRecipeName.contains(searchedName)) {
				return recipes.get(i);
			}

		}
		return recipes.get(0);
	}

	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
		this.recipeID++;
	}

	public ArrayList getRecipes() {
		return this.recipes;
	}
}
