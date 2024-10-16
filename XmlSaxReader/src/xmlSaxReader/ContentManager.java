package xmlSaxReader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentManager extends DefaultHandler {
	 
	private int tabLevel = 0; // Element indentation level

    public ContentManager() {
        super();
    }

    public void startDocument() {
        System.out.println("Start of XML file");
    }

    public void endDocument() {
        System.out.println("End of XML file");
    }

    public void startElement(String uri, String name, String nameC, Attributes atts) {

        tabLevel++; // Each element hierarchy will have more indentation
        for (int i = 0; i < tabLevel; i++) { // Loop that counts the hierarchy level and applies it to tabulation
            System.out.print("\t");
        }
        
        System.out.println(nameC + ": "); // Shows the element's name

        // ATTRIBUTES, if existent
        if (atts.getLength() > 0) {

            for (int i = 0; i < atts.getLength(); i++) { // For each attribute
            	
                for (int j = 0; j < tabLevel; j++) { // Writes their indentation
                    System.out.print("\t");
                }
                
                System.out.println("\t" + atts.getQName(i) + ": " + atts.getValue(i)); // And their content (extra tab to distinguish them from their parent element)
            }
        }
    }

    public void endElement(String uri, String nombre, String nombreC) {
        tabLevel--; // Returning to the previous indentation level, to avoid its accumulation
    }

    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
    	
        for (int i = 0; i < tabLevel; i++) {
            System.out.print("\t");
        }

        String car = new String(ch, inicio, longitud); // Groups characters from an element in a string
        car = car.replaceAll("[\t\n]", ""); // Trims line breaks and tabs from the content
        System.out.println("  " + car); // Shows it, with a couple of spaces to distinguish it from its element
    }

}
