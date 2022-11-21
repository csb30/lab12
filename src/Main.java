import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public static void main(String[] args) {
        if(args.length>=4) {
            double lat = Double.parseDouble(args[1]);
            double lon = Double.parseDouble(args[3]);
            TagCounter h = new TagCounter(lat, lon);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser p = factory.newSAXParser();
                p.parse(new java.io.File("bme.xml"), h);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(h);
            h.printBusStops();
        }
    }
}