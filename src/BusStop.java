public class BusStop {
    String name, oldName, wheelchair;
    boolean valid;
    double lat, lon, distance;

    public BusStop(double lat1, double lon1, double lat2, double lon2){
        this.lat=lat1;
        this.lon=lon1;
        valid=false;

        distance=dist(lat1, lon1, lat2, lon2);
    }

    public void setValid(boolean valid){
        this.valid=valid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    public boolean getValid(){
        return valid;
    }

    public double getDistance(){
        return distance;
    }

    public String toString(){
        if(oldName!=null){
            return "Megálló: \n" +
                    "\tNév: " + name + " (" + oldName + ")\n" +
                    "\tKerekesszék: " + wheelchair + "\n" +
                    "\tTávolság: " + distance;
        } else{
            return "Megálló: \n" +
                    "\tNév: " + name + "\n" +
                    "\tKerekesszék: " + wheelchair + "\n" +
                    "\tTávolság: " + distance;
        }
    }

    double dist(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000; // metres
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dphi = phi2-phi1;
        double dl = Math.toRadians(lon2-lon1);
        double a = Math.sin(dphi/2) * Math.sin(dphi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(dl/2) * Math.sin(dl/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d;
    }
}
