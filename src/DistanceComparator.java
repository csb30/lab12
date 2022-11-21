public class DistanceComparator implements java.util.Comparator<BusStop>{

    @Override
    public int compare(BusStop o1, BusStop o2) {
        return (int) (o1.getDistance()-o2.getDistance());
    }
}
