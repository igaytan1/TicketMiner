//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - EventFactory

public class EventFactory {
    
    public EventFactory(){

    }

    public Event createEvent(String eventNameIn, String eventDateIn, String eventTimeIn, double vIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn, Venue venueTypeIn, String eventTypeIn, double amountDiscountedIn, int eventIDIn){
        if(eventTypeIn.equalsIgnoreCase("Sport")){
            return new Sport(eventNameIn, eventDateIn, eventTimeIn, vIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn, venueTypeIn, eventTypeIn, amountDiscountedIn, eventIDIn);
        }else if(eventTypeIn.equalsIgnoreCase("Concert")){
            return new Concert(eventNameIn, eventDateIn, eventTimeIn, vIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn, venueTypeIn, eventTypeIn, amountDiscountedIn, eventIDIn);
        }else if(eventTypeIn.equalsIgnoreCase("Special")){
            return new Special(eventNameIn, eventDateIn, eventTimeIn, vIPPriceIn, goldPriceIn, silverPriceIn, bronzePriceIn, gaPriceIn, venueTypeIn, eventTypeIn, amountDiscountedIn, eventIDIn);
        }
        return null;
    }
}
