//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Sport Class


public class Sport extends Event {
    public Sport(){
    }

    public Sport(String sportNameIn, String sportDateIn, String sportTimeIn, double VIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn, Venue venueTypeIn, String eventTypeIn, double amountDiscountedIn, int eventIDIn){
        super(sportNameIn, sportDateIn, sportTimeIn, VIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn, venueTypeIn, eventTypeIn, amountDiscountedIn, eventIDIn);
    }

    public Sport(String sportNameIn, String sportDateIn, String sportTimeIn) {
        super(sportNameIn, sportDateIn, sportTimeIn);
        
    }

    public Sport(double VIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn) {
        super(VIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn);
    }
}
