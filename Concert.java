//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Concert Class

public class Concert extends Event {
    public Concert(){

    }

    public Concert(String concertNameIn, String concertDateIn, String concertTimeIn, double vIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn, Venue venueTypeIn, String eventTypeIn, double amountDiscountedIn, int eventIDIn){
        super(concertNameIn, concertDateIn, concertTimeIn, vIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn, venueTypeIn, eventTypeIn, amountDiscountedIn, eventIDIn);
    }

    public Concert(String concertNameIn, String concertDateIn, String concertTimeIn) {
        super(concertNameIn, concertDateIn, concertTimeIn);
        
    }

    public Concert(double vIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn) {
        super(vIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn);
    }
}
