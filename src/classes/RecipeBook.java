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

	/*
	The searchRecipe() function takes as input the search term as a string, then it returns the search results as an ArrayList of Recipes.
	For the GUI, there are three cases you need to handle:
		1. The arraylist is empty - no results
		2. The arraylist has one element - a single result, just retrieve that
		3. The arraylist has multiple elements - multiple results, prompt the user to decide which one they want to select
	*/
	public ArrayList<Recipe> searchRecipe(String searchName) {

		String searchedName = searchName.toLowerCase(); // Convert input to lowercase to get rid of case sensitivity
		ArrayList<Recipe> foundRecipes = new ArrayList<Recipe>(); // We'll be keeping the search results in a Recipe array that we return to the GUI
		System.out.println(searchedName);

		for(int i = 0; i < this.recipes.size(); i++) {
			String currentRecipeName = this.recipes.get(i).getName().toLowerCase(); // This goes all lowercase too
			if(currentRecipeName.contains(searchedName)) { // Simple condition that accounts for exact and substring matching
				foundRecipes.add(recipes.get(i)); // Add every finding to the arraylist
			}
		}

		return foundRecipes;
	}

	public void addRecipe(Recipe recipe) {
		this.recipes.add(recipe);
		this.recipeID++;
	}

	/*
	The exploreRecipe function takes as input a boolean value step-by-step
	and a Recipe object.
	This function returns an ArrayList with String objects.
	If step-by-step is true then the function returns an ArrayList with a header
	 and ingredients indexed the first bin and a new step for each subsequent index.
	If step-by-step is false the entire recipe is formatted and index in the first
	bin of the ArrayList
	*/
	public ArrayList<String> exploreRecipe(Boolean step_by_step, Recipe recipe){
		ArrayList<String> displayResults = new ArrayList<String>();
		//Build string for header
		String header = recipe.getName() + System.lineSeparator() + recipe.getDescription() + System.lineSeparator();
		//Build string for Ingredient List
		String ingredientList = "Ingredients:" + System.lineSeparator();
		for(Ingredient ingredient: recipe.getIngredients()){
			ingredientList += ingredient.getMeasurement() + " " + ingredient.getName() + System.lineSeparator();
		}
		if(step_by_step == true){ // if true then display recipe step-by-step
			displayResults.add(header);
			displayResults.add(ingredientList);
			for(String step: recipe.getSteps()){
				displayResults.add(step); //add each step on by one to return Array
			}
		} else { //display recipe in its entirety
			String entireRecipe = header + System.lineSeparator() + ingredientList + System.lineSeparator();
			//build list of steps
			String stepList = "Steps:" + System.lineSeparator();
			int i = 0;
			for(String step: recipe.getSteps()){
				stepList += String.valueOf(++i) + ": " + step + System.lineSeparator();
			}
			entireRecipe += stepList;
			displayResults.add(entireRecipe);
		}
		return displayResults;
	}

}
