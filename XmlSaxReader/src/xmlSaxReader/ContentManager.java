package xmlSaxReader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentManager extends DefaultHandler {
	
	private int nivelTabulado; // Creada para controlar la tabulación por niveles

    public ContentManager() {
        super();
    }

    public void startDocument() {
        System.out.println("Comienzo del documento XML");
    }

    public void endDocument() {
        System.out.println("Fin del documento XML");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {

        nivelTabulado++; // Cada elemento iniciado aumenta el tabulado
        for (int i = 0; i < nivelTabulado; i++) { // Se introduce el nivel de tabulado
            System.out.print("\t");
        }
        System.out.println(nombreC + ": "); // Se muestra el nombre de elemento

        // ATRIBUTOS, si los tiene
        if (atts.getLength() > 0) {

            for (int i = 0; i < atts.getLength(); i++) {
                for (int j = 0; j < nivelTabulado; j++) {
                    System.out.print("\t");
                }
                System.out.println("\t" + atts.getQName(i) + ": " + atts.getValue(i));
                // Tienen un tabulado extra para distinguirlos del elemento al que pertenecen
            }
        }
    }

    public void endElement(String uri, String nombre, String nombreC) {
        nivelTabulado--; // Terminar un elemento disminuye el tabulado, para que no sea acumulativo
    }

    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        for (int i = 0; i < nivelTabulado; i++) {
            System.out.print("\t");
        }

        String car = new String(ch, inicio, longitud); // Agrupa caracteres del contenido de un elemento en un String
        car = car.replaceAll("[\t\n]", ""); // Quita los saltos de línea y tabulaciones
        System.out.println("  " + car); // Lo muestra, con dos espacios para distinguir contenido de elemento
    }

}
