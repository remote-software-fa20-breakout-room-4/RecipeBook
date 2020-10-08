package classes;

import java.util.*;

public class RecipeBook implements java.io.Serializable {
	private ArrayList<Recipe> recipes;

	public RecipeBook() {
		recipes = new ArrayList<Recipe>();
	}
	
	public void createRecipe() {
		// ToDo: Kaleb
	}

	public void searchRecipe() {
		// ToDo: Maté
	}

	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
	}

	public void listAllRecipes() {
		for(Recipe recipe: recipes) {
			recipe.listAll();
		}
	}

}
