import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

import classes.Recipe;
import classes.RecipeBook;
import classes.Ingredient;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Arrays;


public class GUI implements ActionListener{
		private JFrame frame;
		private JLabel label;
		private JPanel creationPanel;
		private JPanel searchPanel;
		private JPanel recipesPanel;
		private JPanel outerPanel;
		private FlowLayout layout;
		private JList list;
		private JTextField search;
		private JDialog displayDialog;
		private JButton displayButton;
		private ArrayList<Recipe> recipes;
		private ArrayList<String> names;
		private RecipeBook recipeBook;

	public GUI(){
		readRecipes();
		//setup frame
		int frameWidth = 600;
		int frameHeight = 600;
		frame = new JFrame("Recipe Book");
		frame.setSize(frameWidth, frameHeight); //width height of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set what happens when window is closed

		//drawCreation();
		drawRecipes();
		drawSearch();
		drawAddRecipe();

		//setup outer panel
		outerPanel = new JPanel(new BorderLayout()); //create panel
		//outerPanel.add(creationPanel, BorderLayout.NORTH);
		outerPanel.add(searchPanel, BorderLayout.NORTH);
		outerPanel.add(recipesPanel, BorderLayout.CENTER);
		frame.add(outerPanel); //add panel to frame
		frame.setVisible(true); //set window to be visible and in focus

	}

	private void readRecipes(){
		recipeBook = null;
		try {
				FileInputStream fileIn = new FileInputStream("disk/book.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				recipeBook = (RecipeBook) in.readObject();
				in.close();
				fileIn.close();
				System.out.println("Deserialized from disk/book.ser");
		} catch (IOException i) {
				System.out.println("disk/book.ser not found");
				recipeBook = new RecipeBook();
		} catch (ClassNotFoundException c) {
				System.out.println("Class not found. Made a new one");
				recipeBook = new RecipeBook();
		}
	}

	private void addRecipeToFile(){
		try {
			File dir = new File("disk");
			dir.mkdir();
			FileOutputStream fileOut = new FileOutputStream("disk/book.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(recipeBook);
			out.close();
			fileOut.close();
			System.out.println("Serialized at disk/book.ser");
			System.out.println("Exit");
		} catch (IOException i) {
				i.printStackTrace();
		}
	}

	private void drawCreation(){
		JDialog creationFrame = new JDialog();
		creationFrame.setTitle("Create Recipe");
		creationFrame.setSize(350, 350); //width height of window
		creationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		creationPanel = new JPanel(new GridLayout(0, 1));


		//draw creation objects
		JTextField nameText = new JTextField(10);
		//nameText.setText("Recipe Name");


		JTextField descText = new JTextField(15);
		//descText.setText("Recipe Description");

		JTextField ingredientsText = new JTextField(15);
		//ingredientsText.setText("Ingredients(i.e: tomatos: 1 head, butter: 1/2 tbsp)");

		JTextField stepsText = new JTextField(15);
		//stepsText.setText("Steps(i.e: step1, step2, step3)");

		JLabel nameLabel, descLabel, ingredientsLabel, stepsLabel;
		nameLabel = new JLabel("Recipe Name:");
		descLabel = new JLabel("Recipe Description:");
		ingredientsLabel = new JLabel("Ingredients(i.e: tomatos: 1 head, butter: 1/2 tbsp):");
		stepsLabel = new JLabel("Steps(i.e: step1, step2, step3):");

		JButton submitButton = new JButton("Submit");

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//get input
				String name = nameText.getText();
				String desc = descText.getText();
				String ingredients = ingredientsText.getText();
				String steps = stepsText.getText();
				System.out.println(name +" "+ desc+" "+ ingredients+" "+ steps);
				String[] ingArray = ingredients.split(", ");
				String[] stepsArray = steps.split(", ");
				System.out.println(Arrays.toString(ingArray));
				System.out.println(Arrays.toString(stepsArray));

				Recipe newRecipe = new Recipe(recipes.size()+1); //create new recipe
				//parse data from array
				ArrayList<Ingredient> newIngredients = new ArrayList<Ingredient>();
				ArrayList<String> newSteps = new ArrayList<String>();
				for (String ing: ingArray) {
					String[] ingSplit = ing.split(": ");
					newIngredients.add(new Ingredient(ingSplit[0], ingSplit[1]));
				}
				for (String step: stepsArray) {
					newSteps.add(step);
				}
				//set recipe params
				newRecipe.setName(name);
				newRecipe.setDescription(desc);
				newRecipe.setIngredients(newIngredients);
				newRecipe.setSteps(newSteps);
				//add recipe to recipebook
				recipeBook.addRecipe(newRecipe);
				//add and update recipe and recipebook
				addRecipeToFile();
				searchRecipeBook();//update recipes
				creationFrame.dispose();
			}
		});



		//setup creation layout
		//creationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//add objects to panel
		creationPanel.add(nameLabel);
		creationPanel.add(nameText);
		creationPanel.add(descLabel);
		creationPanel.add(descText);
		creationPanel.add(ingredientsLabel);
		creationPanel.add(ingredientsText);
		creationPanel.add(stepsLabel);
		creationPanel.add(stepsText);
		creationPanel.add(submitButton);

