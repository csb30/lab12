import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class TagCounter extends org.xml.sax.helpers.DefaultHandler {
    HashMap<String, Integer> tagcount=new HashMap<String, Integer>();
    BusStop busstop;
    LinkedList<BusStop> busstop_list=new LinkedList<BusStop>();
    double lat, lon;

    public TagCounter(double lat, double lon){
        this.lat=lat;
        this.lon=lon;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (tagcount.containsKey(qName)){
            tagcount.put(qName, tagcount.get(qName)+1);
        } else{
            tagcount.put(qName, 1);
        }

        if (qName.equals("node")){
            busstop=new BusStop(lat, lon, Double.parseDouble(attributes.getValue("lat")), Double.parseDouble(attributes.getValue("lon")));
        } else if (qName.equals("tag")) {
            if(attributes.getValue("v").equals("bus_stop")){
                busstop.setValid(true);
            } else {
                switch (attributes.getValue("k")){
                    case "name":
                        busstop.setName(attributes.getValue("v"));
                        break;
                    case "old_name":
                        busstop.setOldName(attributes.getValue("v"));
                        break;
                    case "wheelchair":
                        busstop.setWheelchair(attributes.getValue("v"));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void endElement (String uri, String localName, String qName){
        if (qName.equals("node") && busstop.getValid()){
            busstop_list.add(busstop);
        }
    }

    public String toString(){
        String output="";

        for (String i : tagcount.keySet()) {
            output+=i + ": " + tagcount.get(i)+"\n";
        }
        return output;
    }

    public void printBusStops(){
        busstop_list.sort(new DistanceComparator());
        for(BusStop i : busstop_list){
            System.out.println(i);
        }
    }
}
