//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Special Class

public class Special extends Event {
    public Special(){

    }

    public Special(String specialNameIn, String specialDateIn, String specialTimeIn, double vipPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn, Venue venueTypeIn, String eventTypeIn, double amountDiscountedIn, int eventIDIn){
        super(specialNameIn, specialDateIn, specialTimeIn, vipPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn, venueTypeIn, eventTypeIn, amountDiscountedIn, eventIDIn);
    }

    public Special(String specialNameIn, String specialDateIn, String specialTimeIn) {
        super(specialNameIn, specialDateIn, specialTimeIn);
        
    }

    public Special(double vIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn) {
        super(vIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn);
    }
}