		creationFrame.add(creationPanel);
		// creationPanel.add(stepsPanel, BorderLayout.CENTER);
		// creationPanel.add(recipeDescText);

        creationFrame.setModal(true);
		creationFrame.setVisible(true);
	}

	private void drawAddRecipe(){
			JButton creationButton = new JButton("Add Recipe");
			creationButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					drawCreation();
				}

			});

			searchPanel.add(creationButton);
	}

	private void searchRecipeBook(){
		recipes = recipeBook.searchRecipe(search.getText());
		names = new ArrayList<String>();
		for(int i = 0; i < recipes.size(); i++) {
			String currentname = recipes.get(i).getName();
			names.add(currentname);
		}
		list.setListData(names.toArray());
		list.setSelectedIndex(0);
	}

	private void drawSearch(){
		searchPanel = new JPanel(); //create panel

		//create search objects
		search = new JTextField(20); // search textbox
		JButton searchButton = new JButton("Search");


		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchRecipeBook();
			}
		});

		//setup layout
		FlowLayout searchLayout = new FlowLayout(FlowLayout.CENTER);
		searchPanel.setLayout(searchLayout);
		//add objects to panel
		searchPanel.add(search);
		searchPanel.add(searchButton);
	}

	//This class allows to iterate through the recipe while storing the
	//current state of the recipe (which step the user has selected)
	public class IterateStep{
	  private int index; //keep track of index
	  private Recipe recipe;
	  private String stepBuilder; //slowly increment to build full list of steps
	  private String original;


	  public IterateStep(Recipe recipe){
	    this.index = 1;
	    this.recipe = recipe;

	    //build ingredients list
	    String selectedingredients = "<ul>";
	    for (int i = 0; i < this.recipe.getIngredients().toArray().length; i++) {
	      selectedingredients += "<li>" + this.recipe.getIngredients().get(i).getMeasurement() + " " + this.recipe.getIngredients().get(i).getName() + "</li>";
	    }
	    selectedingredients += "</ul>";

	    //only put first step
	    String firstStep = "<ol>" + "<li>" + this.recipe.getSteps().get(0) + "</li>" + "</ol>";
	    this.stepBuilder = firstStep;
	    this.original = firstStep;
	  }

	//this method adds the next step to stepBuilder field
	  public String getNext(){
	    if(this.index < this.recipe.getSteps().toArray().length){
	      String step = this.recipe.getSteps().get(this.index);
	      this.stepBuilder = this.stepBuilder.substring(0,this.stepBuilder.length()-5);
	      this.stepBuilder += "<li>"+ step + "</li>" + "</ol>";
	      this.index += 1;
	    }
	    return this.stepBuilder;
	  }

	  public int getIndex() { return this.index; }
	}

	//this function defines the recipe exploration functionality in GUI
	//takes as input the selected recipe of the user
	private void exploreRecipe(Recipe selectedRecipe){
	  //build JDialog to house recipe values
	  JDialog exploreFrame = new JDialog();
	  exploreFrame.setTitle(selectedRecipe.getName());
	  exploreFrame.setSize(400,400);
	  exploreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  JPanel mainPanel = new JPanel(new BorderLayout());
	  mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	  JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  JButton next = new JButton("Next");
	  JToggleButton displayToggleButton = new JToggleButton("View step by step",false); //this button toggles between step-by-step and whole recipe view


	  //construct Recipe String
	  String selectedingredients = "<ul>";
	  for (int i = 0; i < selectedRecipe.getIngredients().toArray().length; i++) {
	    selectedingredients += "<li>" + selectedRecipe.getIngredients().get(i).getMeasurement() + " " + selectedRecipe.getIngredients().get(i).getName() + "</li>";
	  }
	  selectedingredients += "</ul>";
	  String selectedsteps = "<ol>";
	  for (int i = 0; i < selectedRecipe.getSteps().toArray().length; i++) {
	    selectedsteps += "<li>" + selectedRecipe.getSteps().get(i) + "</li>";
	  }
	  selectedsteps += "</ol>";
	  String recipeTextString = "<html><h1>" + selectedRecipe.getName() + "</h1><h3>" + selectedRecipe.getDescription() + "</h3><p>Ingredients:<br />" + selectedingredients + "</p><br /><p>Steps:<br />" + selectedsteps + "</p></html>";
	  JLabel recipeText = new JLabel("<html><h1>" + selectedRecipe.getName() + "</h1><h3>" + selectedRecipe.getDescription() + "</h3><p>Ingredients:<br />" + selectedingredients + "</p><br /><p>Steps:<br />" + selectedsteps + "</p></html>");

	//define behavior when toggle button is clicked
	  displayToggleButton.addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e){
	      boolean selected = ((JToggleButton)e.getSource()).isSelected();
	      if(selected == true){ //if clicked
	        IterateStep buildSteps = new IterateStep(selectedRecipe); //get new iterator
	        displayToggleButton.setText("View Entire Recipe");

	        //build String to put in panel
	        String selectedingredients = "<ul>";
	        for (int i = 0; i < selectedRecipe.getIngredients().toArray().length; i++) {
	          selectedingredients += "<li>" + selectedRecipe.getIngredients().get(i).getMeasurement() + " " + selectedRecipe.getIngredients().get(i).getName() + "</li>";
	        }
	        selectedingredients += "</ul>";
	        String firstStep = "<ol>" + "<li>" + selectedRecipe.getSteps().get(0) + "</li>" + "</ol>";
	        String firstDisplayString = "<html><h1>" + selectedRecipe.getName() + "</h1><h3>" + selectedRecipe.getDescription() + "</h3><p>Ingredients:<br />" + selectedingredients + "</p><br /><p>Steps:<br />" + firstStep + "</p></html>";

	        //update bottom panel
					btnPnl.remove(displayToggleButton);
	        recipeText.setText(firstDisplayString);
	        btnPnl.add(next);
	        btnPnl.revalidate();
	        btnPnl.repaint();

	        //define behavior when next button is clicked
	        next.addActionListener(new ActionListener(){
	          @Override
	          public void actionPerformed(ActionEvent e){

	            if(buildSteps.getIndex() == selectedRecipe.getSteps().toArray().length-1){ //when last step is reached remove buttons
	              btnPnl.remove(next);

	            }

	            //build display string
	            String selectedingredients = "<ul>";
	            for (int i = 0; i < selectedRecipe.getIngredients().toArray().length; i++) {
	              selectedingredients += "<li>" + selectedRecipe.getIngredients().get(i).getMeasurement() + " " + selectedRecipe.getIngredients().get(i).getName() + "</li>";
	            }
	            selectedingredients += "</ul>";
	            String displaySteps = "<html><h1>" + selectedRecipe.getName() + "</h1><h3>" + selectedRecipe.getDescription() + "</h3><p>Ingredients:<br />" + selectedingredients + "</p><br /><p>Steps:<br />" + buildSteps.getNext() + "</p></html>";
	            recipeText.setText(displaySteps);
	          }
	        });
	      } else { //go back to entire recipe view
	        displayToggleButton.setText("View step by step");
	        recipeText.setText(recipeTextString);
	        btnPnl.remove(next);
	        btnPnl.revalidate();
	        btnPnl.repaint();
	      }
	    }
	  });
	  //update panels and add to main JDialog to display
	  btnPnl.add(displayToggleButton);
	  mainPanel.add(btnPnl, BorderLayout.SOUTH);
	  mainPanel.add(recipeText);
	  exploreFrame.setLocationRelativeTo(frame);
	  exploreFrame.add(mainPanel);
	  exploreFrame.pack();
	  exploreFrame.setModal(true);
	  exploreFrame.setVisible(true);
	}



	private void drawRecipes(){
		recipesPanel = new JPanel(); //create panel

		recipes = recipeBook.searchRecipe("");
		names = new ArrayList<String>();
		for(int i = 0; i < recipes.size(); i++) {
			String currentname = recipes.get(i).getName();
			names.add(currentname);
		}

		list = new JList(names.toArray());
		list.setSelectedIndex(0);

		displayButton = new JButton("Display Recipe");
		displayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedValue = list.getSelectedIndex();
				Recipe selectedRecipe = recipes.get(selectedValue);
				exploreRecipe(selectedRecipe);
				/*
				String selectedingredients = "<ul>";
				for (int i = 0; i < selectedRecipe.getIngredients().toArray().length; i++) {
					selectedingredients += "<li>" + selectedRecipe.getIngredients().get(i).getMeasurement() + " " + selectedRecipe.getIngredients().get(i).getName() + "</li>";
				}
				selectedingredients += "</ul>";
				String selectedsteps = "<ol>";
				for (int i = 0; i < selectedRecipe.getSteps().toArray().length; i++) {
					selectedsteps += "<li>" + selectedRecipe.getSteps().get(i) + "</li>";
				}
				selectedsteps += "</ol>";
			    JLabel recipeText = new JLabel("<html><h1>" + selectedRecipe.getName() + "</h1><h3>" + selectedRecipe.getDescription() + "</h3><p>Ingredients:<br />" + selectedingredients + "</p><br /><p>Steps:<br />" + selectedsteps + "</p></html>");
				displayDialog = new JDialog();
				displayDialog.setTitle("Recipe");
				displayDialog.setSize(400, 400);
				displayDialog.setLocationRelativeTo(frame);
		        displayDialog.getContentPane().add(recipeText);
		        displayDialog.pack();
		        displayDialog.setModal(true);
		        displayDialog.setVisible(true);
			*/
			}
		});
		FlowLayout recipesLayout = new FlowLayout(FlowLayout.LEFT);
		//add to panel
		recipesPanel.setLayout(recipesLayout);
		//recipesPanel.add(label);
		recipesPanel.add(list);
		recipesPanel.add(displayButton);
	}

	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clicked!");
		label.setText("recipe");
	}

}
