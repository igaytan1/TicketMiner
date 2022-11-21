//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Stadium Class

public class Stadium extends Venue {
    public Stadium(){

    }

    public Stadium(String venueNameIn, double pctSeatUnavailableIn, int venueCapacityIn, int venueCostIn, double vipPctIn,
            double goldPctIn, double silverPctIn, double bronzePctIn, double gaPctIn, double reservedExtraPctIn,
            boolean fireWorksIn, double fireworksCostIn) {
        super(venueNameIn, pctSeatUnavailableIn, venueCapacityIn, venueCostIn, vipPctIn, goldPctIn, silverPctIn,
                bronzePctIn, gaPctIn, reservedExtraPctIn, fireWorksIn, fireworksCostIn);
    }

    public Stadium(String venueName, double pctSeatUnavailable, int venueCapacity, int venueCost, double vipPct,
            double goldPct, double silverPct, double bronzePct, double gaPct, double reservedExtraPct,
            boolean fireWorks) {
        super(venueName, pctSeatUnavailable, venueCapacity, venueCost, vipPct, goldPct, silverPct, bronzePct, gaPct,
                reservedExtraPct, fireWorks);
    }

    public Stadium(String venueName, double pctSeatUnavailable, int venueCapacity, int venueCost, double vipPct,
            double goldPct, double silverPct, double bronzePct, double gaPct, double reservedExtraPct) {
        super(venueName, pctSeatUnavailable, venueCapacity, venueCost, vipPct, goldPct, silverPct, bronzePct, gaPct,
                reservedExtraPct);
    }
  
}
