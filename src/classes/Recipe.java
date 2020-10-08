package classes;

import java.util.ArrayList;

/** Holds all state on a recipe. Access by ID. */
public class Recipe implements java.io.Serializable {
	private int id;
	private String name;
	private String description;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<String> steps;

	/** Creates a new recipe object. */
	public Recipe(int id) { 
		this.id = id; 
		this.ingredients = new ArrayList<Ingredient>();
		this.steps = new ArrayList<String>();
	}

	/** I discourage this type of constructor for OOP, but add it for completeness. */
	public Recipe(
		int id,
		String name, 
		String description,
		ArrayList<Ingredient> ingredients, 
		ArrayList<String> steps
		) {
			this.id = id;
			this.name = name;
			this.description = description;
			this.ingredients = ingredients;
			this.steps = steps;
	}

	public void listAll() {
		System.out.println(id);
		System.out.println(name);
		System.out.println(description);

		for(Ingredient ingredient: ingredients) {
			System.out.println(ingredient.getName());
			System.out.println(ingredient.getMeasurement());
		}

		for(String step: steps) {
				System.out.println(step);
		}
	}

	public int getID() { return this.id; }

	public String getName() { return this.name; }

	public String getDescription() { return this.description; }

	public ArrayList<Ingredient> getIngredients() { return this.ingredients; }

	public ArrayList<String> getSteps() { return this.steps; }

	public void setName(String name) { this.name = name; }

	public void setDescription(String description) { this.description = description; }

	public void setIngredients(ArrayList<Ingredient> ingredients) { this.ingredients = ingredients; }

	public void setSteps(ArrayList<String> steps) { this.steps = steps; }
}