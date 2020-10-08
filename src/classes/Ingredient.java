package classes;

/** Holds the name and measurement of an ingredient. */  
public class Ingredient implements java.io.Serializable{
    private String name;
    private String measurement;

		/** Creates a new Ingredient object. */
		public Ingredient() {}

    public Ingredient(String name, String measurement) {
      this.name = name;
      this.measurement = measurement;
    }

    public String getName() { return name; }

    public String getMeasurement() { return measurement; }

		public void setName(String name) { this.name = name; }

    public void setMeasurement(String measurement) { this.measurement = measurement; }
}