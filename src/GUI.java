import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import classes.Recipe;
import classes.RecipeBook;

import javax.swing.BorderFactory;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI implements ActionListener{
		private JFrame frame;
		private JLabel label;
		private JPanel searchPanel;
		private JPanel recipesPanel;
		private JPanel outerPanel;
		private JButton button;
		private JTextField search;
		private FlowLayout layout;
		private JList list;
		private JDialog displayDialog;
		private ArrayList<Recipe> recipes;
		private ArrayList<String> recipeNames;
		private RecipeBook recipeBook;

	public GUI(){
        recipeBook = null;
        try {
            FileInputStream fileIn = new FileInputStream("example/example.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            recipeBook = (RecipeBook) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized from example/example.ser");
        } catch (IOException i) {
            System.out.println("example/example.ser not found");
            recipeBook = new RecipeBook();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found. Made a new one");
            recipeBook = new RecipeBook();
        }
		
		//setup size vars
		int frameWidth = 500;
		int frameHeight = 500;

		outerPanel = new JPanel(new BorderLayout()); //create panel
		searchPanel = new JPanel(); //create panel
		recipesPanel = new JPanel(); //create panel
		//setup frame
		frame = new JFrame("Recipe Book");
		frame.setSize(frameWidth, frameHeight); //width height of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set what happens when window is closed
		outerPanel.add(searchPanel, BorderLayout.NORTH);
		outerPanel.add(recipesPanel, BorderLayout.CENTER);
		frame.add(outerPanel); //add panel to frame
		//frame.add(recipesPanel); //add panel to frame

		//create objects to put in panel
		recipes = recipeBook.searchRecipe("");
		recipeNames = new ArrayList<String>();
		for(int i = 0; i < recipes.size(); i++) {
			String currentRecipeName = recipes.get(i).getName();
			recipeNames.add(currentRecipeName);
		}
		
		list = new JList(recipeNames.toArray());
		list.setSelectedIndex(0);
		//label = new JLabel("<html>Recipe(s):<ol><li>burger<ul><li>description:</li><li>ingredients:</li><li>instructions:</li></ul></li><li>pasta<ul><li>description:</li><li>ingredients:</li><li>instructions:</li></ul></li></html>");
		//label.setBounds(10, 20, 160, 25); //x, y, w, h of label
		button = new JButton("Search");
		//button.setBounds(searchButtonX, searchButtonY, searchButtonWidth, searchButtonHeight);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub 
				recipes = recipeBook.searchRecipe(search.getText());
				recipeNames = new ArrayList<String>();
				for(int i = 0; i < recipes.size(); i++) {
					String currentRecipeName = recipes.get(i).getName();
					recipeNames.add(currentRecipeName);
				}
				list.setListData(recipeNames.toArray());
				list.setSelectedIndex(0);
			}
			
		});
		JButton displayButton = new JButton("Display Recipe");
		displayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedValue = list.getSelectedIndex();
				Recipe selectedRecipe = recipes.get(selectedValue);
				String selectedRecipeIngredients = "<ul>";
				for (int i = 0; i < selectedRecipe.getIngredients().toArray().length; i++) {
					selectedRecipeIngredients += "<li>" + selectedRecipe.getIngredients().get(i).getMeasurement() + " " + selectedRecipe.getIngredients().get(i).getName() + "</li>";
				}
				selectedRecipeIngredients += "</ul>";
				String selectedRecipeSteps = "<ol>";
				for (int i = 0; i < selectedRecipe.getSteps().toArray().length; i++) {
					selectedRecipeSteps += "<li>" + selectedRecipe.getSteps().get(i) + "</li>";
				}
				selectedRecipeSteps += "</ol>";
			    JLabel recipeText = new JLabel("<html><h1>" + selectedRecipe.getName() + "</h1><h3>" + selectedRecipe.getDescription() + "</h3><p>Ingredients:<br />" + selectedRecipeIngredients + "</p><br /><p>Steps:<br />" + selectedRecipeSteps + "</p></html>");
				displayDialog = new JDialog();
				displayDialog.setTitle("Recipe");
				displayDialog.setSize(400, 400);
				displayDialog.setLocationRelativeTo(frame);
		        displayDialog.getContentPane().add(recipeText);
		        displayDialog.pack();
		        displayDialog.setModal(true);
		        displayDialog.setVisible(true);
			}
			
		});
		search = new JTextField(20);
		//search.setBounds(searchX, searchY, searchWidth, searchHeight);

		FlowLayout searchLayout = new FlowLayout(FlowLayout.CENTER);
		FlowLayout recipesLayout = new FlowLayout(FlowLayout.LEFT);
		//add to panel
		searchPanel.setLayout(searchLayout);
		searchPanel.add(search);
		searchPanel.add(button);
		recipesPanel.setLayout(recipesLayout);
		//recipesPanel.add(label);
		recipesPanel.add(list);
		recipesPanel.add(displayButton);

		frame.setVisible(true); //set window to be visible and in focus

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
