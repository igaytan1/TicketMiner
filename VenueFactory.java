//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - VenueFactory Class

public class VenueFactory {
    
    public VenueFactory(){

    }

    public Venue createVenue(String type, String venueNameIn, double pctSeatUnavailableIn, int venueCapacityIn, int venueCostIn, double vipPctIn, double goldPctIn, double silverPctIn, double bronzePctIn, double gaPctIn, double reservedExtraPctIn, boolean fireWorksIn, double fireworksCostIn){
        if(type.equalsIgnoreCase("Stadium")){
            return new Stadium(venueNameIn, pctSeatUnavailableIn, venueCapacityIn, venueCostIn, vipPctIn, goldPctIn, silverPctIn, bronzePctIn, gaPctIn, reservedExtraPctIn, fireWorksIn, fireworksCostIn);
        }else if(type.equalsIgnoreCase("Arena")){
            return new Arena(venueNameIn, pctSeatUnavailableIn, venueCapacityIn, venueCostIn, vipPctIn, goldPctIn, silverPctIn, bronzePctIn, gaPctIn, reservedExtraPctIn, fireWorksIn, fireworksCostIn);
        }else if(type.equalsIgnoreCase("Auditorium")){
            return new Auditorium(venueNameIn, pctSeatUnavailableIn, venueCapacityIn, venueCostIn, vipPctIn, goldPctIn, silverPctIn, bronzePctIn, gaPctIn, reservedExtraPctIn, fireWorksIn, fireworksCostIn);
        }else if(type.equalsIgnoreCase("Open Air")){
            return new OpenAir(venueNameIn, pctSeatUnavailableIn, venueCapacityIn, venueCostIn, vipPctIn, goldPctIn, silverPctIn, bronzePctIn, gaPctIn, reservedExtraPctIn, fireWorksIn, fireworksCostIn);
        }
        return null;
    }
}
