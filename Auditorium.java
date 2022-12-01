//Author: Isabella Gaytan
//Advanced Object-Oriented Programming

/*
 * This class represents a type of Venue, Auditorium
 * 
 * @author Isabella
 * @since October 25, 2022
 */


public class Auditorium extends Venue{
    
    public Auditorium(){

    }

    public Auditorium(String venueNameIn, double pctSeatUnavailableIn, int venueCapacityIn, int venueCostIn,
            double vipPctIn, double goldPctIn, double silverPctIn, double bronzePctIn, double gaPctIn,
            double reservedExtraPctIn, boolean fireWorksIn, double fireworksCostIn) {
        super(venueNameIn, pctSeatUnavailableIn, venueCapacityIn, venueCostIn, vipPctIn, goldPctIn, silverPctIn,
                bronzePctIn, gaPctIn, reservedExtraPctIn, fireWorksIn, fireworksCostIn);
    }

    public Auditorium(String venueName, double pctSeatUnavailable, int venueCapacity, int venueCost, double vipPct,
            double goldPct, double silverPct, double bronzePct, double gaPct, double reservedExtraPct,
            boolean fireWorks) {
        super(venueName, pctSeatUnavailable, venueCapacity, venueCost, vipPct, goldPct, silverPct, bronzePct, gaPct,
                reservedExtraPct, fireWorks);
    }

    public Auditorium(String venueName, double pctSeatUnavailable, int venueCapacity, int venueCost, double vipPct,
            double goldPct, double silverPct, double bronzePct, double gaPct, double reservedExtraPct) {
        super(venueName, pctSeatUnavailable, venueCapacity, venueCost, vipPct, goldPct, silverPct, bronzePct, gaPct,
                reservedExtraPct);
    }

}
