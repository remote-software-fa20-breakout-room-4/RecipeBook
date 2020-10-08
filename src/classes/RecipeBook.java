package classes;

import java.util.ArrayList;
import java.util.Scanner; // For getting user input

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
		
		ArrayList<Recipe> foundRecipes = new ArrayList<Recipe>(); // We'll be keeping the search results in a Recipe array and we'll decide what to do based on how many we have

		for(int i = 0; i < this.recipes.size(); i++) {
			String currentRecipeName = this.recipes.get(i).getName();
			if(currentRecipeName.contains(searchedName)) { // Simple condition that accounts for exact and substring matching
				foundRecipes.add(recipes.get(i));
			}
		}

		if(foundRecipes.size() == 0) { // No results - don't know how to handle this yet, just exit gracefully for now
			System.exit(0);
		}
		else if(foundRecipes.size() == 1) { // Only one result, the easiest case - just return that one
			return foundRecipes.get(0);
		}
		else {
			for(int i = 0; i < foundRecipes.size(); i++) { // If multiple results, display all their IDs and names
				System.out.println(Integer.toString(i + 1) + ". " + foundRecipes.get(i).getName() + "\n"); // +1 so the list doesn't start with 0
			}
			Scanner resultDecision = new Scanner(System.in);
			System.out.println("Please enter ID number of selected recipe.\n"); // And ask the user to select one
			int selection = resultDecision.nextInt();

			return foundRecipes.get(selection - 1);

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
