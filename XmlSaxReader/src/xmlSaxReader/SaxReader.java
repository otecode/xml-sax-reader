package xmlSaxReader;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SaxReader {
	
	// Method that check if a path is valid. It will call itself
	// until a valid path is entered, which will be returned.
	
	public static String EnterAndValidateRoute(Scanner input) {
		
		String route = null;
		System.out.println("Enter the route for the xml file you want to read: ");
		route = input.nextLine();
		
		try {
			
			Paths.get(route);
			
		}catch (Exception e) {
			
			// TODO: handle exception
			System.out.println("Couldn't validate the entered path. Please enter the route again: ");
			EnterAndValidateRoute(input);
			
		}
		
		return route;

	}

	public static void main(String[] args) throws FileNotFoundException, IOException, SAXException {
		// TODO Auto-generated method stub

		//VARIABLES
		
		SAXParser xmlParser;
		Scanner input = new Scanner(System.in);
		String route = EnterAndValidateRoute(input);
		File file = new File(route);
		
		// Checking existence of file
		
		while (!file.exists()) {
			
			System.out.println("File not found, try again.");
			route = EnterAndValidateRoute(input);
			file = new File(route);
			
		}
		
		// Reading the file and showing it on console
		
        try {
        	
            xmlParser = SAXParserFactory.newInstance().newSAXParser(); // Creates both parserFactory and parser (will parse through the document's lines)          
            ContentManager manager = new ContentManager(); // Creates the handler (will decide an action for each parsed line)
            
            InputSource fileXML = new InputSource(route); // Declares the file to read          
            	
            xmlParser.parse(fileXML, manager); // Tells the parser which file to parse and which handler to use when parsing
            	
            
        } catch (ParserConfigurationException ex) {
        	
            Logger.getLogger(SaxReader.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}

}
