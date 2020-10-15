import core.SampleWriter;
import core.GUI;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class App {
	public static void main(String[] args) {
		Path path = Paths.get("disk");
		if (Files.notExists(path)) {
			SampleWriter.createSamples();
			System.out.println("Sample Recipes Created");
		}
		new GUI();		
	}

}
